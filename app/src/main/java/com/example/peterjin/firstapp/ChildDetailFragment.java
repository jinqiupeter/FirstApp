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
 * {@link ChildDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ChildDetailFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;

    public ChildDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_detail, container, false);
        Bundle bundle = getArguments();
        Child child = (Child)bundle.getSerializable(Child.EXTRA_MESSAGE_CHILD);
        /*
        if (getActivity() != null)
            child = (Child)getActivity().getIntent().getSerializableExtra(Child.EXTRA_MESSAGE_CHILD);
        else
            child = (Child)savedInstanceState.getSerializable(Child.EXTRA_MESSAGE_CHILD);
            */
        initView(view, child);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        /*
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
        */
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
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
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView genderTextView = (TextView) view.findViewById(R.id.gender);
        TextView idTextView = (TextView) view.findViewById(R.id.id);
        TextView regDateTextView = (TextView) view.findViewById(R.id.reg_date);
        TextView lostDateTextView = (TextView) view.findViewById(R.id.lost_date);
        TextView dobTextView = (TextView) view.findViewById(R.id.dob);
        TextView lostLocTextView = (TextView) view.findViewById(R.id.lost_loc);
        TextView heightTextView = (TextView) view.findViewById(R.id.height);
        TextView descTextView = (TextView) view.findViewById(R.id.desc);
        TextView otherInfoTextView = (TextView) view.findViewById(R.id.other_info);
        ImageView img = (ImageView) view.findViewById(R.id.detail_image);

        nameTextView.setText(child.getKey(Child.NAME_IDX)               + ":\t\t\t\t\t\t\t " + child.getValue(Child.NAME_IDX));
        genderTextView.setText(child.getKey(Child.GENDER_IDX)           + ":\t\t\t\t\t\t\t " + child.getValue(Child.GENDER_IDX));
        idTextView.setText(child.getKey(Child.ID_IDX)                   + ":\t\t\t\t\t " + child.getValue(Child.ID_IDX));
        regDateTextView.setText(child.getKey(Child.REG_TIME_IDX)        + ":\t\t\t\t\t " + child.getValue(Child.REG_TIME_IDX));
        lostDateTextView.setText(child.getKey(Child.LOST_TIME_IDX)      + ":\t\t\t\t\t " + child.getValue(Child.LOST_TIME_IDX));
        dobTextView.setText(child.getKey(Child.DOB_IDX)                 + ":\t\t\t\t\t " + child.getValue(Child.DOB_IDX));
        lostLocTextView.setText(child.getKey(Child.LOST_LOC_IDX)        + ":\t\t\t\t\t " + child.getValue(Child.LOST_LOC_IDX));
        heightTextView.setText(child.getKey(Child.HEIGHT_IDX)           + ":\t\t\t "   + child.getValue(Child.HEIGHT_IDX));
        descTextView.setText(child.getKey(Child.DESC_IDX)               + ": "     + child.getValue(Child.DESC_IDX));
        otherInfoTextView.setText(child.getKey(Child.OTHER_INFO_IDX)    + ":\t\t\t\t\t " + child.getValue(Child.OTHER_INFO_IDX));

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