package com.example.pay_the_bills_9000.ui.Syn;

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
import android.widget.Switch;
import android.widget.TextView;

import com.example.pay_the_bills_9000.R;

import java.util.Calendar;

public class AddSynFragment extends Fragment {

    private AddSynViewModel mViewModel;

    private TextView shopText;
    private TextView error;
    private TextView synPeriodText;
    private TextView synYearsText;

    private Button dateButton;
    private Button addButton;

    private Spinner yearsSpinner;

    private Switch passedSwitch;

    private DatePickerDialog datePicker;




    public static AddSynFragment newInstance() {
        return new AddSynFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view  = inflater.inflate(R.layout.add_syn_fragment, container, false);

        mViewModel = new ViewModelProvider(this).get(AddSynViewModel.class);

        shopText = view.findViewById(R.id.addSynShopName);
        error = view.findViewById(R.id.addSynErrorText);
        dateButton = view.findViewById(R.id.selectSynDate);
        addButton = view.findViewById(R.id.addSynButton);
        yearsSpinner = view.findViewById(R.id.synYearsSpinner);
        passedSwitch = view.findViewById(R.id.passedSynSwitch);
        synPeriodText = view.findViewById(R.id.synPeriodText);
        synYearsText = view.findViewById(R.id.synYearsText);


        synYearsText.setAlpha(0.0f);
        synPeriodText.setAlpha(0.0f);
        yearsSpinner.setAlpha(0.0f);

        String[] years = new String[]{"1", "2", "3", "5"};

        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, years);

        yearsSpinner.setAdapter(adapterSpinner);

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
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

        passedSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passedSwitch.isChecked())
                {
                    synYearsText.setAlpha(1.0f);
                    synPeriodText.setAlpha(1.0f);
                    yearsSpinner.setAlpha(1.0f);
                    mViewModel.setPassed(true);
                }
                else
                {
                    mViewModel.setPassed(false);
                    synYearsText.setAlpha(0.0f);
                    synPeriodText.setAlpha(0.0f);
                    yearsSpinner.setAlpha(0.0f);
                }
            }
        });

        yearsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                Object item = parent.getItemAtPosition(pos);
                mViewModel.setSynYears(item.toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(mViewModel.createNewSyn(shopText.getText().toString(), dateButton.getText().toString()))
                {
                    goBack();
                }
            }
        });

        mViewModel.getAddSynError().observe(getViewLifecycleOwner(), new Observer<String>() {
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
        //DataConnection.getInstance().createNewSyn("Gigel", false, "12/12/2020", 3);
        mViewModel = new ViewModelProvider(this).get(AddSynViewModel.class);
    }





    private void goBack()
    {
        NavHostFragment.findNavController(getParentFragment()).navigate(R.id.syn);
    }
}