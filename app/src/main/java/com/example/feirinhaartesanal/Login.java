package com.example.feirinhaartesanal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {

    private TextView textCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        IniciarComponentes();

        textCadastro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(Login.this,EscolhaTipoCadastro.class);
                startActivity(intent);
            }
        });
    }

    private void IniciarComponentes()
    {
        textCadastro = findViewById(R.id.textCadastro);
    }

}