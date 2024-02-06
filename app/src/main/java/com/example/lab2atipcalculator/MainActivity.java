package com.example.lab2atipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import java.text.NumberFormat;

import com.example.lab2atipcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String bill_input, percent_input, people_input, totalFormat;
    int percent, people;
    double bill, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.calButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill_input = binding.billInput.getText().toString();
                percent_input =  binding.percentInput.getText().toString();
                people_input = binding.peopleInput.getText().toString();

                if (isValidCheck()){
                    calculateTotal();
                }
            }
        });
    }

    public boolean isValidCheck(){

        if (bill_input.isEmpty() || percent_input.isEmpty()
            || people_input.isEmpty()){
            return false;
        }
        else if (bill_input.equals(".")){
            return false;
        }
        else{
            return true;
        }
    }

    public void calculateTotal(){
        bill = Double.parseDouble(bill_input);
        percent = Integer.parseInt(percent_input);
        people = Integer.parseInt(people_input);

        if (bill != 0 && percent != 0 && people != 0) {
            total = ((bill * percent / 100) + bill) / people;
            totalFormat = NumberFormat.getCurrencyInstance().format(total);

            binding.totalText.setText(getResources().getString(R.string.total_text, totalFormat));
        }
    }
}