package br.ulbra.calculadoradesalario;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText edtxtEmail,edtxtSenha;
    Button btnLogar, btnTelaCadastro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtxtEmail = (EditText) findViewById(R.id.edtxtEmail);
        edtxtSenha = (EditText) findViewById(R.id.edtxtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);
        btnTelaCadastro =(Button) findViewById(R.id.btnTelaCadastro);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
