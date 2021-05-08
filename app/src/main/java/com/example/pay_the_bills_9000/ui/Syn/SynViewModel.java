package com.example.pay_the_bills_9000.ui.Syn;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pay_the_bills_9000.ui.data.DataConnection;

import model.Car;

public class SynViewModel extends ViewModel {



    public SynViewModel() {

    }

    public LiveData<Car> getSyns()
    {
        return DataConnection.getInstance().getCar();
    }
}