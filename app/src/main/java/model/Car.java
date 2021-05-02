package model;

import java.util.ArrayList;

public class Car
{
    private String licencePlate;

    private ArrayList<insurancePolicy> insurancePolicies;
    private ArrayList<Syn> syns;
    private ArrayList<greenTax> greenTaxes;

    public Car(String licencePlate, ArrayList<insurancePolicy> insurancePolicies, ArrayList<Syn> syns, ArrayList<greenTax> greenTaxes) {
        this.licencePlate = licencePlate;
        this.insurancePolicies = insurancePolicies;
        this.syns = syns;
        this.greenTaxes = greenTaxes;
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
