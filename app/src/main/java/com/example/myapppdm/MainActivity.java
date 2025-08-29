package com.example.myapppdm;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final double precoBase = 10.00;
    private Map<Integer,Double> precosAdicionais;
    private Map<Integer,Integer> precosTamanhos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inicializarPrecos();
        inicializarTamanho();

        Button button = findViewById(R.id.btnFinalizar);

        //adicionais
        CheckBox adc1 = findViewById(R.id.adc1);
        CheckBox adc2 = findViewById(R.id.adc2);
        CheckBox adc3 = findViewById(R.id.adc3);
        CheckBox adc4 = findViewById(R.id.adc4);
        CheckBox adc5 = findViewById(R.id.adc5);
        CheckBox adc6 = findViewById(R.id.adc6);
        CheckBox adc7 = findViewById(R.id.adc7);
        CheckBox adc8 = findViewById(R.id.adc8);


        // radioGroup
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton rdbtn_tam1 = findViewById(R.id.rdbtn_tam1);
        RadioButton rdbtn_tam2 = findViewById(R.id.rdbtn_tam2);
        RadioButton rdbtn_tam3 = findViewById(R.id.rdbtn_tam3);


        //spinner
        String [] tipos = {"Tradicional", "Puro", "Guaraná"};
        Spinner spinner = findViewById(R.id.spinner_tipos);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double precoTotalAdicionais = 0.0;
                double precoTotalTamanho = 0.0;
                //calculando preco dos adicionais
                for (Map.Entry<Integer, Double> entry : precosAdicionais.entrySet()) {
                    CheckBox checkBox = findViewById(entry.getKey());
                    if (checkBox.isChecked()) {
                        precoTotalAdicionais += entry.getValue();
                    }
                }

                //calculando preco do tamanho
                for (Map.Entry<Integer, Integer> entry : precosTamanhos.entrySet()) {
                    RadioButton radioButton = findViewById(entry.getKey());
                    if (radioButton.isChecked()) {
                        precoTotalTamanho += entry.getValue();
                    }
                }

                double precoTotal = precoBase + precoTotalTamanho + precoTotalAdicionais;
                Toast.makeText(MainActivity.this, "Preço total: R$" + precoTotal, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void inicializarPrecos(){
        precosAdicionais = new HashMap<>();
        precosAdicionais.put(R.id.adc1,1.99);
        precosAdicionais.put(R.id.adc2,1.99);
        precosAdicionais.put(R.id.adc3,2.99);
        precosAdicionais.put(R.id.adc4,2.99);
        precosAdicionais.put(R.id.adc5,3.99);
        precosAdicionais.put(R.id.adc6,4.99);
        precosAdicionais.put(R.id.adc7,3.49);
        precosAdicionais.put(R.id.adc8,3.99);
    }

    private void inicializarTamanho(){
        precosTamanhos = new HashMap<>();
        precosTamanhos.put(R.id.rdbtn_tam1,2);
        precosTamanhos.put(R.id.rdbtn_tam2,3);
        precosTamanhos.put(R.id.rdbtn_tam3,5);
    }
}