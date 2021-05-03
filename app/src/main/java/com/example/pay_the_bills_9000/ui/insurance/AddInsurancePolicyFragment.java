package com.example.pay_the_bills_9000.ui.insurance;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pay_the_bills_9000.MainActivity;
import com.example.pay_the_bills_9000.R;
import com.example.pay_the_bills_9000.ui.data.DataConnection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddInsurancePolicyFragment extends Fragment {

    private AddInsurancePolicyViewModel mViewModel;

    private DatePickerDialog datePicker;
    private Button buttonStart;
    private Button buttonStop;

    private TextView insurerText;
    private TextView costText;

    private  TextView policyError;

    private Button addButton;

    private Spinner spinnerCurrency;



    public static AddInsurancePolicyFragment newInstance() {
        return new AddInsurancePolicyFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_insurance_policy_fragment, container, false);

        mViewModel = new ViewModelProvider(this).get(AddInsurancePolicyViewModel.class);

       buttonStart = view.findViewById(R.id.selectStartDateButton);

        buttonStop = view.findViewById(R.id.selectStopDateButton);

        addButton = view.findViewById(R.id.addInsPolicyButton);

        insurerText = view.findViewById(R.id.addInsNameInsurer);

        costText = view.findViewById(R.id.addInsCost);

        policyError = view.findViewById(R.id.addInsError);

        spinnerCurrency = view.findViewById(R.id.spinnerCurrency);




        String[] currency = new String[]{"DKK", "EUR", "USD", "RON", "CHK"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, currency);

        spinnerCurrency.setAdapter(adapter);

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
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
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

        spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                Object item = parent.getItemAtPosition(pos);
                mViewModel.setCurrency(item.toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mViewModel.setCost(costText.getText().toString());
                mViewModel.setInsurer(insurerText.getText().toString());
                mViewModel.setStartDate(buttonStart.getText().toString());
                mViewModel.setStopDate(buttonStop.getText().toString());

                if(mViewModel.createInsurancePolicy())
                {
                    goBack();
                }
            }
        });

        mViewModel.getAddInsError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                policyError.setText(s);
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

    private void goBack()
    {
        NavHostFragment.findNavController(getParentFragment()).navigate(R.id.insurance);
    }




}