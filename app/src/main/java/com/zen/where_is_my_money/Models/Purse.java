package com.zen.where_is_my_money.Models;

/**
 * Created by Zen on 6/3/16.
 */
public interface Purse {

    public void setValue(double new_value);
    public double getValue();
    public String getType();
    public void setType(String new_type);
    public String getCategory();
    public String getCreatedTime();
    public String getFullInformation();


}
