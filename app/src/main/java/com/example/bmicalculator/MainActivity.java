package com.example.bmicalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight, etAge;
    Button btnCalculate, btnReset;
    TextView txtResult, txtCategory;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        etAge = findViewById(R.id.etAge);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        txtResult = findViewById(R.id.txtResult);
        txtCategory = findViewById(R.id.txtCategory);
        rgGender = findViewById(R.id.rgGender);

        btnCalculate.setOnClickListener(v -> {

            String weightText = etWeight.getText().toString();
            String heightText = etHeight.getText().toString();
            String ageText = etAge.getText().toString();

            if(weightText.isEmpty() || heightText.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double weight = Double.parseDouble(weightText);
            double heightCm = Double.parseDouble(heightText);
            int age = Integer.parseInt(ageText);

            int selectedGenderId = rgGender.getCheckedRadioButtonId();
            String gender = "";
            if (selectedGenderId != -1) {
                RadioButton selectedGender = findViewById(selectedGenderId);
                gender = selectedGender.getText().toString();
            }

            double heightM = heightCm / 100;
            double bmi = weight / (heightM * heightM);

            txtResult.setText("BMI = " + String.format("%.2f", bmi));
            
            String category = "";
            int color;

            if(bmi < 18.5) {
                category = "Underweight";
                color = ContextCompat.getColor(this, R.color.status_underweight);
            } else if(bmi < 25) {
                category = "Normal Weight";
                color = ContextCompat.getColor(this, R.color.status_normal);
            } else if(bmi < 30) {
                category = "Overweight";
                color = ContextCompat.getColor(this, R.color.status_overweight);
            } else {
                category = "Obese";
                color = ContextCompat.getColor(this, R.color.status_overweight);
            }
            
            txtCategory.setText(String.format("%s (%s, %d years)", category, gender, age));
            txtCategory.setTextColor(color);
        });

        btnReset.setOnClickListener(v -> {
            etWeight.setText("");
            etHeight.setText("");
            etAge.setText("");
            rgGender.check(R.id.rbMale);
            txtResult.setText("");
            txtCategory.setText("");
        });
    }
}
