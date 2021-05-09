package com.example.pay_the_bills_9000.ui.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import model.Car;
import model.Syn;
import model.greenTax;
import model.insurancePolicy;

public class DataConnection {
    private MutableLiveData<Car> carMutableLiveData;


    FirebaseDatabase database;
    DatabaseReference myRef;

    private static DataConnection instance;

    private static Object lock = new Object();

    private DataConnection() {
        database = FirebaseDatabase.getInstance("https://pay-the-billls-9000-default-rtdb.europe-west1.firebasedatabase.app");

        database.setLogLevel(Logger.Level.DEBUG);

        myRef = database.getReference();

        carMutableLiveData = new MutableLiveData<Car>(createEmptyCar(""));

    }

    private void findCar(String plate) {
        System.out.println("-----------------------------------69696");
        myRef.child(plate).get().addOnCompleteListener(task -> {

            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
                System.out.println("-----------------------------------NO SUCCESS");
            } else {
                Log.d("firebase", String.valueOf(task.getResult()));
                if (task.getResult().getValue() == null) {
                    System.out.println("-----------------------------------NOT FOUND");
                    myRef.child(plate).setValue(this.createEmptyCar(plate)).addOnCompleteListener(task1 -> Log.d("------firebase", "works i hope"));
                }

//                  this.carMutableLiveData.setValue(this.createEmptyCar(plate));
            }

        });

        myRef.child(plate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue(Car.class) == null)
                    carMutableLiveData.setValue(createEmptyCar(plate));
                else
                    carMutableLiveData.setValue(snapshot.getValue(Car.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private Car createEmptyCar(String plate) {
        ArrayList<insurancePolicy> aux = new ArrayList<insurancePolicy>();


        ArrayList<Syn> auxSyn = new ArrayList<Syn>();


        ArrayList<greenTax> auxTax = new ArrayList<greenTax>();


        return new Car(plate, aux, auxSyn, auxTax);
    }

    public static DataConnection getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DataConnection();
                }
            }
        }
        return instance;
    }

    public void login(String licencePlate) {

        findCar(licencePlate);

        //this.carMutableLiveData.getValue().setLicencePlate(licencePlate);
    }

    public String getLicencePlate() {
        return this.carMutableLiveData.getValue().getLicencePlate();
    }

    public LiveData<Car> getCar() {
        return this.carMutableLiveData;
    }

    public void createNewInsPolicy(String insurer, String startDate, String stopDate, String cost) {
        carMutableLiveData.getValue().getInsurancePolicies().add(0, new insurancePolicy(insurer, startDate + " - " + stopDate, cost));

        myRef.child(this.carMutableLiveData.getValue().getLicencePlate()).setValue(this.carMutableLiveData.getValue()).addOnCompleteListener(task1 -> Log.d("firebase", "MERE"));
    }

    public void createNewSyn(String synShop, boolean passed, String date, int valability) {
        carMutableLiveData.getValue().getSyns().add(0, new Syn(synShop, passed, date, valability));
        carMutableLiveData.getValue().sortSyns();
        myRef.child(this.carMutableLiveData.getValue().getLicencePlate()).setValue(this.carMutableLiveData.getValue()).addOnCompleteListener(task1 -> Log.d("firebase", "MERE"));
    }

    public void createNewGreenTax(String date, String cost, int valability) {
        carMutableLiveData.getValue().getGreenTaxes().add(0, new greenTax(cost, date, valability));
        carMutableLiveData.getValue().sortGreenTaxes();
        myRef.child(this.carMutableLiveData.getValue().getLicencePlate()).setValue(this.carMutableLiveData.getValue()).addOnCompleteListener(task1 -> Log.d("firebase", "MERE"));
    }


    public void deleteSyn(Syn toBeDeleted) {
        carMutableLiveData.getValue().getSyns().remove(toBeDeleted);
        myRef.child(this.carMutableLiveData.getValue().getLicencePlate()).setValue(this.carMutableLiveData.getValue()).addOnCompleteListener(task1 -> Log.d("firebase", "MERE"));
    }

    public void deleteInsPolicy(insurancePolicy toBeDeleted) {
        carMutableLiveData.getValue().getInsurancePolicies().remove(toBeDeleted);
        myRef.child(this.carMutableLiveData.getValue().getLicencePlate()).setValue(this.carMutableLiveData.getValue()).addOnCompleteListener(task1 -> Log.d("firebase", "MERE"));
    }

    public void deleteGreenTax(greenTax toBeDeleted) {
        carMutableLiveData.getValue().getGreenTaxes().remove(toBeDeleted);
        myRef.child(this.carMutableLiveData.getValue().getLicencePlate()).setValue(this.carMutableLiveData.getValue()).addOnCompleteListener(task1 -> Log.d("firebase", "MERE"));
    }

    private Car testingCar() {
        ArrayList<insurancePolicy> aux = new ArrayList<insurancePolicy>();
        aux.add(new insurancePolicy("Gigel Frone SRL", "12/01/2020 - 12/01/2021", "1234 DKK"));
        aux.add(new insurancePolicy("Gigel  SRL", "12/01/2020 - 12/01/2021", "32524 DKK"));
        aux.add(new insurancePolicy("George Frone SRL", "12/01/2020 - 12/01/2021", "34543 DKK"));
        aux.add(new insurancePolicy("Frone SRL", "12/01/2020 - 12/01/2022", "12453 DKK"));

        ArrayList<Syn> auxSyn = new ArrayList<Syn>();
        auxSyn.add(new Syn("Gigel SRL", true, "12.12.2020", 2));
        auxSyn.add(new Syn("Gigel SRL", true, "12.12.2020", 2));
        auxSyn.add(new Syn("Gigel SRL", true, "12.12.2020", 2));
        auxSyn.add(new Syn("Gigel SRL", true, "12.12.2021", 2));
        auxSyn.add(new Syn("Gigel SRL", true, "12.12.2020", 2));

        ArrayList<greenTax> auxTax = new ArrayList<greenTax>();
        auxTax.add(new greenTax("2432", "12.12.2000", 1));
        auxTax.add(new greenTax("2432", "12.12.2000", 1));
        auxTax.add(new greenTax("2432", "12.12.2000", 1));
        auxTax.add(new greenTax("2432", "12.12.2000", 1));

        return new Car(carMutableLiveData.getValue().getLicencePlate(), aux, auxSyn, auxTax);
    }


}
