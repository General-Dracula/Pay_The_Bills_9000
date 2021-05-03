package com.example.pay_the_bills_9000.ui.insurance;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.pay_the_bills_9000.R;
import com.example.pay_the_bills_9000.ui.data.DataConnection;

import java.util.Calendar;

public class AddInsurancePolicyFragment extends Fragment {

    private AddInsurancePolicyViewModel mViewModel;

    private DatePickerDialog datePicker;
    private Button buttonStart;
    private Button buttonStop;

    private TextView insurerText;
    private TextView costText;

    private Button addButton;


    public static AddInsurancePolicyFragment newInstance() {
        return new AddInsurancePolicyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_insurance_policy_fragment, container, false);

       buttonStart = view.findViewById(R.id.selectStartDateButton);

        buttonStop = view.findViewById(R.id.selectStopDateButton);

        addButton = view.findViewById(R.id.addInsPolicyButton);

        insurerText = view.findViewById(R.id.addInsNameInsurer);

        costText = view.findViewById(R.id.addInsCost);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePicker = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                buttonStart.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePicker = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                buttonStop.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DataConnection.getInstance().createNewInsPolicy(insurerText.getText().toString(), buttonStart.getText().toString(), buttonStop.getText().toString(), costText.getText().toString());
            }
        });




        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddInsurancePolicyViewModel.class);
        // TODO: Use the ViewModel
    }

}