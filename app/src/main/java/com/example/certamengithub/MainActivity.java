package com.example.certamengithub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // COMMIT 1 - Interfaz de usuario, IDs y Spinner.

    EditText txtPeso, txtEstatura;
    TextView lblResultadoIMC;
    Button btnCalcular;
    Spinner spGenero;

    ArrayList<String> generos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Variables BOTON, TEXTVIEW y EDITEXT
        txtPeso = findViewById(R.id.txtPeso);
        txtEstatura = findViewById(R.id.txtEstatura);
        btnCalcular = findViewById(R.id.btnCalcular);
        lblResultadoIMC = findViewById(R.id.lblResultadoIMC);

        // Contenido del Spinner
        generos.add("Seleccione");
        generos.add("Masculino");
        generos.add("Femenino");
        generos.add("No binario");

        spGenero = findViewById(R.id.spGenero); // Spinner

        // Configuración ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spGenero.setAdapter(adapter); // Asignamos el adapter al Spinner.


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularIMC();
            }
        });
    }

    // COMMIT 2 - Cálculo IMC

    public void calcularIMC(){

        String genero = spGenero.getSelectedItem().toString();
        String peso = txtPeso.getText().toString();
        String estatura = txtEstatura.getText().toString();

        if(peso.isEmpty() || estatura.isEmpty() || genero.equals("Seleccione")){
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
        } else {
            double pesoValor = Double.parseDouble(peso);
            double estaturaValor = Double.parseDouble(estatura)/100.0;

            double imc = pesoValor / (estaturaValor*estaturaValor);

            if(imc < 18.5){
                lblResultadoIMC.setText("Bajo peso - Delgado : "+imc);
            } else if(imc <= 24.9) {
                lblResultadoIMC.setText("Adecuado - Aceptable : "+imc);
            } else if(imc <= 29.9){
                lblResultadoIMC.setText("Sobrepeso - Sobrepeso : "+imc);
            } else if (imc <= 34.9){
                lblResultadoIMC.setText("Obesisdad grado 1 - Obesidad : "+imc);
            } else {
                lblResultadoIMC.setText("Obesidad extrema - Obesidad : "+imc);
            }
        }
    }

}