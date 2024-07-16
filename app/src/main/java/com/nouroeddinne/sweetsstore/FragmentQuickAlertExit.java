package com.nouroeddinne.sweetsstore;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentQuickAlertExit extends DialogFragment {

    private OnButtonExitLisner onButtonExitLisner;
    private OnButtonCancelLisner onButtonCancelLisner;

    public FragmentQuickAlertExit() {}


    public static FragmentQuickAlertExit newInstance() {
        return new FragmentQuickAlertExit();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonExitLisner){
            onButtonExitLisner = (OnButtonExitLisner) context;
        }else {
            throw new RuntimeException("Pleas Impliment listner OnButtonExitLisner");
        }

        if (context instanceof OnButtonCancelLisner){
            onButtonCancelLisner = (OnButtonCancelLisner) context;
        }else {
            throw new RuntimeException("Pleas Impliment listner OnButtonCancelLisner");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onButtonExitLisner = null;
        onButtonCancelLisner = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quick_alert_exit, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonExit = view.findViewById(R.id.button_fragmentexit_exit);
        Button buttonCancel = view.findViewById(R.id.button_fragmentexit_cancel);

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonExitLisner.onCklickExit();
                dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonCancelLisner.onCklickCancel();
                dismiss();
            }
        });

    }

    public interface OnButtonExitLisner{
        void onCklickExit();
    }

    public interface OnButtonCancelLisner{
        void onCklickCancel();
    }
























}