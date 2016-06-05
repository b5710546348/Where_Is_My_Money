package com.zen.where_is_my_money.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.zen.where_is_my_money.Models.Storage;
import com.zen.where_is_my_money.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

public class MainPageActivity extends AppCompatActivity {

    private PieChart mChart;
    private Button newButton,summaryButton,editButton;
    private TextView total_expenses,today_expenses,month_expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initComponent();
        refresh();
    }

    public void initComponent(){


        //Button

        newButton = (Button)findViewById(R.id.new_button);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, NewDataActivity.class);
                startActivity(intent);
            }
        });

        summaryButton = (Button)findViewById(R.id.summary_button);
        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, SummaryOptionActivity.class);
                startActivity(intent);
            }
        });

        editButton = (Button)findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        //TextView

        total_expenses = (TextView)findViewById(R.id.total_expenses_text);
        today_expenses = (TextView)findViewById(R.id.today_expenses_text);
        month_expenses = (TextView)findViewById(R.id.month_expenses_text);

    }



    private void refresh(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String c = dateFormat.format(Calendar.getInstance().getTime());

        total_expenses.setText(Storage.getInstance().getSumExpensesValue() + "");
        today_expenses.setText(Storage.getInstance().getTodaySumExpensesValue() + "");
        month_expenses.setText(Storage.getInstance().getMonthSumExpensesValue(c.substring(0,7)) + "");
    }


}
