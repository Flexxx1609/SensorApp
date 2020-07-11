package com.example.sensorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;


public class FirstFragment extends AppCompatActivity {
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            System.out.println(menuItem.getTitle());
            if (menuItem.getTitle().equals("Hund")) {

                Intent myIntent = new Intent(navigationView.getContext(), SecondFragment.class);
                // Ausführen des SecondFragment
                startActivity(myIntent);
            }

            if (menuItem.getTitle().equals("Home")) {

                Intent myIntent = new Intent(navigationView.getContext(), FirstFragment.class);
                // Ausführen des FirstFragment
                startActivity(myIntent);
            }

            if (menuItem.getTitle().equals("Sensor")) {

                Intent myIntent = new Intent(navigationView.getContext(), MainActivity.class);
                // Ausführen des MainActivity
                startActivity(myIntent);
            }


            return false;

        });


        // Graph-Knopf
        Button buttongraph = (Button) findViewById(R.id.button3);
        buttongraph.setOnClickListener(v -> {
            Intent myIntent = new Intent(v.getContext(), MainActivity.class);
            // Ausführen der Main
            startActivity(myIntent);
        });

        // Hunde-Knopf
        Button buttonhund = (Button) findViewById(R.id.button4);
        buttonhund.setOnClickListener(v -> {
            Intent myIntent = new Intent(v.getContext(), SecondFragment.class);
            // Ausführen des SecondFragment
            startActivity(myIntent);
        });


    }


}