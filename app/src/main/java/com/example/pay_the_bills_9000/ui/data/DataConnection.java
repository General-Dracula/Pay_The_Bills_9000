package com.example.pay_the_bills_9000.ui.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import model.Car;
import model.Syn;
import model.greenTax;
import model.insurancePolicy;

public class DataConnection
{
  private MutableLiveData<Car> carMutableLiveData;

  private MutableLiveData<ArrayList<insurancePolicy>> insurancePolicies;

  private String licencePlate;

  private static DataConnection instance;

    private static Object lock = new Object();

  private DataConnection()
  {
      carMutableLiveData = new MutableLiveData<Car>();

      carMutableLiveData.setValue(this.testingCar());

      insurancePolicies = new MutableLiveData<ArrayList<insurancePolicy>>();

      insurancePolicies.setValue(carMutableLiveData.getValue().getInsurancePolicies());



  }

  private Car testingCar()
  {
      ArrayList<insurancePolicy> aux = new ArrayList<insurancePolicy>();
      aux.add(new insurancePolicy("Gigel Frone SRL", "12.01.2020 - 12.01.2021", "1234 DKK"));
      aux.add(new insurancePolicy("Gigel  SRL", "12.01.2020 - 12.01.2021", "32524 DKK"));
      aux.add(new insurancePolicy("George Frone SRL", "12.01.2020 - 12.01.2021", "34543 DKK"));
      aux.add(new insurancePolicy("Frone SRL", "12.01.2020 - 12.01.2021", "12453 DKK"));

      ArrayList<Syn> auxSyn = new ArrayList<Syn>();
      auxSyn.add(new Syn("Gigel SRL", true, "12.12.2020", 2));
      auxSyn.add(new Syn("Gigel SRL", true, "12.12.2020", 2));
      auxSyn.add(new Syn("Gigel SRL", true, "12.12.2020", 2));
      auxSyn.add(new Syn("Gigel SRL", true, "12.12.2020", 2));
      auxSyn.add(new Syn("Gigel SRL", true, "12.12.2020", 2));

      ArrayList<greenTax> auxTax = new ArrayList<greenTax>();
      auxTax.add(new greenTax("2432", "12.12.2000"));
      auxTax.add(new greenTax("2432", "12.12.2000"));
      auxTax.add(new greenTax("2432", "12.12.2000"));
      auxTax.add(new greenTax("2432", "12.12.2000"));

      return new Car(licencePlate, aux, auxSyn, auxTax);
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

  public void login(String licencePlate)
  {
      this.licencePlate = licencePlate;
  }

  public String getLicencePlate()
  {
      return this.licencePlate;
  }

  public LiveData<ArrayList<insurancePolicy>> getInsurancePolicies()
  {
      return this.insurancePolicies;
  }

  public void createNewInsPolicy(String insurer, String startDate, String stopDate, String cost)
  {
      insurancePolicies.getValue().add(0, new insurancePolicy(insurer, startDate + " - " + stopDate, cost));
  }





}
