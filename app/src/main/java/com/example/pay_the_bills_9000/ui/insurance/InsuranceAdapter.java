package com.example.pay_the_bills_9000.ui.insurance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pay_the_bills_9000.R;

import java.util.ArrayList;

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

    }

    public class InsuranceViewHolder extends RecyclerView.ViewHolder
    {
        TextView insurer;
        TextView period;
        TextView cost;

        InsuranceViewHolder(View itemView)
        {
            super(itemView);
            this.insurer = itemView.findViewById(R.id.insurerer);
            this.period = itemView.findViewById(R.id.period);
            this.cost = itemView.findViewById(R.id.ammount);
        }
    }
}
