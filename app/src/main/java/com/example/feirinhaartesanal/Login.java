package com.example.feirinhaartesanal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private TextView textCadastro;
    private EditText edit_email,edit_senha;
    private Button bt_entrar;
    private ProgressBar progressBar;
    String[] menssagens = {"Preencha todos os campo", "Login realizado com sucesso"};

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
                finish();
            }
        });

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if(email.isEmpty() || senha.isEmpty())
                {
                    Snackbar snackbar = Snackbar.make(v, menssagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
                else
                {
                    AutenticarUsuario(v,email,senha);
                }
            }
        });
    }
    private void AutenticarUsuario(View v,String email,String senha)
    {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();
                        }
                    }, 2000);
                }else
                {
                    String erro;
                    try
                    {
                        throw task.getException();
                    }catch(Exception e)
                    {
                        erro= "Erro ao logar usuário";
                    }
                    Snackbar snackbar = Snackbar.make(v, erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void TelaPrincipal()
        {
            Intent intent = new Intent(Login.this,TelaPrincipal.class);
            startActivity(intent);
            finish();
        }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioatual = FirebaseAuth.getInstance().getCurrentUser();
        if(usuarioatual!=null)
        {
            TelaPrincipal();
        }
    }

    private void IniciarComponentes()
    {
        textCadastro = findViewById(R.id.textCadastro);
        edit_email = findViewById(R.id.caixa_email);
        edit_senha = findViewById(R.id.caixa_senha);
        bt_entrar = findViewById(R.id.bt_entrar);
        progressBar =findViewById(R.id.loadLogin);
    }

}