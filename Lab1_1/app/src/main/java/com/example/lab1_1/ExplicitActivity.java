package com.example.lab1_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ExplicitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);
    }

    public void confirm(View view){
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String text = editText.getText().toString();
        intent.putExtra("text", text);
        startActivity(intent);
    }

    public void cancel(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
