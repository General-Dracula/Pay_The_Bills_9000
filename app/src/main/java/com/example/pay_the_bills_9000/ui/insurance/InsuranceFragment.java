package com.example.pay_the_bills_9000.ui.insurance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pay_the_bills_9000.R;

import java.util.ArrayList;
import java.util.List;

public class InsuranceFragment extends Fragment {

    private InsuranceViewModel insuranceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        insuranceViewModel =
                new ViewModelProvider(this).get(InsuranceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_insurance, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        //insuranceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            //@Override
           // public void onChanged(@Nullable String s) {
         //       textView.setText(s);
         //   }
       // }


        insuranceViewModel.getList().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                //listView.removeAllViews();
                //listView
            }
        });
        return root;
    }
}