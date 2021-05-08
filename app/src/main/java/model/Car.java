package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class Car
{
    private String licencePlate;

    private ArrayList<insurancePolicy> insurancePolicies;
    private ArrayList<Syn> syns;
    private ArrayList<greenTax> greenTaxes;

    public Car(String licencePlate, ArrayList<insurancePolicy> insurancePolicies, ArrayList<Syn> syns, ArrayList<greenTax> greenTaxes)
    {
        this.licencePlate = licencePlate;
        this.insurancePolicies = insurancePolicies;
        this.syns = syns;
        this.greenTaxes = greenTaxes;
    }

    public Car()
    {
        insurancePolicies = new ArrayList<insurancePolicy>();
        syns = new ArrayList<Syn>();
        greenTaxes = new ArrayList<greenTax>();
    }

    public void sortArrays()
    {
        sortSyns();
    }

    public void sortSyns()
    {
        for(int i = 0; i < this.syns.size(); i++)
        {
            for(int j = i + 1; j < this.syns.size(); j++)
            {
                if(this.syns.get(i).isBefore(this.syns.get(j).getDate()))
                {
                    Collections.swap(this.syns, i, j);
                }
            }
        }

    }



    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public ArrayList<insurancePolicy> getInsurancePolicies() {
        return insurancePolicies;
    }

    public void setInsurancePolicies(ArrayList<insurancePolicy> insurancePolicies) {
        this.insurancePolicies = insurancePolicies;
    }

    public ArrayList<Syn> getSyns() {
        return syns;
    }

    public void setSyns(ArrayList<Syn> syns) {
        this.syns = syns;
    }

    public ArrayList<greenTax> getGreenTaxes() {
        return greenTaxes;
    }

    public void setGreenTaxes(ArrayList<greenTax> greenTaxes) {
        this.greenTaxes = greenTaxes;
    }
}
