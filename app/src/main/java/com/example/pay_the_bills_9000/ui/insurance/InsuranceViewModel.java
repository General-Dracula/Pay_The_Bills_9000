package com.example.pay_the_bills_9000.ui.insurance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.util.ArrayList;
import java.util.List;

import model.insurancePolicy;

public class InsuranceViewModel extends ViewModel {

    MutableLiveData<ArrayList<insurancePolicy>> insurancePolicies;

    ArrayList<insurancePolicy> aux;

    public InsuranceViewModel()
    {
      this.insurancePolicies = new MutableLiveData<ArrayList<insurancePolicy>>();
      aux = new ArrayList<insurancePolicy>();
        aux.add(new insurancePolicy("Gigel Frone SRL", "12.01.2020", "1234 DKK"));
        aux.add(new insurancePolicy("Gigel  SRL", "12.01.2020", "32524 DKK"));
        aux.add(new insurancePolicy("George Frone SRL", "12.01.2020", "34543 DKK"));
        aux.add(new insurancePolicy("Frone SRL", "12.01.2020", "12453 DKK"));

        insurancePolicies.setValue(aux);


        try {
            for(int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                aux.add(0, new insurancePolicy(String.valueOf(i), "12.01.2020", "666 DKK"));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }






    }

    public LiveData<ArrayList<insurancePolicy>> getPolicies()
    {
        return insurancePolicies;
    }










}