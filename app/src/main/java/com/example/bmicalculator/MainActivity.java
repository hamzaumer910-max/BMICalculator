package com.example.bmicalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;
    Button btnCalculate, btnReset;
    TextView txtResult, txtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        txtResult = findViewById(R.id.txtResult);
        txtCategory = findViewById(R.id.txtCategory);

        btnCalculate.setOnClickListener(v -> {

            String weightText =
                    etWeight.getText().toString();

            String heightText =
                    etHeight.getText().toString();

            if(weightText.isEmpty() ||
                    heightText.isEmpty()) {

                txtResult.setText("Enter values");
                return;
            }

            double weight =
                    Double.parseDouble(weightText);

            double heightCm =
                    Double.parseDouble(heightText);

            double heightM =
                    heightCm / 100;

            double bmi =
                    weight /
                            (heightM * heightM);

            txtResult.setText(
                    "BMI = " +
                            String.format("%.2f", bmi));

            if(bmi < 18.5) {
                txtCategory.setText("Underweight");
                txtCategory.setTextColor(ContextCompat.getColor(this, R.color.status_underweight));
            }
            else if(bmi < 25) {
                txtCategory.setText("Normal Weight");
                txtCategory.setTextColor(ContextCompat.getColor(this, R.color.status_normal));
            }
            else if(bmi < 30) {
                txtCategory.setText("Overweight");
                txtCategory.setTextColor(ContextCompat.getColor(this, R.color.status_overweight));
            }
            else {
                txtCategory.setText("Obese");
                txtCategory.setTextColor(ContextCompat.getColor(this, R.color.status_overweight));
            }

        });

        btnReset.setOnClickListener(v -> {

            etWeight.setText("");
            etHeight.setText("");

            txtResult.setText("");
            txtCategory.setText("");

        });
    }
}
