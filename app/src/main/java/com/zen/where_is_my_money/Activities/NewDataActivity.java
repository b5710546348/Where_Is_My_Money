package com.zen.where_is_my_money.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.zen.where_is_my_money.Models.Expenses;
import com.zen.where_is_my_money.Models.Income;
import com.zen.where_is_my_money.Models.Storage;
import com.zen.where_is_my_money.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class NewDataActivity extends AppCompatActivity{

    private EditText value_text;
    private Spinner choice;
    private Button save,cancel;
    private RadioGroup catagories_selection;
    private RadioButton category;
    private String type;
    private double value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_data);

        initComponent();
    }

    private void initComponent(){


        //text
        value_text = (EditText)findViewById(R.id.value_editText);

        //radio_group
        catagories_selection = (RadioGroup)findViewById(R.id.catagories_summary_radioGroup);
        int select_categories_id = catagories_selection.getCheckedRadioButtonId();
        category = (RadioButton)findViewById(select_categories_id);

        //spinner
        choice = (Spinner) findViewById(R.id.type_selection_box);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                        Storage.getInstance().getExpensesTypeList());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choice.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();







        //button
        save = (Button)findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                type = choice.getSelectedItem().toString();


                if(type != null && value_text != null && category != null) {

                    value = Double.parseDouble(value_text.getText().toString());

                    if (category.getText().toString().equals("Expenses")) {
                        Storage.getInstance().addPurseToList(new Expenses(type, value));
                    }
                    else {
                        Storage.getInstance().addPurseToList(new Income(type, value));
                    }

                    Toast.makeText(NewDataActivity.this, "Your data have been saved.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewDataActivity.this, MainPageActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(NewDataActivity.this, "Blank information occur.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        cancel = (Button)findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewDataActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });


    }



}
