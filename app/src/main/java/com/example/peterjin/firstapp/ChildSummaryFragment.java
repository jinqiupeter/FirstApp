package com.example.peterjin.firstapp;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChildSummaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChildSummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChildSummaryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChildSummaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChildSummaryFragment newInstance(String param1, String param2) {
        ChildSummaryFragment fragment = new ChildSummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ChildSummaryFragment() {
        // Required empty public constructor
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
        View rootView = inflater.inflate(R.layout.fragment_child_summary, container, false);

        Bundle bundle = getArguments();
        Child child = (Child)bundle.getSerializable(Child.EXTRA_MESSAGE_CHILD);
        /*
        Child child;
        if (getActivity() != null)
        child = (Child)getActivity().getIntent().getSerializableExtra(Child.EXTRA_MESSAGE_CHILD);
        else
            child = (Child)savedInstanceState.getSerializable(Child.EXTRA_MESSAGE_CHILD);
            */
        initView(rootView, child);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        //if (mListener != null) {
        //    mListener.onFragmentInteraction(uri);
       // }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //try {
          //  mListener = (OnFragmentInteractionListener) activity;
        //} catch (ClassCastException e) {
          //  throw new ClassCastException(activity.toString()
            //        + " must implement OnFragmentInteractionListener");
        //}
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private void initView(View view, Child child) {
        //name
        TextView nameKeyTextView = (TextView) view.findViewById(R.id.name_key);
        TextView nameValueTextView = (TextView) view.findViewById(R.id.name_value);
        //gender
        TextView genderKeyTextView = (TextView) view.findViewById(R.id.gender_key);
        TextView genderValueTextView = (TextView) view.findViewById(R.id.gender_value);
        //id
        TextView idTextView = (TextView) view.findViewById(R.id.id_key);
        TextView idValueTextView = (TextView) view.findViewById(R.id.id_value);
        //regDate
        TextView regDateTextView = (TextView) view.findViewById(R.id.reg_date_key);
        TextView regDateValueTextView = (TextView) view.findViewById(R.id.reg_date_value);
        //lostDate
        TextView lostDateTextView = (TextView) view.findViewById(R.id.lost_date_key);
        TextView lostDateValueTextView = (TextView) view.findViewById(R.id.lost_date_value);
        //image
        ImageView img = (ImageView)view.findViewById(R.id.summary_image);

        nameKeyTextView.setText(child.getKey(Child.NAME_IDX) + ": ");
        nameValueTextView.setText(child.getValue(Child.NAME_IDX));
        genderKeyTextView.setText(child.getKey(Child.GENDER_IDX) + ": ");
        genderValueTextView.setText(child.getValue(Child.GENDER_IDX));
        idTextView.setText(child.getKey(Child.ID_IDX) + ": ");
        idValueTextView.setText(child.getValue(Child.ID_IDX));
        regDateTextView.setText(child.getKey(Child.REG_TIME_IDX) + ": ");
        regDateValueTextView.setText(child.getValue(Child.REG_TIME_IDX));
        lostDateTextView.setText(child.getKey(Child.LOST_TIME_IDX) + ": ");
        lostDateValueTextView.setText(child.getValue(Child.LOST_TIME_IDX));

        String image = child.getValue(Child.IMAGE_IDX);
        if (image.isEmpty()) {
            img.setImageResource(R.drawable.none_120_150);
        } else {
            byte[] data = Base64.decode(image, Base64.DEFAULT);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            img.setImageBitmap(BitmapFactory.decodeStream(inputStream));
        }
    }

}
