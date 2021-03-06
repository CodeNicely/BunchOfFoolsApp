package app.bunchofools.codenicely.project.developers.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.bunchofools.codenicely.project.R;
import app.bunchofools.codenicely.project.developers.model.RetrofitDeveloperProvider;
import app.bunchofools.codenicely.project.developers.model.data.CompanyData;
import app.bunchofools.codenicely.project.developers.presenter.DevelopersPresenter;
import app.bunchofools.codenicely.project.developers.presenter.DevelopersPresenterImpl;
import app.bunchofools.codenicely.project.helper.image_loader.GlideImageLoader;
import app.bunchofools.codenicely.project.helper.image_loader.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeveloperFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeveloperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeveloperFragment extends Fragment implements DeveloperView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View snackView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.imageView)
    ImageView companyImage;

    @BindView(R.id.imageProgressBar)
    ProgressBar imageProgressBar;

    @BindView(R.id.about)
    TextView aboutUs;

    @BindView(R.id.address)
    TextView address;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.mobile)
    TextView mobile;

    @BindView(R.id.website)
    TextView website;

    @BindView(R.id.facebook)
    TextView facebook;

    @BindView(R.id.developers)
    TextView developers;

    @BindView(R.id.developerLayout)
    LinearLayout developerLayout;

    private DevelopersPresenter developersPresenter;
    private OnFragmentInteractionListener mListener;

    public DeveloperFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeveloperFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeveloperFragment newInstance(String param1, String param2) {
        DeveloperFragment fragment = new DeveloperFragment();
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
        View view = inflater.inflate(R.layout.fragment_developer, container, false);
        ButterKnife.bind(this, view);
        developersPresenter=new DevelopersPresenterImpl(this,new RetrofitDeveloperProvider());
        developersPresenter.requestDevelopersData();
        return view;
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoading(boolean show) {

        if (show)
        {
            progressBar.setVisibility(View.VISIBLE);
            developerLayout.setVisibility(View.GONE);
            }
        else
        {
            progressBar.setVisibility(View.GONE);
            developerLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {

        Snackbar snackbar = Snackbar
                .make(snackView, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void setData(CompanyData companyData) {
        if (companyData != null) {

            developers.setText(companyData.getCompany());
            mobile.setText(companyData.getContact());
            email.setText(companyData.getEmail());
            address.setText(companyData.getAddress());
            facebook.setText(companyData.getFacebook());
            aboutUs.setText(companyData.getAbout());

            website.setText(companyData.getWebsite());
            ImageLoader imageLoader = new GlideImageLoader(getContext());
            imageLoader.loadImage(companyData.getCompanyImage(), companyImage, imageProgressBar);

        }
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
        developersPresenter.onDestroy();
    }
}
