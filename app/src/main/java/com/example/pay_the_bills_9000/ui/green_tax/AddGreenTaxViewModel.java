package com.example.pay_the_bills_9000.ui.green_tax;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pay_the_bills_9000.ui.data.DataConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddGreenTaxViewModel extends ViewModel
{
    private MutableLiveData<String> addTaxError;

    private String currency;

    private int months;

    private String date;


    public AddGreenTaxViewModel()
    {
        addTaxError = new MutableLiveData<>("");
        currency = "DKK";
        date = "";
    }


    public void setCurrency(String toString)
    {
        this.currency = currency;
    }

    public boolean createNewGreenTax(String cost)
    {

        for(int i = 0; i < cost.length(); i++)
        {
            if(Character.isLetter(cost.charAt(i)))
            {
                addTaxError.setValue("Invalid cost");
                return false;
            }
        }

        if(cost.length() == 0)
        {
            addTaxError.setValue("Invalid cost");
            return false;
        }

        if(date.equals("SELECT TAX DATE") || date.equals(""))
        {
            addTaxError.setValue("Invalid tax date");
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date c = Calendar.getInstance().getTime();
        //System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        try {
            if(sdf.parse(formattedDate).before(sdf.parse(date)))
            {
                addTaxError.setValue("Invalid tax date - Timetraveler");
                return false;
            }
            //insuranceViewHolder.itemView.setBackgroundColor(Color.parseColor("#00cc99"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DataConnection.getInstance().createNewGreenTax(date, cost + " " + currency, months);

        return true;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LiveData<String> getAddTaxError() {
        return addTaxError;
    }
}