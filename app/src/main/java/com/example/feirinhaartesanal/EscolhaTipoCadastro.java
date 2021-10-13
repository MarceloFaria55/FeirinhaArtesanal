package com.example.feirinhaartesanal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EscolhaTipoCadastro extends AppCompatActivity {

    private androidx.appcompat.widget.AppCompatButton buttonComprador;
    private androidx.appcompat.widget.AppCompatButton buttonVendedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_tipo_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();

        buttonVendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolhaTipoCadastro.this,CadastroVendedor.class);
                startActivity(intent);
            }
        });

        buttonComprador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolhaTipoCadastro.this,CadastroComprador.class);
                startActivity(intent);
            }
        });

    }
    private void IniciarComponentes()
    {
        buttonComprador = findViewById(R.id.buttonComprador);
        buttonVendedor  = findViewById(R.id.buttonVendedor);
    }
}