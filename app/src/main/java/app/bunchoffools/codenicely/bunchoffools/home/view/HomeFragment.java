package app.bunchoffools.codenicely.bunchoffools.home.view;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.home.model.RetrofitHomeProvider;
import app.bunchoffools.codenicely.bunchoffools.home.model.data.HomeData;
import app.bunchoffools.codenicely.bunchoffools.home.presenter.HomePresenter;
import app.bunchoffools.codenicely.bunchoffools.home.presenter.HomePresenterImpl;
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
    private View snackView;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.homeLayout)
    LinearLayout homeLayout;
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

        snackView=getActivity().findViewById(R.id.cordinatorLayout);

        homePresenter=new HomePresenterImpl(this,new RetrofitHomeProvider());
        homePresenter.requestHome();
        imagesAdapter = new ImagesAdapter(getContext());
        facebookAdapter=new FacebookAdapter(getContext());
        recyclerView.setAdapter(facebookAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

    //    imagesAdapter.setImageList(getMockImageList());
    //    imagesAdapter.notifyDataSetChanged();
        viewPager.setAdapter(imagesAdapter);

        return view;
    }

    List<String> getMockImageList() {

        List<String> imageUrlList = new ArrayList<>();
        imageUrlList.add("http://bunchofools.com/wp/wp-content/uploads/2015/10/310.jpg");
        imageUrlList.add("http://bunchofools.com/wp/wp-content/uploads/2015/10/410.jpg");
        imageUrlList.add("http://bunchofools.com/wp/wp-content/uploads/2015/10/1.jpg");
        imageUrlList.add("http://bunchofools.com/wp/wp-content/uploads/2015/10/51.jpg");

        return imageUrlList;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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

}
