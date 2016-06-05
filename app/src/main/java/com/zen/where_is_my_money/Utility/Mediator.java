package com.zen.where_is_my_money.Utility;

import com.zen.where_is_my_money.Models.Purse;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Zen on 6/4/16.
 */
public class Mediator  {

    private static Mediator instance;
    private ArrayList<Purse> pool;
    private ArrayList<String> type_list ;
    private Purse temp_purse;
    private int hash_code;


    private Mediator(){
        pool = new ArrayList<>();
        type_list = new ArrayList<>();
    }

    public static  Mediator getInstance(){
        if(instance == null){
            instance = new Mediator();
        }
        return  instance;

    }

    public void storeHashCode(int h){
        hash_code = h;
    }

    public int getHashCodeOfPurse(){
        return  hash_code;
    }

    public void storePurse(Purse p){
        temp_purse = p;
    }

    public  Purse getPurse(){
        return temp_purse;
    }

    public void storeTemporaryData(ArrayList<Purse> e){
        pool = e;
    }

    public ArrayList<Purse> getPoolData(){
        return pool;
    }

    public void storeTypeData(ArrayList<String> e){
        type_list = e;
    }

    public ArrayList<String> getTypeDataList(){
        return type_list;
    }



    public ArrayList<Float> getManagedList(){

        float [] temp  = new float[type_list.size()];

        for(Purse p : pool){
            for(int i = 0; i < type_list.size() ; i++){
                if(p.getType().equals(type_list.get(i))){
                    temp[i] += p.getValue();
                }
            }
        }

        ArrayList<Float> values = new ArrayList<Float>();
        for(float c : temp){
            values.add(c);
        }

        return  values;

    }




}
