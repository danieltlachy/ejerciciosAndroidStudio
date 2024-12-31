package com.example.databinding;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.databinding.databinding.ActivityResultBinding;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    public static final String IMC_KEY = "masacorporal";

    public static final String WEIGHT_KEY = "peso";

    public static final String HEIGHT_KEY = "altura";

    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras(); //clase que almacena y transmite datos entre componentes de la app
        DecimalFormat df = new DecimalFormat("#.#");
        binding.setResultImc(df.format(extras.getDouble(IMC_KEY)));
        binding.setDataHeight(String.valueOf(extras.getDouble(HEIGHT_KEY)));
        binding.setDataWeight(String.valueOf(extras.getDouble(WEIGHT_KEY)));
        paintRow(extras.getDouble(IMC_KEY));
    }

    private void paintRow(double imc){
        double normal = 18.5, over = 25, obesity1 = 30, obesity2 = 35, obesity3 = 40;
        if(imc < normal){
            binding.rowUnderWeight.setBackgroundColor(getColor(R.color.blue)); //agregar manualmente el html de colores en values/colors
        } else if (imc >= normal && imc < over) {
            binding.rowNormalWeight.setBackgroundColor(getColor(R.color.green));
        }else if(imc >= over && imc < obesity1){
            binding.rowOverweight.setBackgroundColor(getColor(R.color.yellow));
        }else if(imc >= obesity1 && imc < obesity2){
            binding.rowObesity1.setBackgroundColor(getColor(R.color.orange));
        }else if(imc >= obesity2 && imc < obesity3){
            binding.rowObesity2.setBackgroundColor(getColor(R.color.red));
        }else{
            binding.rowObesity3.setBackgroundColor(getColor(R.color.pink));
        }
    }
}