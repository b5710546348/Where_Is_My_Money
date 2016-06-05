package com.zen.where_is_my_money.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zen.where_is_my_money.Adapter.LogAdapter;
import com.zen.where_is_my_money.Models.Storage;
import com.zen.where_is_my_money.R;

public class EditActivity extends AppCompatActivity {

    private ListView show_log;
    private LogAdapter logAdapter;
    private Button home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initComponents();

    }

    private void initComponents(){
        show_log = (ListView)findViewById(R.id.purse_list_view);
        logAdapter = new LogAdapter(this , R.layout.log_cell , Storage.getInstance().getPurseList());
        show_log.setAdapter(logAdapter);

        home = (Button)findViewById(R.id.goto_homepage_button);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditActivity.this, MainPageActivity.class);
                startActivity(intent);
            }
        });

    }
}
