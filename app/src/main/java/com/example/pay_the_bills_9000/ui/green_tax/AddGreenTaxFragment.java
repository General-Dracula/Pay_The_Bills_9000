package com.example.pay_the_bills_9000.ui.green_tax;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
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

import com.example.pay_the_bills_9000.R;

import java.util.Calendar;

public class AddGreenTaxFragment extends Fragment {

    private AddGreenTaxViewModel mViewModel;

    private TextView taxCost;
    private TextView error;

    private Spinner currencySpinner;
    private Spinner monthsSpinner;

    private Button dateButton;
    private Button addButton;

    private DatePickerDialog datePicker;


    public static AddGreenTaxFragment newInstance() {
        return new AddGreenTaxFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_green_tax_fragment, container, false);

        //DataConnection.getInstance().createNewGreenTax("12/12/2020", "2342 DKK", 6);

        mViewModel = new ViewModelProvider(this).get(AddGreenTaxViewModel.class);

        taxCost = view.findViewById(R.id.addTaxCost);
        error = view.findViewById(R.id.addTaxError);

        currencySpinner = view.findViewById(R.id.spinnerCurrencyAddTax);
        monthsSpinner = view.findViewById(R.id.spinnerAddTaxMonths);

        dateButton = view.findViewById(R.id.selectTaxDate);
        addButton = view.findViewById(R.id.addTaxButton);

        String[] months = new String[]{"1", "2", "3", "6", "120"};

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, months);

        monthsSpinner.setAdapter(adapterSpinner);

        String[] currency = new String[]{"DKK", "EUR", "USD", "RON", "CHK"};

        ArrayAdapter<String> adapterSpinner2 = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, currency);

        currencySpinner.setAdapter(adapterSpinner2);

        dateButton.setOnClickListener(new View.OnClickListener() {
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
                                dateButton.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                mViewModel.setDate(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                Object item = parent.getItemAtPosition(pos);
                mViewModel.setCurrency(item.toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        monthsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                Object item = parent.getItemAtPosition(pos);
                mViewModel.setMonths(Integer.valueOf(item.toString()));
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewModel.createNewGreenTax(taxCost.getText().toString()))
                {
                    mViewModel.setCurrency(currencySpinner.toString());
                    goBack();
                }
            }
        });

        mViewModel.getAddTaxError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                error.setText(s);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddGreenTaxViewModel.class);
    }

    private void goBack()
    {
        NavHostFragment.findNavController(getParentFragment()).navigate(R.id.green_tax);
    }

}