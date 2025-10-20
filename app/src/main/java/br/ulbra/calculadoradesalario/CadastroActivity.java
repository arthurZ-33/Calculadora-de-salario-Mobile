package br.ulbra.calculadoradesalario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    EditText edtxtNome, edtxtEmailCad, edtxtSenhaCad;
    Button btnCadastrar, btnTelaLogin;


    DbHelper dbHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        dbHelper = new DbHelper(this);


        edtxtNome = findViewById(R.id.edtxtNome);
        edtxtEmailCad = findViewById(R.id.edtxtEmailCad);
        edtxtSenhaCad = findViewById(R.id.edtxtSenhaCad);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnTelaLogin = findViewById(R.id.btnTelaLogin);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edtxtNome.getText().toString().trim();
                String email = edtxtEmailCad.getText().toString().trim();
                String senha = edtxtSenhaCad.getText().toString();

                if (nome.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "O campo NOME está vazio", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (email.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "O E-MAIL é obrigatório", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (senha.isEmpty()) {
                    Toast.makeText(CadastroActivity.this, "A SENHA não pode ser vazia", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (senha.length() < 6) {
                    Toast.makeText(CadastroActivity.this, "A SENHA é fraca! Mínimo de 6 caracteres.", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean sucesso = dbHelper.addUser(nome, email, senha);

                if (sucesso) {
                    Toast.makeText(CadastroActivity.this, "Registro OK! Bem-vindo ao Multiverso!", Toast.LENGTH_LONG).show();

                    finish();
                } else {
                    Toast.makeText(CadastroActivity.this, "Falha no registro! Email já cadastrado ou erro interno.", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnTelaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

}