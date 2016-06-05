package com.zen.where_is_my_money.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zen.where_is_my_money.Models.Purse;
import com.zen.where_is_my_money.Models.Storage;
import com.zen.where_is_my_money.R;
import com.zen.where_is_my_money.Utility.Summary;

public class EditDataActivity extends AppCompatActivity {

    private EditText edit_value_text;
    private Button save,cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        initComponents();
    }

    private  void initComponents(){
        edit_value_text = (EditText)findViewById(R.id.new_value_text);

        save = (Button)findViewById(R.id.save_edit_button);
        cancel = (Button)findViewById(R.id.cancel_edit_button);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_value_text != null){
                    double new_value = Double.parseDouble(edit_value_text.getText().toString());

                    for(Purse p  : Storage.getInstance().getPurseList()){
                        if(Summary.getInstance().getHashCodeOfPurse() == p.hashCode()){
                            p.setValue(new_value);
                        }
                    }

                    Toast.makeText(EditDataActivity.this, "Your new data have been saved.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditDataActivity.this, EditActivity.class);
                    startActivity(intent);

                }

                else{
                    Toast.makeText(EditDataActivity.this, "Blank information occur", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditDataActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
    }
}
