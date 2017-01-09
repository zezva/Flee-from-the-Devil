package com.example.zezva.ontouch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class testActivity extends AppCompatActivity  implements  View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button but = (Button) findViewById(R.id.testtesttest);
        but.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

    }
}
