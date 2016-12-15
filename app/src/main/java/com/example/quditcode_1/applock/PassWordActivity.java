package com.example.quditcode_1.applock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

/**
 * Created by QuditCode on 12/10/2016.
 */

public class PassWordActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Pass = "PassKey";
    SharedPreferences sharedpreferences;

    Button btn_SetPassword;
    EditText password_edit;
    String Check_pref_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_activity);
        btn_SetPassword = (Button) findViewById(R.id.setPassword);
        password_edit = (EditText) findViewById(R.id.password);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Map<String, ?> str = sharedpreferences.getAll();
        if (Check_pref_value == null) {
            for (final Map.Entry<String, ?> entry : str.entrySet()) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "Shared Preference Value:- " + entry.getValue().toString(), Toast.LENGTH_LONG).show();
            }
        }
        btn_SetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(password_edit.getText().toString())) {
                    if (password_edit.getText().toString().length() == 4) {
                        String p = password_edit.getText().toString();
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(Pass, p);
                        editor.commit();
                        Log.d("Shared Password", "Edittext Password" + p);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "Thanks...." + p, Toast.LENGTH_LONG).show();
                    } else {
                        password_edit.setError("Password should be atleast 4 number");
                    }
                } else {
                    password_edit.setError("Enter Password");
                }


            }
        });

    }
}
