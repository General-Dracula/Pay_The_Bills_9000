package com.example.pay_the_bills_9000.ui.insurance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.pay_the_bills_9000.ui.data.DataConnection;

import java.util.ArrayList;
import java.util.List;

import model.insurancePolicy;

public class InsuranceViewModel extends ViewModel {

    MutableLiveData<ArrayList<insurancePolicy>> insurancePolicies;

    ArrayList<insurancePolicy> aux;

    public InsuranceViewModel()
    {

    }

    public LiveData<ArrayList<insurancePolicy>> getPolicies()
    {
        return DataConnection.getInstance().getInsurancePolicies();
    }










}