package com.example.pay_the_bills_9000.ui.green_tax;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pay_the_bills_9000.ui.data.DataConnection;

import model.Car;

public class Green_TaxViewModel extends ViewModel {


    public Green_TaxViewModel() {

    }

    public LiveData<Car> getGreenTaxes() {
        return DataConnection.getInstance().getCar();
    }
}