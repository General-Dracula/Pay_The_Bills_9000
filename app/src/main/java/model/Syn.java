package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Syn
{
    private String synShop;
    private boolean passed;
    private String date;
    private int valability;

    public Syn(String synShop, boolean passed, String date, int valability) {
        this.synShop = synShop;
        this.passed = passed;
        this.date = date;
        this.valability = valability;
    }

    public Syn()
    {

    }


    public String getSynShop() {
        return synShop;
    }

    public void setSynShop(String synShop) {
        this.synShop = synShop;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getValability() {
        return valability;
    }

    public void setValability(int valability) {
        this.valability = valability;
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

}
