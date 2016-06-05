package com.zen.where_is_my_money.Models;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;

/**
 * Created by Zen on 6/3/16.
 */
public class Storage {

    private static Storage instance;
    private ArrayList<Purse> purseList;
    private ArrayList<Purse> temp;
    private ArrayList<String> income_type;
    private ArrayList<String> expenses_type;

    private Storage(){
        temp = new ArrayList<Purse>();
        purseList = new ArrayList<Purse>();
        income_type = new ArrayList<String>();
        expenses_type = new ArrayList<String>();
        addInformationType();
    }

    public static Storage getInstance(){
        if(instance == null){
            instance = new Storage();
        }
        return instance;
    }

    public ArrayList<Purse> getPurseList(){
        return  purseList;
    }

    public ArrayList<String> getIncomeTypeList(){
        return income_type;
    }

    public ArrayList<String> getExpensesTypeList(){
        return expenses_type;
    }

    public void addPurseToList(Purse purse){
        purseList.add(purse);
    }

    public void deletePurseLog(Purse purse){
        purseList.remove(purse);
    }


    public ArrayList<Purse> getListBySpecific(String category , String day_or_month){
        temp.clear();

        for(Purse p : purseList){
            if(p.getCategory().equals(category) && p.getCreatedTime().contains(day_or_month)){
                temp.add(p);
            }
        }
        return  temp;
    }



    public double getSumIncomeValue(){
        double temp = 0;
        for(Purse p : purseList){
            if(p.getCategory().equals("Income")) {
                temp += p.getValue();
            }
        }
        return temp;
    }


    public double getSumExpensesValue(){
        double temp = 0;
        for(Purse p : purseList){
            if(p.getCategory().equals("Expenses")) {
                temp += p.getValue();
            }
        }
        return temp;
    }

    public double getExpensesSumValueByType(String type){
        double temp = 0;
        for (Purse p : purseList){
            if(p.getType().equalsIgnoreCase(type)){
                temp += p.getValue();
            }
        }
        return temp;
    }

    public double getTodaySumExpensesValue(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String current_time = dateFormat.format(Calendar.getInstance().getTime());
        double temp = 0;
        for (Purse p : purseList){
            if(p.getCategory().equals("Expenses") && p.getCreatedTime().contains(current_time.substring(0,10))){
                Log.e("Enter" , " today value");
                temp += p.getValue();
            }
        }
        return temp;
    }

    public double getMonthSumExpensesValue(String month_number){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String current_time = dateFormat.format(Calendar.getInstance().getTime());
        double temp = 0;
        for (Purse p : purseList){
            if(p.getCategory().equals("Expenses") && p.getCreatedTime().substring(0, 7).equals(month_number)){
                temp += p.getValue();
            }
        }
        return temp;
    }

    private  void addInformationType(){
        expenses_type.add("Travel");
        expenses_type.add("Food");
        expenses_type.add("Study");
        expenses_type.add("Rental");
        expenses_type.add("Home Stuff");
        expenses_type.add("Technology");
        expenses_type.add("Others");

        income_type.add("Salary");
        income_type.add("Scholarships");
        income_type.add("Interest");
        income_type.add("Bonus");
        income_type.add("Others");
    }

}
