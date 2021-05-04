package com.example.pay_the_bills_9000.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pay_the_bills_9000.MainActivity;
import com.example.pay_the_bills_9000.R;
import com.example.pay_the_bills_9000.ui.data.DataConnection;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textView = findViewById(R.id.licencePlateInput);
        TextView errorText = findViewById(R.id.invalidLicenceText);
        errorText.setAlpha(0.0f);

        textView.setText(getLastPlate());



        Button button = findViewById(R.id.logInButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(isPlateValid(String.valueOf(textView.getText())))
                {
                    String auxPlate = (String.valueOf(textView.getText()));
                    auxPlate = auxPlate.toUpperCase();
                    DataConnection.getInstance().login(String.valueOf(auxPlate));
                    saveLastPlate(auxPlate);
                    goToMain();
                }
                else {
                    errorText.setAlpha(1.0f);
                    textView.setText("");
                }
            }
            });

    }

    private boolean isPlateValid(String plate)
    {
        System.out.println("---CHECKING LICENCE PLATE  " + plate);

        if(plate.equals("69"))
            return true;


        if(plate.length() != 9)
            return false;

        if(Character.isDigit(plate.charAt(0)))
            return false;
        if(Character.isDigit(plate.charAt(1)))
            return false;

        if(plate.charAt(2) != ' ')
            return false;

        if(Character.isLetter(plate.charAt(3)))
            return false;
        if(Character.isLetter(plate.charAt(4)))
            return false;

        if(plate.charAt(5) != ' ')
            return false;

        if(Character.isLetter(plate.charAt(6)))
            return false;
        if(Character.isLetter(plate.charAt(7)))
            return false;
        if(Character.isLetter(plate.charAt(8)))
            return false;
        return true;
    }

    private void saveLastPlate(String plate)
    {
        SharedPreferences prefs = getSharedPreferences("LastPlate", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("This_Is_An_Easter_Egg_Muie_PSD", plate);
        editor.apply();
    }

    private String getLastPlate()
    {
        SharedPreferences prefs = getSharedPreferences("LastPlate", MODE_PRIVATE);
        String plate = prefs.getString("This_Is_An_Easter_Egg_Muie_PSD", "");
        return plate;
    }



    private void goToMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
        finish();
    }


}