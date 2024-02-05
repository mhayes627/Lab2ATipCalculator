package com.example.lab2atipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.example.lab2atipcalculator.databinding.ActivityMainBinding;

import java.text.Format;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Editable bill_input, percent_input, people_input;
    int percent, people;
    double bill, total;
    String totalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill_input = binding.billInput.getText();
                percent_input =  binding.percentInput.getText();
                people_input = binding.peopleInput.getText();

                if (isValidCheck()){
                    total = calculateTotal();
                    totalFormat = NumberFormat.getCurrencyInstance().format(total);

                    binding.totalText.setText(getResources().getString(R.string.total_text, totalFormat));
                }
            }
        });
    }

    public boolean isValidCheck(){

        bill = Double.parseDouble(bill_input.toString());
        percent = Integer.parseInt(percent_input.toString());
        people = Integer.parseInt(people_input.toString());

        if (TextUtils.isEmpty(bill_input) || TextUtils.isEmpty(percent_input)
            || TextUtils.isEmpty(people_input)){
            return false;
        }
        else if (bill == 0 || percent == 0 || people ==0){
            return false;
        }
        else{
            return true;
        }
    }

    public double calculateTotal(){
        total = ((bill * percent/100) + bill)/ people;

        return total;
    }
}