package com.example.feirinhaartesanal;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class CadastroVendedor extends AppCompatActivity {

    private EditText caixa_nome,caixa_email,caixa_cidade,caixa_UF,caixa_conta,caixa_senha,caixa_cpf_cnpj;
    private Button bt_cadastrar;
    String[] menssagens = {"Preencha todos os campo", "Cadastro realizado com sucesso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_vendedor);

        getSupportActionBar().hide();
        IniciarComponentes();

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String nome = caixa_nome.getText().toString();
                String email = caixa_email.getText().toString();
                String senha = caixa_senha.getText().toString();
                String cidade = caixa_cidade.getText().toString();
                String UF = caixa_UF.getText().toString();
                String conta = caixa_conta.getText().toString();
                String cpf_cnpj  = caixa_cpf_cnpj.getText().toString();


                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cidade.isEmpty() || UF.isEmpty()  || conta.isEmpty() || cpf_cnpj.isEmpty() )
                {
                    Snackbar snackbar = Snackbar.make(v, menssagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void IniciarComponentes()
    {

        caixa_nome = findViewById(R.id.caixa_nome);
        caixa_cidade = findViewById(R.id.caixa_cidade);
        caixa_UF = findViewById(R.id.caixa_UF);
        caixa_conta = findViewById(R.id.caixa_conta);
        caixa_email = findViewById(R.id.caixa_email);
        caixa_cpf_cnpj = findViewById(R.id.caixa_cpf_cnpj);
        caixa_senha = findViewById(R.id.caixa_senha);
        bt_cadastrar = findViewById(R.id.bt_CadastroV);

    }
}