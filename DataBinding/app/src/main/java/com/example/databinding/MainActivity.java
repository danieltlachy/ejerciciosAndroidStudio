package com.example.databinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding; //Se genera cuando se transforma el activity_main en un data binding layout.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //crea o infla vistas a partir de un XML en la app //Una vez inflada se accede a sus elementos de cada vista
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(v->{
            try {
                double peso = Double.parseDouble(binding.userWeight.getText().toString());
                double altura = Double.parseDouble(binding.userHeight.getText().toString());
                //binding.textResult.setText(getString(R.string.result_message, calc_imc(peso, altura))); //No olvidar el %f para valores decimales
                openResultActivity(peso, altura);
            }catch(NumberFormatException ex){
                Log.e("MainActivity",ex.toString());
                Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double calc_imc(double peso, double altura){
        double altura_m = altura/100;
        return peso/(altura_m*altura_m);
    }

    //Usamos Explicit intent, de modo que desde el MainActivity pasamos al ResultActivity
    private void openResultActivity(double peso, double altura) {
        double imc = calc_imc(peso, altura);
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.WEIGHT_KEY,peso); //agrega datos adicionales a un objeto Intent
        intent.putExtra(ResultActivity.HEIGHT_KEY,altura);
        intent.putExtra(ResultActivity.IMC_KEY, imc);
        startActivity(intent);
    }
}