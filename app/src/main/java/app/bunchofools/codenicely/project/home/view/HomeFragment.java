package app.bunchofools.codenicely.project.home.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import app.bunchofools.codenicely.project.R;
import app.bunchofools.codenicely.project.about_us.view.AboutUsFragment;
import app.bunchofools.codenicely.project.contact_us.view.ContactUsFragment;
import app.bunchofools.codenicely.project.gallery.view.GalleryFragment;
import app.bunchofools.codenicely.project.helper.HeaderGridView;
import app.bunchofools.codenicely.project.home.model.RetrofitHomeProvider;
import app.bunchofools.codenicely.project.home.model.data.HomeData;
import app.bunchofools.codenicely.project.home.model.data.HomeGridData;
import app.bunchofools.codenicely.project.home.presenter.HomePresenter;
import app.bunchofools.codenicely.project.home.presenter.HomePresenterImpl;
import app.bunchofools.codenicely.project.join_us.JoinUsCallback;
import app.bunchofools.codenicely.project.join_us.api.JoinUsApi;
import app.bunchofools.codenicely.project.join_us.view.JoinUsFragment;
import app.bunchofools.codenicely.project.spot_upload.view.UploadSpotFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String FACEBOOK_ID="bunchofools";
    private static final String TWITTER_ID="bunch_fools";
    private static final String INSTAGRAM_ID="bunchofools";

    private View snackView;

   /* @BindView(R.id.viewPager)
    ViewPager viewPager;
*/
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.homeLayout)
    LinearLayout homeLayout;


    @BindView(R.id.aboutUsCard)
    CardView aboutUsCard;

    @BindView(R.id.galleryCard)
    CardView galleryCard;

    @BindView(R.id.uploadSpotCard)
    CardView uploadSpotCard;

    @BindView(R.id.joinUsCard)
    CardView joinUsCard;

    @BindView(R.id.contactUsCard)
    CardView contactUsCard;

    @BindView(R.id.facebookCard)
    CardView facebookCard;

    @BindView(R.id.twitterCard)
    CardView twitterCard;

    @BindView(R.id.instagramCard)
    CardView instagramCard;



    private HomeGridAdapter homeGridAdapter;

    private ImagesAdapter imagesAdapter;

    private HomePresenter homePresenter;
    private FacebookAdapter facebookAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Snackbar snackbar;
    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        aboutUsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AboutUsFragment aboutUsFragment= new AboutUsFragment();
                ((HomeActivity) getActivity()).setFragment(aboutUsFragment,"About Us");
            }
        });

        galleryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryFragment galleryFragment=new GalleryFragment();
                ((HomeActivity)getActivity()).setFragment(galleryFragment,"Gallery");
            }
        });


        uploadSpotCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadSpotFragment uploadSpotFragment=new UploadSpotFragment();
                ((HomeActivity)getActivity()).setFragment(uploadSpotFragment,"Upload Spot");
            }
        });


        joinUsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinUsFragment joinUsFragment=new JoinUsFragment();
                ((HomeActivity)getActivity()).setFragment(joinUsFragment,"Join Us");
            }
        });

        contactUsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactUsFragment contactUsFragment=new ContactUsFragment();
                ((HomeActivity)getActivity()).setFragment(contactUsFragment,"Contact Us");
            }
        });


        final String facebookUrl="https://www.facebook.com/"+FACEBOOK_ID;
        final String twitterUrl="https://www.twitter.com/"+TWITTER_ID;
        final String instagramUrl="https://www.instagram.com/"+INSTAGRAM_ID;

        facebookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    facebookIntent.setData(Uri.parse(getFacebookPageURL(getContext(), FACEBOOK_ID, FACEBOOK_ID)));
                    startActivity(facebookIntent);
                }catch (Exception e){

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(facebookUrl));
                    startActivity(i);
                }

            }
        });

        twitterCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                try {
                    // get the Twitter app if possible
                    getContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name="+TWITTER_ID));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    // no Twitter app, revert to browser
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
                }
                getContext().startActivity(intent);
            }
        });

        instagramCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivity(newInstagramProfileIntent(getContext().getPackageManager(),instagramUrl));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(instagramUrl)));
                }
            }
        });








        snackView=getActivity().findViewById(R.id.cordinatorLayout);
 //       homeGridAdapter=new HomeGridAdapter(getContext());
