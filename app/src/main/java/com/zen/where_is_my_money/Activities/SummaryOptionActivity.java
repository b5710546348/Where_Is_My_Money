package com.zen.where_is_my_money.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zen.where_is_my_money.Models.Storage;
import com.zen.where_is_my_money.R;
import com.zen.where_is_my_money.Utility.Mediator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SummaryOptionActivity extends AppCompatActivity {

    //component
    private RadioGroup categories;
    private Spinner month_list;
    private Button by_day_button , by_month_button,select_date_button;
    private RadioButton category;
    private TextView selected_date_text;
    private ArrayList<String> months = new ArrayList<String>();

    //data
    private int year_x = Calendar.getInstance().get(Calendar.YEAR);
    private int month_x = Calendar.getInstance().get(Calendar.MONTH);
    private int day_x = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    static final int DIALOG_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_option);
        insertMonthToList();
        showDialogOnButtonClick();
        initComponents();
    }


    private void initComponents(){
        //spinner
        month_list = (Spinner)findViewById(R.id.month_select_spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month_list.setAdapter(dataAdapter);

        //TextView
        selected_date_text = (TextView)findViewById(R.id.selected_date_text);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String requiredDate = df.format(new Date()).toString();
        selected_date_text.setText(requiredDate);


        //Radio group
        categories = (RadioGroup)findViewById(R.id.catagories_summary_radioGroup);

        //button
        by_day_button = (Button)findViewById(R.id.by_date_summary_button);
        by_day_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int select_catagories_id = categories.getCheckedRadioButtonId();
                category = (RadioButton)findViewById(select_catagories_id);

                if(category != null && selected_date_text.getText().toString() != null){
                    String date = selected_date_text.getText().toString();

                    Log.e("Date : "  , date);


                    if(category.getText().toString().equals("Income")){
                        Mediator.getInstance().storeTemporaryData(Storage.getInstance().getListBySpecific("Income", date));

                        Intent intent = new Intent(SummaryOptionActivity.this , GraphActivity.class);
                        startActivity(intent);
                    }
                    else if(category.getText().toString().equals("Expenses")){
                        Mediator.getInstance().storeTemporaryData(Storage.getInstance().getListBySpecific("Expenses", date));

                        Intent intent = new Intent(SummaryOptionActivity.this , GraphActivity.class);
                        startActivity(intent);
                    }


                }
                else{
                    Toast.makeText(SummaryOptionActivity.this, "Blank Information occur.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        by_month_button = (Button)findViewById(R.id.by_month_summary_button);
        by_month_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int select_catagories_id = categories.getCheckedRadioButtonId();
                category = (RadioButton)findViewById(select_catagories_id);

                if(category != null ){
                    String date = "2016/" + month_list.getSelectedItem().toString().substring(0,2);

                    if(category.getText().toString().equals("Income")){
                        Mediator.getInstance().storeTemporaryData(Storage.getInstance().getListBySpecific("Income" , date));
                        Mediator.getInstance().storeTypeData(Storage.getInstance().getIncomeTypeList());

                        Intent intent = new Intent(SummaryOptionActivity.this , GraphActivity.class);
                        startActivity(intent);
                    }
                    else if(category.getText().toString().equals("Expenses")){
                        Mediator.getInstance().storeTemporaryData(Storage.getInstance().getListBySpecific("Expenses", date));
                        Mediator.getInstance().storeTypeData(Storage.getInstance().getExpensesTypeList());

                        Intent intent = new Intent(SummaryOptionActivity.this , GraphActivity.class);
                        startActivity(intent);
                    }


                }
                else{
                    Toast.makeText(SummaryOptionActivity.this, "Blank Information occur.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void showDialogOnButtonClick(){
        select_date_button = (Button)findViewById(R.id.select_date_button);
        select_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID){
            return  new DatePickerDialog(this , dpickerListener , year_x , month_x , day_x);
        }
        return  null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;

            String day = "" , month = "";

            if(day_x < 10){
                day = "0" + day_x;
            }
            else{
                day = day_x+"";
            }
            if(month_x < 10){
                month = "0" + month_x;
            }
            else{
                month = month_x+"";
            }

            selected_date_text.setText(year_x + "/" + month + "/" + day);
        }
    };

    private void insertMonthToList(){
        months.clear();
        months.add("01 January"); months.add("02 February"); months.add("03 March");
        months.add("04 April");   months.add("05 May");      months.add("06 June");
        months.add("07 July");    months.add("08 August");   months.add("09 September");
        months.add("10 October"); months.add("11 November"); months.add("12 December");

    }
}
