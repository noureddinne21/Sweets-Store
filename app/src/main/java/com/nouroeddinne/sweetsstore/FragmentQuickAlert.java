package com.nouroeddinne.sweetsstore;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentQuickAlert extends DialogFragment {

    private OnButtonLisner onButtonLisner;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;


    public FragmentQuickAlert() {}


    public static FragmentQuickAlert newInstance(String param1, String param2, String param3,String param4) {
        FragmentQuickAlert fragment = new FragmentQuickAlert();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonLisner){
            onButtonLisner = (OnButtonLisner) context;
        }else {
            throw new RuntimeException("Pleas Impliment listner OnButtonLisner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onButtonLisner = null;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quick_alert, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button button = view.findViewById(R.id.button_fragment_oky);
        TextView textView = view.findViewById(R.id.textView21);
        TextView textView2 = view.findViewById(R.id.textView22);
        ImageView imageView = view.findViewById(R.id.imageView5);
        LinearLayout linearLayout = view.findViewById(R.id.linear);

        textView.setText(mParam1);
        textView2.setText(mParam2);
        Log.d("TAG", "onViewCreated: "+mParam3);
        linearLayout.setBackgroundResource(Integer.parseInt(mParam3));
        imageView.setImageResource(Integer.parseInt(mParam4));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonLisner.onCklick();
                dismiss();
            }
        });



    }

    public interface OnButtonLisner{
        void onCklick();
    }



























}