//        gridView.setAdapter(homeGridAdapter);
//        setGridData();
  //      gridView.setColumnWidth(4);


        homePresenter=new HomePresenterImpl(this,new RetrofitHomeProvider());
        homePresenter.requestHome();
        imagesAdapter = new ImagesAdapter(getContext());
        facebookAdapter=new FacebookAdapter(getContext());
        recyclerView.setAdapter(facebookAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

  //      viewPager.setAdapter(imagesAdapter);

        return view;
    }

    void setGridData(){

        List<HomeGridData> homeGridDataList=new ArrayList<>();


        HomeGridData home= new HomeGridData("home",R.drawable.ic_home_black_24dp);
        HomeGridData aboutUs= new HomeGridData("About Us",R.drawable.ic_about_us_24dp);
        HomeGridData contactUs= new HomeGridData("home",R.drawable.ic_home_black_24dp);
        HomeGridData gallery= new HomeGridData("home",R.drawable.ic_home_black_24dp);
        HomeGridData facebook= new HomeGridData("home",R.drawable.ic_home_black_24dp);
        HomeGridData twitter= new HomeGridData("home",R.drawable.ic_home_black_24dp);
        HomeGridData instagram= new HomeGridData("home",R.drawable.ic_home_black_24dp);
        HomeGridData joinUs= new HomeGridData("home",R.drawable.ic_home_black_24dp);
        HomeGridData uploadSpot= new HomeGridData("home",R.drawable.ic_home_black_24dp);

        homeGridDataList.add(home);
        homeGridDataList.add(aboutUs);
        homeGridDataList.add(contactUs);
        homeGridDataList.add(gallery);
        homeGridDataList.add(facebook);
        homeGridDataList.add(twitter);
        homeGridDataList.add(instagram);
        homeGridDataList.add(joinUs);
        homeGridDataList.add(uploadSpot);

        homeGridAdapter.setData(homeGridDataList);
        homeGridAdapter.notifyDataSetChanged();

    }

/*
    List<String> getMockImageList() {

        List<String> imageUrlList = new ArrayList<>();
        imageUrlList.add("http://bunchofools.com/wp/wp-content/uploads/2015/10/310.jpg");
        imageUrlList.add("http://bunchofools.com/wp/wp-content/uploads/2015/10/410.jpg");
        imageUrlList.add("http://bunchofools.com/wp/wp-content/uploads/2015/10/1.jpg");
        imageUrlList.add("http://bunchofools.com/wp/wp-content/uploads/2015/10/51.jpg");

        return imageUrlList;
    }

*/


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoader(boolean show) {

        if (show) {
            progressBar.setVisibility(View.VISIBLE);
            homeLayout.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            homeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {


        Snackbar snackbar = Snackbar.make(snackView, message, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    @Override
    public void setData(HomeData homeData) {


        imagesAdapter.setImageList(homeData.getSlider_data());
        imagesAdapter.notifyDataSetChanged();

        facebookAdapter.setData(homeData.getFeeds().getData());
        facebookAdapter.notifyDataSetChanged();


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onStop() {
        super.onStop();
        homePresenter.onDestroy();

    }





    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context,String facebookUrl,String facebookPageId) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + facebookUrl;
            } else { //older versions of fb app
                return "fb://page/" + facebookPageId;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return facebookUrl; //normal web url
        }
    }

    public static Intent newInstagramProfileIntent(PackageManager pm, String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                final String username = url.substring(url.lastIndexOf("/") + 1);
                // http://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android
                intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                intent.setPackage("com.instagram.android");
                return intent;
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        intent.setData(Uri.parse(url));
        return intent;
    }
}
