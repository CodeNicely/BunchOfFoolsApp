package app.bunchoffools.codenicely.bunchoffools.join_us.view;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.join_us.model.RetrofitJoinUsProvider;
import app.bunchoffools.codenicely.bunchoffools.join_us.model.VolleyJoinUsProvider;
import app.bunchoffools.codenicely.bunchoffools.join_us.presenter.JoinUsPresenter;
import app.bunchoffools.codenicely.bunchoffools.join_us.presenter.JoinUsPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JoinUsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JoinUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JoinUsFragment extends Fragment implements JoinUsView{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.mobile)
    EditText mobile;

    @BindView(R.id.submitButton)
    Button submitButton;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private OnFragmentInteractionListener mListener;

    public JoinUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JoinUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JoinUsFragment newInstance(String param1, String param2) {
        JoinUsFragment fragment = new JoinUsFragment();
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
        View view=inflater.inflate(R.layout.fragment_join_us, container, false);
        ButterKnife.bind(this,view);

        final JoinUsPresenter joinUsPresenter=new JoinUsPresenterImpl(this,new RetrofitJoinUsProvider());


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1=name.getText().toString().trim();
                String mobile1=mobile.getText().toString().trim();
                String email1=email.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(name1.equals("") || name1.equals(null)){
                    name.setError("Please fill name", ContextCompat.getDrawable
                            (getContext(),R.drawable.ic_error_blue_grey_500_24dp));
                    name.requestFocus();

                }else if(mobile1.equals("") || mobile1.equals(null)){
                    mobile.setError("Please fill mobile number",ContextCompat.getDrawable
                            (getContext(),R.drawable.ic_error_blue_grey_500_24dp));
                    mobile.requestFocus();
                }else if(email1.equals("") || email1.equals("")){
                    email.setError("Please enter Email Id",ContextCompat.getDrawable
                            (getContext(),R.drawable.ic_error_blue_grey_500_24dp));
                    email.requestFocus();
                }else if(!email1.equals(emailPattern)){
                    email.setError("Invalid Email Address",ContextCompat.getDrawable
                            (getContext(),R.drawable.ic_error_blue_grey_500_24dp));
                    email.requestFocus();


                }

                joinUsPresenter.requestJoin(name1,mobile1,email1);

            }
        });



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
/*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showLoading(boolean show) {

        if(show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
        // Show loder or hide loader
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

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
