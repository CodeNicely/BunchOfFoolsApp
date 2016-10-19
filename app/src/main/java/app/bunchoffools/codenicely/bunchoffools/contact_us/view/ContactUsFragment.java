package app.bunchoffools.codenicely.bunchoffools.contact_us.view;

import android.content.Context;
import android.media.Image;
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

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.contact_us.model.RetrofitContactUsProvider;
import app.bunchoffools.codenicely.bunchoffools.contact_us.model.data.ContactUsData;
import app.bunchoffools.codenicely.bunchoffools.contact_us.presenter.ContactUsPresenter;
import app.bunchoffools.codenicely.bunchoffools.contact_us.presenter.ContactUsPresenterImpl;
import app.bunchoffools.codenicely.bunchoffools.helper.image_loader.GlideImageLoader;
import app.bunchoffools.codenicely.bunchoffools.helper.image_loader.ImageLoader;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactUsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFragment extends Fragment implements ContactUsView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageLoader imageLoader;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.email)
    TextView email;

    @BindView(R.id.mobile)
    TextView mobile;

    @BindView(R.id.address)
    TextView address;

    @BindView(R.id.website)
    TextView website;

    @BindView(R.id.facebook)
    TextView facebook;

    @BindView(R.id.imageProgressBar)
    ProgressBar imageProgressBar;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.contactUsLayout)
    LinearLayout contactUsLayout;

    private ContactUsPresenter contactUsPresenter;
    private OnFragmentInteractionListener mListener;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
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
        View view= inflater.inflate(R.layout.fragment_contact_us, container, false);

        ButterKnife.bind(this,view);

        contactUsPresenter=new ContactUsPresenterImpl(this,new RetrofitContactUsProvider());
        contactUsPresenter.requestContactUs();

        imageLoader=new GlideImageLoader(getContext());
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
    public void showLoader(boolean show) {

        if(show) {

            contactUsLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            contactUsLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(getActivity().findViewById(R.id.cordinatorLayout), message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    @Override
    public void setData(ContactUsData contactUsData) {

        email.setText(contactUsData.getEmail());
        mobile.setText(contactUsData.getMobile());
        address.setText(contactUsData.getAddress());
        website.setText(contactUsData.getWebsite());
        facebook.setText(contactUsData.getFb());

        imageLoader.loadImage(contactUsData.getImage(),imageView,imageProgressBar);




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
}
