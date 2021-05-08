package com.example.pay_the_bills_9000.ui.Syn;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pay_the_bills_9000.ui.data.DataConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddSynViewModel extends ViewModel
{
    private String synYears;
    private MutableLiveData<String> addSynError;
    private boolean isPassed;

    public AddSynViewModel()
    {
        addSynError = new MutableLiveData<>("");
        isPassed = false;
        synYears = "0";
    }


    public boolean createNewSyn(String shop, String date)
    {
        if(shop.equals(""))
        {
            addSynError.setValue("Invalid shop");
            return false;
        }

        if(date.equals("SELECT SYN DATE"))
        {
            addSynError.setValue("Invalid syn date");
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
                addSynError.setValue("Invalid syn date");
                return false;
            }
            //insuranceViewHolder.itemView.setBackgroundColor(Color.parseColor("#00cc99"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        DataConnection.getInstance().createNewSyn(shop, isPassed, date, Integer.valueOf(synYears));

        return true;
    }


    public LiveData<String> getAddSynError() {
        return addSynError;
    }

    public String getSynYears() {
        return synYears;
    }

    public void setSynYears(String synYears) {
        this.synYears = synYears;
    }

    public boolean isPassed() {
        return isPassed;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }
}