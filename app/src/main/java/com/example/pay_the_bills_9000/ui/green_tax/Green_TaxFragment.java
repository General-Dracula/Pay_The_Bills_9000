package com.example.pay_the_bills_9000.ui.green_tax;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pay_the_bills_9000.R;
import com.example.pay_the_bills_9000.ui.Syn.SynAdapter;
import com.example.pay_the_bills_9000.ui.Syn.SynViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import model.Car;

public class Green_TaxFragment extends Fragment {

    private Green_TaxViewModel greenTaxViewModel;

    RecyclerView taxList;

    GreenTaxAdapter greenTaxAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        greenTaxViewModel =
                new ViewModelProvider(this).get(Green_TaxViewModel.class);
        View view = inflater.inflate(R.layout.fragment_green_tax, container, false);

        taxList = view.findViewById(R.id.rvTax);
        taxList.hasFixedSize();

        FloatingActionButton fab = view.findViewById(R.id.fabAddGreenTax);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.addGreenTaxAction);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        greenTaxViewModel = new ViewModelProvider(this).get(Green_TaxViewModel.class);
        // TODO: Use the ViewModel
        greenTaxViewModel.getGreenTaxes().observe(getViewLifecycleOwner(), policyObserver);
    }



    Observer<Car> policyObserver = new Observer<Car>()
    {
        @Override
        public void onChanged(Car car) {
            greenTaxAdapter = new GreenTaxAdapter(car.getGreenTaxes());
            taxList.setLayoutManager(new LinearLayoutManager(getContext()));
            taxList.setAdapter(greenTaxAdapter);
        }

    };

}