package com.zen.where_is_my_money.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.zen.where_is_my_money.R;
import com.zen.where_is_my_money.Utility.Summary;

import java.util.ArrayList;


public class GraphActivity extends AppCompatActivity {

    private TextView total_amount_text;
    private double total_amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        init();
    }

    private void init(){
        PieChart pieChart = (PieChart) findViewById(R.id.chart);
        // creating data values
        ArrayList<Entry> entries = new ArrayList<>();


        for(int i = 0; i < Summary.getInstance().getManagedList().size() ; i++){
            entries.add(new Entry(Summary.getInstance().getManagedList().get(i) , i));
            total_amount += Summary.getInstance().getManagedList().get(i);
        }

        total_amount_text  = (TextView)findViewById(R.id.total_amount_text);
        total_amount_text.setText(total_amount + "");


        PieDataSet dataset = new PieDataSet(entries, "amount(baht)");

        // creating labels x-axis
        ArrayList<String> labels = Summary.getInstance().getTypeDataList();



        PieData data = new PieData(labels, dataset); // initialize Piedata

        pieChart.setData(data); //set data into chart

        pieChart.setDescription("Summary View");  // set the description

        pieChart.setCenterTextSize(20);

        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.animateY(2000);

    }
}
