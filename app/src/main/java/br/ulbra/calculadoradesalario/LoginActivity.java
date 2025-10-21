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
    Button btnLogar;
    DbHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializando o DB
        dbHelper = new DbHelper(this);


        edtxtEmail = findViewById(R.id.edtxtEmail);
        edtxtSenha = findViewById(R.id.edtxtSenha);
        btnLogar = findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtxtEmail.getText().toString();
                String senha = edtxtSenha.getText().toString();

                boolean loginSucesso = dbHelper.checkUser(email, senha);

                if (loginSucesso) {
                    Toast.makeText(LoginActivity.this, "Acesso liberado! Bem-vindo ao Matrix.", Toast.LENGTH_LONG).show();

                    // Navega para a tela de cálculo APÓS a verificação
                    Intent intent = new Intent(LoginActivity.this, CalcularSalarioActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "Credenciais inválidas. Tente de novo, Neo.", Toast.LENGTH_LONG).show();
                }
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
