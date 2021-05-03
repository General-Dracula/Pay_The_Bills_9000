package com.example.pay_the_bills_9000.ui.insurance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pay_the_bills_9000.ui.data.DataConnection;

public class AddInsurancePolicyViewModel extends ViewModel
{
    private String insurer;
    private String cost;

    private String startDate;
    private String stopDate;

    private String currency;

    private MutableLiveData<String> addInsError;

    public AddInsurancePolicyViewModel()
    {
        addInsError = new MutableLiveData<>("");
    }

    public boolean createInsurancePolicy()
    {
       if(insurer.equals(""))
       {
           addInsError.setValue("Invalid insurer");
           return false;
       }


        if(startDate.equals("Select start date"))
        {
            addInsError.setValue("Invalid start date");
            return false;
        }

        if(stopDate.equals("Select stop date"))
        {
            addInsError.setValue("Invalid stop date");
            return false;
        }

        for(int i = 0; i < cost.length(); i++)
        {
            if(Character.isLetter(cost.charAt(i)))
            {
                addInsError.setValue("Invalid cost");
                return false;
            }
        }

        if(cost.equals(""))
        {
            addInsError.setValue("Invalid cost");
            return false;
        }

        addInsError.setValue("");
        DataConnection.getInstance().createNewInsPolicy(insurer, startDate, stopDate, cost + " " + currency);
        return true;
    }


    public LiveData<String> getAddInsError() {
        return addInsError;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}