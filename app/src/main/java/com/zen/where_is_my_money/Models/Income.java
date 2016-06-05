package com.zen.where_is_my_money.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Zen on 6/3/16.
 */
public class Income implements Purse {

    private String type;
    private double value;
    private long createdTime;

    public Income(String type , double value){
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
        return "Income";
    }

    public String getCreatedTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(createdTime);
        return sdf.format(date);
    }

    @Override
    public String getFullInformation() {
        return getCreatedTime() + "\n Income Type : " + getType() + "\n Value : " + getValue() + " Baht.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Income income = (Income) o;

        if (Double.compare(income.value, value) != 0) return false;
        if (createdTime != income.createdTime) return false;
        return !(type != null ? !type.equals(income.type) : income.type != null);

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
