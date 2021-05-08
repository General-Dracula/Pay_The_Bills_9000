package com.example.pay_the_bills_9000.ui.Syn;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pay_the_bills_9000.R;
import com.example.pay_the_bills_9000.ui.insurance.InsuranceAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import model.Syn;

public class SynAdapter extends RecyclerView.Adapter<SynAdapter.SynViewHolder>
{
    private ArrayList<Syn> syns;

    public SynAdapter(ArrayList<Syn> syns)
    {
        this.syns = syns;
    }

    @Override
    public int getItemCount() {
        return this.syns.size();
    }


    @NonNull
    @Override
    public SynViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.synitem, parent, false);
        return new SynViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull SynViewHolder synViewHolder, int position)
    {
        synViewHolder.date.setText(syns.get(position).getDate());
        synViewHolder.synShop.setText(syns.get(position).getSynShop());
        synViewHolder.valability.setText(String.valueOf(syns.get(position).getValability()) + " years");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        Calendar c2 = Calendar.getInstance();
        try {
            c2.setTime(sdf.parse(syns.get(position).getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c2.add(Calendar.YEAR, syns.get(position).getValability());
        String formattedDate2 = df.format(c2.getTime());

        if(syns.get(position).isPassed())
        {

            try {
                if (sdf.parse(formattedDate).before(c2.getTime())) {
                    synViewHolder.valid.setAlpha(1.0f);
                    synViewHolder.daysLeft.setAlpha(1.0f);
                    synViewHolder.valability.setAlpha(1.0f);
                    synViewHolder.valid.setText("Valid");
                    synViewHolder.valid.setTextColor(Color.parseColor("#14cc45"));

                    Date firstDate = sdf.parse(formattedDate);
                    Date secondDate = sdf.parse(formattedDate2);

                    assert firstDate != null;
                    assert secondDate != null;
                    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                    long difference = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);


                    synViewHolder.daysLeft.setText(difference + " days left");
                }
                else
                    {
                    synViewHolder.valid.setText("Expired");
                    synViewHolder.daysLeft.setAlpha(0.0f);
                        synViewHolder.valid.setTextColor(Color.parseColor("#ffff00"));

                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else {
            synViewHolder.valid.setAlpha(1.0f);
            synViewHolder.valid.setTextColor(Color.parseColor("#cc1414"));
            synViewHolder.valid.setText("Failed");
            synViewHolder.daysLeft.setAlpha(0.0f);
            synViewHolder.valability.setAlpha(0.0f);
        }

    }



    public class SynViewHolder extends RecyclerView.ViewHolder
    {
        TextView synShop;
        TextView date;
        TextView valability;
        TextView valid;
        TextView daysLeft;

        SynViewHolder(View itemView)
        {
            super(itemView);
            this.synShop = itemView.findViewById(R.id.synShop);
            this.date = itemView.findViewById(R.id.dateSyn);
            this.valability = itemView.findViewById(R.id.synValability);
            this.valid = itemView.findViewById(R.id.synValidText);
            this.daysLeft = itemView.findViewById(R.id.synDaysLeft);
        }
    }
}
