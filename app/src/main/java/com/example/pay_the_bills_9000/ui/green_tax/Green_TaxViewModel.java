package com.example.pay_the_bills_9000.ui.green_tax;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Green_TaxViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Green_TaxViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is green tax fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}