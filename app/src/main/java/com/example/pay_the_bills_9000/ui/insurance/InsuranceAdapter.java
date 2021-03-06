package com.example.pay_the_bills_9000.ui.insurance;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pay_the_bills_9000.R;
import com.example.pay_the_bills_9000.ui.data.DataConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import model.insurancePolicy;

public class InsuranceAdapter extends RecyclerView.Adapter<InsuranceAdapter.InsuranceViewHolder>
{

    private ArrayList<insurancePolicy> insurancePolicies;


    public InsuranceAdapter(ArrayList<insurancePolicy> insurancePolicies)
    {
        this.insurancePolicies = insurancePolicies;
    }

    @Override
    public int getItemCount()
    {
        return this.insurancePolicies.size();
    }



    @NonNull
    @Override
    public InsuranceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.insuranceitem, parent, false);
        return new InsuranceViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull InsuranceViewHolder insuranceViewHolder, int position)
    {
        insuranceViewHolder.cost.setText(insurancePolicies.get(position).getAmount());
        insuranceViewHolder.insurer.setText(insurancePolicies.get(position).getInsurer());
        insuranceViewHolder.period.setText(insurancePolicies.get(position).getPeriod());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date c = Calendar.getInstance().getTime();
        //System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        try {
            if(sdf.parse(formattedDate).before(sdf.parse(insurancePolicies.get(position).getPeriod().substring(12))))
                insuranceViewHolder.valid.setAlpha(1.0f);
            else insuranceViewHolder.valid.setAlpha(0.0f);
            //insuranceViewHolder.itemView.setBackgroundColor(Color.parseColor("#00cc99"));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        insuranceViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //syns.remove(position);
                //notifyDataSetChanged();
                DataConnection.getInstance().deleteInsPolicy(insurancePolicies.get(position));
            }
        });
    }

    public class InsuranceViewHolder extends RecyclerView.ViewHolder
    {
        TextView insurer;
        TextView period;
        TextView cost;
        TextView valid;

        ImageButton deleteButton;

        InsuranceViewHolder(View itemView)
        {
            super(itemView);
            this.insurer = itemView.findViewById(R.id.insurerer);
            this.period = itemView.findViewById(R.id.period);
            this.cost = itemView.findViewById(R.id.ammount);
            this.valid = itemView.findViewById(R.id.validText);
            this.deleteButton = itemView.findViewById(R.id.deleteInsButton);
        }
    }
}
