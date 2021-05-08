package com.example.pay_the_bills_9000.ui.Syn;

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
import com.example.pay_the_bills_9000.ui.insurance.InsuranceAdapter;
import com.example.pay_the_bills_9000.ui.insurance.InsuranceFragment;
import com.example.pay_the_bills_9000.ui.insurance.InsuranceViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import model.Car;

public class SynFragment extends Fragment {

    private SynViewModel synViewModel;

    RecyclerView synList;

    SynAdapter synAdapter;

    public static SynFragment newInstance(){
        return new SynFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        synViewModel =
                new ViewModelProvider(this).get(SynViewModel.class);

        View view = inflater.inflate(R.layout.fragment_syn, container, false);

        synList = view.findViewById(R.id.rvSyn);
        System.out.println("----------------" + synList);
        synList.hasFixedSize();

        FloatingActionButton fab = view.findViewById(R.id.fabAddSyn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.add_syn_action);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        synViewModel = new ViewModelProvider(this).get(SynViewModel.class);
        // TODO: Use the ViewModel
        synViewModel.getSyns().observe(getViewLifecycleOwner(), policyObserver);
    }



    Observer<Car> policyObserver = new Observer<Car>()
    {
        @Override
        public void onChanged(Car car) {
            synAdapter = new SynAdapter(car.getSyns());
            synList.setLayoutManager(new LinearLayoutManager(getContext()));
            synList.setAdapter(synAdapter);
        }

    };
}