package com.amrapps.paneraautomate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by alex on 11/6/15.
 */
public class firstRun extends AppCompatActivity {
    EditText name;
    EditText lastName;
    EditText password;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);

        prefs = getSharedPreferences("com.amrapps.paneraautomate", MODE_PRIVATE);

        name = (EditText) findViewById(R.id.name);
        lastName = (EditText) findViewById(R.id.lastName);
        password = (EditText) findViewById(R.id.password);
        final CheckBox passwordReveal = (CheckBox) findViewById
                (R.id.checkbox);

        passwordReveal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    password.setInputType(129);
                }
            }
        });
    }

    public void continueButton(View v) {
        String stringName = name.getText().toString();
        String stringLastName = lastName.getText().toString();
        String stringPassword = password.getText().toString();

        SharedPreferences.Editor editor = prefs.edit();
        prefs.edit().putString("name", stringName).commit();
        prefs.edit().putString("lastName", stringLastName).commit();
        prefs.edit().putString("password", stringPassword);
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

