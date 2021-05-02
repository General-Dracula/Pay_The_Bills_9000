package com.example.pay_the_bills_9000.ui.insurance;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pay_the_bills_9000.R;

public class AddInsurancePolicyFragment extends Fragment {

    private AddInsurancePolicyViewModel mViewModel;

    public static AddInsurancePolicyFragment newInstance() {
        return new AddInsurancePolicyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_insurance_policy_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddInsurancePolicyViewModel.class);
        // TODO: Use the ViewModel
    }

}