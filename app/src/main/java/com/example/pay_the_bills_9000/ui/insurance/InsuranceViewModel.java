package com.example.pay_the_bills_9000.ui.insurance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.pay_the_bills_9000.ui.data.DataConnection;

import model.Car;


public class InsuranceViewModel extends ViewModel {



    public InsuranceViewModel()
    {

    }

    public LiveData<Car> getPolicies()
    {
        return DataConnection.getInstance().getCar();
    }

}