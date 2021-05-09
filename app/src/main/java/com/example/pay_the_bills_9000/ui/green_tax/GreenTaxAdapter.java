package com.example.pay_the_bills_9000.ui.green_tax;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pay_the_bills_9000.R;
import com.example.pay_the_bills_9000.ui.Syn.SynAdapter;
import com.example.pay_the_bills_9000.ui.data.DataConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import model.greenTax;

public class GreenTaxAdapter extends RecyclerView.Adapter<GreenTaxAdapter.GreenTaxViewHolder>
{
    private ArrayList<greenTax> taxes;

    public GreenTaxAdapter(ArrayList<greenTax> taxes)
    {
        this.taxes = taxes;
    }

    @NonNull
    @Override
    public GreenTaxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.taxitem, parent, false);

        return new GreenTaxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GreenTaxViewHolder holder, int position)
    {

        holder.date.setText(taxes.get(position).getDate());
        holder.cost.setText(taxes.get(position).getDate());
        if(taxes.get(position).getValability() == 1)
            holder.valability.setText(taxes.get(position).getValability() + " Month");
        else
        holder.valability.setText(taxes.get(position).getValability() + " Months");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        Calendar c2 = Calendar.getInstance();
        try {
            c2.setTime(sdf.parse(taxes.get(position).getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        c2.add(Calendar.MONTH, taxes.get(position).getValability());
        String formattedDate2 = df.format(c2.getTime());

        try {
            if (sdf.parse(formattedDate).before(c2.getTime())) {
                holder.valid.setAlpha(1.0f);
                holder.daysLeft.setAlpha(1.0f);
                holder.valability.setAlpha(1.0f);
                holder.valid.setText("Valid");
                holder.valid.setTextColor(Color.parseColor("#14cc45"));

                Date firstDate = sdf.parse(formattedDate);
                Date secondDate = sdf.parse(formattedDate2);

                assert firstDate != null;
                assert secondDate != null;
                long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                long difference = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);


                if(difference == 1)
                    holder.daysLeft.setText(difference + " day left");
                else holder.daysLeft.setText(difference + " days left");
            }
            else
            {
                holder.valid.setText("Expired");
                holder.daysLeft.setAlpha(0.0f);
                holder.valid.setTextColor(Color.parseColor("#ffff00"));

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DataConnection.getInstance().deleteGreenTax(taxes.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.taxes.size();
    }






    public class GreenTaxViewHolder extends RecyclerView.ViewHolder
    {

        TextView date;
        TextView cost;
        TextView daysLeft;
        TextView valability;
        TextView valid;

        ImageButton deleteButton;

        public GreenTaxViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.taxDate);
            cost = itemView.findViewById(R.id.taxCost);
            daysLeft = itemView.findViewById(R.id.taxDaysLeft);
            valability = itemView.findViewById(R.id.taxValability);
            valid = itemView.findViewById(R.id.taxValidText);

            deleteButton = itemView.findViewById(R.id.taxItemDeleteButton);

        }
    }
}
