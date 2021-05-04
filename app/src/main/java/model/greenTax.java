package model;

public class greenTax
{
    private String ammount;
    private String date;


    public greenTax(String ammount, String date) {
        this.ammount = ammount;
        this.date = date;
    }

    public greenTax()
    {

    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
