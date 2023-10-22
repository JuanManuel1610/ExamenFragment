package com.example.examenfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    Button btnsalir, btndos, btnuno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnsalir = findViewById(R.id.btnsalir);
        btndos = findViewById(R.id.btnfragdos);
        btnuno = findViewById(R.id.btnuno);


        Fragment funo = new PrimerFragment();
        Fragment fdos = new SegundoFragment();


        btnuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction f1 = getSupportFragmentManager().beginTransaction();
                f1.replace(R.id.fcvcontenedor,funo).commit();
            }
        });


        btndos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction f2 = getSupportFragmentManager().beginTransaction();
                f2.replace(R.id.fcvcontenedor,fdos).commit();
            }
        });

        btnsalir.setOnClickListener(view -> {
            finish();
            Toast.makeText(Principal.this, "Se cerro con exito", Toast.LENGTH_SHORT).show();
        });
    }
}