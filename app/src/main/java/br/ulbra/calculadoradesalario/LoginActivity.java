package br.ulbra.calculadoradesalario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText edtxtEmail, edtxtSenha;
    Button btnLogar, btnTelaCadastro;

    DbHelper dbHelper; 

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        dbHelper = new DbHelper(this);

        edtxtEmail = findViewById(R.id.edtxtEmail);
        edtxtSenha = findViewById(R.id.edtxtSenha);
        btnLogar = findViewById(R.id.btnLogar);
        btnTelaCadastro = findViewById(R.id.btnTelaCadastro);



        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtxtEmail.getText().toString();
                String senha = edtxtSenha.getText().toString();

                // Validação Rápida
                if (email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Preencha TUDO, não seja um fantasma!", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean loginSucesso = dbHelper.checkUser(email, senha);

                if (loginSucesso) {
                    Toast.makeText(LoginActivity.this, "Bem vindo de volta meu patrão", Toast.LENGTH_LONG).show();


                    Intent intent = new Intent(LoginActivity.this, CalcularSalarioActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Acesso negado, tente novamente. O usuário não existe.", Toast.LENGTH_LONG).show();

                }
            }
        });


        btnTelaCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(i);

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
