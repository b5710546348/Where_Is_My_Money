package com.zen.where_is_my_money.Utility;

import com.zen.where_is_my_money.Models.Purse;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Zen on 6/4/16.
 */
public class Mediator  {

    private static Mediator instance;
    ArrayList<Purse> pool;
    ArrayList<String> income_type_list ;
    ArrayList<String> expenses_type_list;

    private Mediator(){
        pool = new ArrayList<>();
        income_type_list = new ArrayList<String>();
        expenses_type_list = new ArrayList<String>();
    }

    public static  Mediator getInstance(){
        if(instance == null){
            instance = new Mediator();
        }
        return  instance;

    }

    public void storeTemporaryData(ArrayList<Purse> e){
        pool = e;
    }

    public void obtainIncomeTypeData(ArrayList<String> c){
        income_type_list = c;
    }

    public void obtainExpensesTypeData(ArrayList<String> c){
        expenses_type_list = c;
    }

    public ArrayList<Purse> getPoolData(){
        return pool;
    }

    public ArrayList<String> getIncomeTypeList(){
        return  income_type_list;
    }

    public ArrayList<String> getExpensesTypeList(){
        return expenses_type_list;
    }


}
