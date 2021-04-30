package com.example.pay_the_bills_9000.ui.insurance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class InsuranceViewModel extends ViewModel {

    //private MutableLiveData<String> mText;

    private MutableLiveData<ArrayList<String>> insuranceList;

    public InsuranceViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is an insurance fragment");

        insuranceList = new MutableLiveData<ArrayList<String>>();
    }

    //public LiveData<String> getText() {
        //return mText;
   // }

    public LiveData<ArrayList<String>> getList()
    {
        return this.insuranceList;
    }
}