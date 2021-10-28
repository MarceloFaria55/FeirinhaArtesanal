package com.example.feirinhaartesanal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroVendedor extends AppCompatActivity {

    private EditText caixa_nome,caixa_email,caixa_cidade,caixa_UF,caixa_conta,caixa_senha,caixa_cpf_cnpj;
    private Button bt_cadastrar;
    private ProgressBar progressBar;
    String[] menssagens = {"Preencha todos os campo", "Cadastro realizado com sucesso", "Politica de senha não atendido"};
    String usuarioId;
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
                }else
                {
                    CadastrarVendedor(v);

                }

            }
        });
    }
    private void CadastrarVendedor(View v)
    {
        String email = caixa_email.getText().toString();
        String senha = caixa_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    SalvarDados();
                    Snackbar snackbar = Snackbar.make(v, menssagens[1],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaLogin();
                        }
                    }, 2000);


                }
                else
                {
                    String Erro;
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        Erro = "Digite uma senha com no minimo 6 caracteres";
                    }catch (FirebaseAuthUserCollisionException e){
                        Erro = "Erro. Conta já foi cadastrada anteriormente!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        Erro = "Digite uma email válido. Ex: mail@gmail.com";
                    }

                    catch (Exception e)
                    {
                        Erro=   "Ocorreu um erro";
                    }
                    Snackbar snackbar = Snackbar.make(v, Erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }

            }
        });

    }
    private void SalvarDados()
    {
        String nome = caixa_nome.getText().toString();
        String cpf_cnpj = caixa_cpf_cnpj.getText().toString();
        String cidade = caixa_cidade.getText().toString();
        String uf = caixa_UF.getText().toString();
        String conta = caixa_conta.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("nome",nome);
        usuarios.put("cpf",cpf_cnpj);
        usuarios.put("cidade",cidade);
        usuarios.put("uf",uf);
        usuarios.put("endereco",conta);

        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioId);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db","Sucesso ao salvar dados");

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("db_error","Esso ao salvar dados"+ e.toString());

                    }
                });

    }
    private void TelaLogin()
    {
        Intent intent = new Intent(CadastroVendedor.this,Login.class);
        startActivity(intent);
        finish();
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
        progressBar = findViewById(R.id.loadCV);

    }
}