package com.example.pay_the_bills_9000.ui.Syn;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SynViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SynViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is syn fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}