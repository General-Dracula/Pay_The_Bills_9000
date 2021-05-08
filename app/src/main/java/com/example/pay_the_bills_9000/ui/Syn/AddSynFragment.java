package com.example.pay_the_bills_9000.ui.Syn;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pay_the_bills_9000.R;
import com.example.pay_the_bills_9000.ui.data.DataConnection;

import model.Syn;

public class AddSynFragment extends Fragment {

    private AddSynViewModel mViewModel;

    public static AddSynFragment newInstance() {
        return new AddSynFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_syn_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataConnection.getInstance().createNewSyn("Gigel", false, "12/12/2020", 3);

        mViewModel = new ViewModelProvider(this).get(AddSynViewModel.class);
        // TODO: Use the ViewModel
    }

}