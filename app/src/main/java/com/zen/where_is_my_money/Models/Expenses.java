package com.zen.where_is_my_money.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zen on 6/3/16.
 */
public class Expenses implements Purse {

    private String type;
    private double value;
    private long createdTime;

    public Expenses(String type, double value){
        this.type = type;
        this.value = value;
        createdTime = System.currentTimeMillis();
    }


    @Override
    public void setValue(double new_value) {
        value = new_value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String new_type) {
        type = new_type;
    }

    public String getCategory(){
        return "Expenses";
    }

    public String getCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(createdTime);
        return sdf.format(date);
    }

    @Override
    public String getFullInformation() {
        return getCreatedTime() + "\n Expenses Type : " + getType() + "\n Value : " + getValue() + " Baht.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expenses expenses = (Expenses) o;

        if (Double.compare(expenses.value, value) != 0) return false;
        if (createdTime != expenses.createdTime) return false;
        return !(type != null ? !type.equals(expenses.type) : expenses.type != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type != null ? type.hashCode() : 0;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (createdTime ^ (createdTime >>> 32));
        return result;
    }


}
