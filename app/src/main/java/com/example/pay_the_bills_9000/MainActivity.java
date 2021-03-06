package com.example.pay_the_bills_9000;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.pay_the_bills_9000.ui.data.DataConnection;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import model.Car;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private TextView licenseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.insurance, R.id.syn, R.id.green_tax)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /*

        licenseText = findViewById(R.id.menuLicencePlate);

        DataConnection.getInstance().getLicencePlateLive().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                licenseText.setText(s);
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        licenseText = findViewById(R.id.menuLicencePlate);

        DataConnection.getInstance().getCar().observe(this, new Observer<Car>() {
            @Override
            public void onChanged(Car car) {
                licenseText.setText(car.getLicencePlate());
            }
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}