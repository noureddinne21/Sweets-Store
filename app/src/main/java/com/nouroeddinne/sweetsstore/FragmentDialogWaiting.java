package com.nouroeddinne.sweetsstore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class FragmentDialogWaiting extends DialogFragment {

    private static final String TAG = "FragmentDialogWaiting";

    public static FragmentDialogWaiting newInstance() {
        return new FragmentDialogWaiting();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_waiting, container, false);
    }

    // Method to show the loading dialog
    public void showLoading() {
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        show(getParentFragmentManager(), TAG);
    }

    // Method to hide the loading dialog
    public void hideLoading() {
        dismiss();
    }

    // Example method to simulate a background task
    public void performBackgroundTask() {
        // Show loading dialog before starting background task
        showLoading();

        // Simulate background task (replace with your actual background task logic)
        new Thread(() -> {
            // Perform your background task here

            // Example: Delaying for 3 seconds to simulate a task
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Hide loading dialog after background task completes
            requireActivity().runOnUiThread(this::hideLoading);
        }).start();
    }
}























