package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class greenTax
{
    private String ammount;
    private String date;
    private int valability;


    public greenTax(String amount, String date, int valability) {
        this.ammount = amount;
        this.date = date;
        this.valability = valability;
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

    public boolean isBefore(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if(sdf.parse(this.date).before(sdf.parse(date)))
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getValability() {
        return valability;
    }

    public void setValability(int valability) {
        this.valability = valability;
    }
}
