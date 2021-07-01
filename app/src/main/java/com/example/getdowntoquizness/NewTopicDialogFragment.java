package com.example.getdowntoquizness;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewTopicDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewTopicDialogFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewTopicDialogFragment() {
        // Required empty public constructor
    }

    public interface NewTopicDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);//, String newTopic);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    NewTopicDialogListener listener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewTopicDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewTopicDialogFragment newInstance(String param1, String param2) {
        NewTopicDialogFragment fragment = new NewTopicDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static NewTopicDialogFragment newInstance() {
        return new NewTopicDialogFragment();
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
        return inflater.inflate(R.layout.fragment_new_topic_dialog, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_new_topic_dialog, null))
                // Add action buttons
                .setPositiveButton("Add Topic", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
//                        String newTopic = getActivity().findViewById(R.id.txtNewTopic).toString();
                        try {
                            listener.onDialogPositiveClick(NewTopicDialogFragment.this);//, newTopic);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(NewTopicDialogFragment.this);
//                        NewTopicDialogFragment.this.getDialog().cancel(); //TODO: how does this work?
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (NewTopicDialogListener) getParentFragment();
    }

    //    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        // Verify that the host activity implements the callback interface
//        try {
//            // Instantiate the NoticeDialogListener so we can send events to the host
//            listener = (NewTopicDialogListener) context;
//        } catch (ClassCastException e) {
//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(context.toString() + " must implement NoticeDialogListener");
//        }
//    }
}