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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pay_the_bills_9000.R;

import java.util.ArrayList;
import java.util.List;

import model.insurancePolicy;

public class InsuranceFragment extends Fragment {

    private InsuranceViewModel insuranceViewModel;

    RecyclerView insPoliciesList;

    InsuranceAdapter insuranceAdapter;


    public static InsuranceFragment newInstance(){
        return new InsuranceFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        insuranceViewModel =
                new ViewModelProvider(this).get(InsuranceViewModel.class);

        View view = inflater.inflate(R.layout.fragment_insurance, container, false);

        insPoliciesList = view.findViewById(R.id.rv);
        System.out.println("----------------" + insPoliciesList);
        insPoliciesList.hasFixedSize();
        //insPoliciesList.setLayoutManager(new LinearLayoutManager(this));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        insuranceViewModel = new ViewModelProvider(this).get(InsuranceViewModel.class);
        // TODO: Use the ViewModel
        insuranceViewModel.getPolicies().observe(getViewLifecycleOwner(), policyObserver);
    }


    Observer<ArrayList<insurancePolicy>> policyObserver = new Observer<ArrayList<insurancePolicy>>()
    {
        @Override
        public void onChanged(ArrayList<insurancePolicy> insurancePolicies)
        {
            InsuranceAdapter insuranceAdapter = new InsuranceAdapter(insurancePolicies);
            insPoliciesList.setLayoutManager(new LinearLayoutManager(getContext()));
            insPoliciesList.setAdapter(insuranceAdapter);
        }
    };
}