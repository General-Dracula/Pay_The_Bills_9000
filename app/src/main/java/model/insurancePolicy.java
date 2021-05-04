package model;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public class insurancePolicy extends LiveData<ArrayList<insurancePolicy>> {
    private String insurer;
    private String period;
    private String amount;


    public insurancePolicy(String insurer, String period, String amount)
    {
        this.insurer = insurer;
        this.amount = amount;
        this.period = period;
    }

    public insurancePolicy()
    {

    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInsurer() {
        return insurer;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
