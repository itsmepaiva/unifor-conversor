package com.exemplo.conversorunidades;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] unidades = {"Centímetros", "Metros", "Quilômetros", "Milhas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputValue = findViewById(R.id.inputValue);
        Spinner fromUnit = findViewById(R.id.fromUnit);
        Spinner toUnit = findViewById(R.id.toUnit);
        Button convertButton = findViewById(R.id.convertButton);
        TextView resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnit.setAdapter(adapter);
        toUnit.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputValue.getText().toString();

                if (!input.isEmpty()) {
                    double valor = Double.parseDouble(input);
                    String unidadeOrigem = fromUnit.getSelectedItem().toString();
                    String unidadeDestino = toUnit.getSelectedItem().toString();

                    double resultado = converter(valor, unidadeOrigem, unidadeDestino);
                    resultText.setText(valor + " " + unidadeOrigem + " = " + resultado + " " + unidadeDestino);
                } else {
                    Toast.makeText(MainActivity.this, "Digite um valor!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private double converter(double valor, String origem, String destino) {
        double metros = 0;

        switch (origem) {
            case "Centímetros":
                metros = valor / 100;
                break;
            case "Metros":
                metros = valor;
                break;
            case "Quilômetros":
                metros = valor * 1000;
                break;
            case "Milhas":
                metros = valor * 1609.34;
                break;
        }

        switch (destino) {
            case "Centímetros":
                return metros * 100;
            case "Metros":
                return metros;
            case "Quilômetros":
                return metros / 1000;
            case "Milhas":
                return metros / 1609.34;
        }

        return 0;
    }
}
