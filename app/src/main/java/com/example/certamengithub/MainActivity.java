package com.example.certamengithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtPeso, txtEstatura;
    TextView lblResultadoIMC;
    Button btnCalcular;
    Spinner spGenero;

    ArrayList<String> generos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        generos.add("Seleccione un género");
        generos.add("Masculino");
        generos.add("Femenino");
        generos.add("No binario");

        spGenero = findViewById(R.id.spGenero);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spGenero.setAdapter(adapter);


    }
}