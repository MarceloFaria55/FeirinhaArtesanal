package com.example.feirinhaartesanal;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroComprador extends AppCompatActivity {

    private EditText caixa_nome,caixa_email,caixa_endereco,caixa_cidade,caixa_UF,caixa_senha,caixa_cpf;
    private Button bt_cadastrar;
    String[] menssagens = {"Preencha todos os campos", "Cadastro realizado com sucesso", "Deu algum erro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_comprador);

        getSupportActionBar().hide();
        IniciarComponentes();

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String nome = caixa_nome.getText().toString();
                String email = caixa_email.getText().toString();
                String senha = caixa_senha.getText().toString();
                String cpf  = caixa_cpf.getText().toString();
                String cidade = caixa_cidade.getText().toString();
                String UF = caixa_UF.getText().toString();
                String endereco  = caixa_endereco.getText().toString();

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cpf.isEmpty() || cidade.isEmpty() || UF.isEmpty()  || endereco.isEmpty() )
                {
                    Snackbar snackbar = Snackbar.make(v, menssagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                else
                {
                    CadastrarComprador(v);
                }
            }
        });
    }
    private void CadastrarComprador(View v)
      {
        String email = caixa_email.getText().toString();
        String senha = caixa_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Snackbar snackbar = Snackbar.make(v, menssagens[1],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                else
                {
                    Snackbar snackbar = Snackbar.make(v, menssagens[2],Snackbar.LENGTH_SHORT);
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
        caixa_cpf = findViewById(R.id.caixa_cpf);
        caixa_email = findViewById(R.id.caixa_emailC);
        caixa_endereco = findViewById(R.id.caixa_endereco);
        caixa_senha = findViewById(R.id.caixa_senhaC);
        bt_cadastrar = findViewById(R.id.bt_CadastroC);
    }
}