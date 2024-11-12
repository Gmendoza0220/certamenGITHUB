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

    // ENLACE A REPOSITORIO GITHUB
    // https://github.com/Gmendoza0220/certamenGITHUB

    // COMMIT 1 - Interfaz de usuario, IDs y Spinner.

    EditText txtPeso, txtEstatura;
    TextView lblResultadoIMC, lblSugerencias;
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
        lblSugerencias = findViewById(R.id.lblSugerencias);


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

        // Se extraen los datos de la interfaz
        String genero = spGenero.getSelectedItem().toString();
        String peso = txtPeso.getText().toString();
        String estatura = txtEstatura.getText().toString();

        // Se validan los campos
        if(peso.isEmpty() || estatura.isEmpty() || genero.equals("Seleccione")){
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
        } else {
            // Se parsean a Double los valores extraídos de la interfaz
            double pesoValor = Double.parseDouble(peso);
            double estaturaValor = Double.parseDouble(estatura)/100.0;

            // Se calcula el IMC
            double imcValor = pesoValor / (estaturaValor*estaturaValor);

            String imc = ""+imcValor;
            imc = imc.substring(0, 5);

            if(imcValor < 18.5){ // Bajo peso
                lblResultadoIMC.setText("Bajo peso - Delgado : "+imc);
                bajopeso();
            } else if(imcValor <= 24.9) { // Aceptable
                lblResultadoIMC.setText("Adecuado - Aceptable : "+imc);
                pesoNormal();
            } else if(imcValor <= 29.9){ // Sobrepeso
                lblResultadoIMC.setText("Sobrepeso - Sobrepeso : "+imc);
                sobrepeso_Obeso();
            } else if (imcValor <= 34.9){ // Obesidad
                lblResultadoIMC.setText("Obesisdad grado 1 - Obesidad : "+imc);
                sobrepeso_Obeso();
            } else { // Obesidad grado extremoooo
                lblResultadoIMC.setText("Obesidad extrema - Obesidad : "+imc);
                sobrepeso_Obeso();
            }
        }
    }

    // COMMIT 3 - Métodos de sugerencias + últimos detalles

    public void sobrepeso_Obeso(){
        lblSugerencias.setText("" +
                "1) Haga 30 minutos de ejercicio aróbico 5 veces por semana \n" +
                "2) Logre un equilibrio entre el consumo de calorías y la actividad física \n" +
                "3) Limite las grasas saturadas a no mas del 7% de las calorías totales \n" +
                "4) Tenga una alimentación baja en colesterol con carnes magras, frutas, verduras y cereales");
    }

    public void bajopeso(){
        lblSugerencias.setText("" +
                "1) Come con más frecuencia. Empieza poco a poco a hacer 5 a 6 comidas más pequeñas durante el día \n" +
                "2) Elige alimentos con muchos nutrientes \n" +
                "3) Agrega aderezos \n" +
                "4) Prueba licuados y batisos de frutas \n" +
                "5) Controla que bebes y cuando \n" +
                "6) Haz ejercicio");
    }

    public void pesoNormal(){
        lblSugerencias.setText("Felicitaciones ud mantiene una excelente alimentación");
    }

}