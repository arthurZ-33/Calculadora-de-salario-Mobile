package br.ulbra.calculadoradesalario;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class CalcularSalarioActivity extends AppCompatActivity {

    EditText edtxtNomeCalcular, edtxtSalarioBruto, edtxtNumFilhos;
    RadioGroup rdGrupo;
    Button btnCalcular, btnNovoCalculo;


    TextView txtCabecalho, txtSalarioBrutoExibir, txtDescontoInss, txtDescontoIR, txtSalarioFamilia, txtSalarioLiquido;
    View layoutResultados;

    //
    private static final NumberFormat MOEDA_BR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_salario);

        // 1. Binding (Conectando o Java ao seu XML)
        // Inputs
        edtxtNomeCalcular = findViewById(R.id.edtxtNomeCalcular);
        edtxtSalarioBruto = findViewById(R.id.edtxtSalarioBruto);
        edtxtNumFilhos = findViewById(R.id.edtxtNumFilhos);
        rdGrupo = findViewById(R.id.rdGrupo);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnNovoCalculo = findViewById(R.id.btnNovoCalculo);


        txtCabecalho = findViewById(R.id.txtCabecalho);
        txtSalarioBrutoExibir = findViewById(R.id.txtSalarioBrutoExibir);
        txtDescontoInss = findViewById(R.id.txtDescontoInss);
        txtDescontoIR = findViewById(R.id.txtDescontoIR);
        txtSalarioFamilia = findViewById(R.id.txtSalarioFamilia);
        txtSalarioLiquido = findViewById(R.id.txtSalarioLiquido);

        btnCalcular.setOnClickListener(v -> realizarCalculo());
        btnNovoCalculo.setOnClickListener(v -> limparFormulario());


        limparFormulario();
    }

    // --- O CÓDIGO DA AÇÃO ---

    private void realizarCalculo() {
        // A. Pega e Limpa os Dados
        String nome = edtxtNomeCalcular.getText().toString().trim();
        String strSalario = edtxtSalarioBruto.getText().toString().trim();
        String strFilhos = edtxtNumFilhos.getText().toString().trim();
        int selectedSexoId = rdGrupo.getCheckedRadioButtonId();


        if (nome.isEmpty() || strSalario.isEmpty() || strFilhos.isEmpty() || selectedSexoId == -1) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios, Agente Smith!", Toast.LENGTH_LONG).show();
            return;
        }

        double salario;
        int filhos;
        try {
            salario = Double.parseDouble(strSalario);
            filhos = Integer.parseInt(strFilhos);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Salário e Filhos precisam ser números válidos. Não é um dragão!", Toast.LENGTH_LONG).show();
            return;
        }


        if (salario <= 0 || salario > 1000000 || filhos < 0) {
            Toast.makeText(this, "Dados fora do limite permitido: Salário (> 0 e <= 1M) e Filhos (>= 0).", Toast.LENGTH_LONG).show();
            return;
        }


        CalculadoraSalario.ResultadoCalculo resultado = CalculadoraSalario.calcular(salario, filhos);


        RadioButton rbSelected = findViewById(selectedSexoId);
        String sexoStr = rbSelected.getText().toString().toLowerCase(Locale.ROOT);
        String tratamento = sexoStr.contains("masculino") ? "Sr. " : "Sra. ";
        txtCabecalho.setText(tratamento + nome);


        txtSalarioBrutoExibir.setText("Salário Bruto: " + MOEDA_BR.format(resultado.salarioBruto));
        txtDescontoInss.setText("Desconto INSS: " + MOEDA_BR.format(resultado.descontoINSS));
        txtDescontoIR.setText("Desconto IR: " + MOEDA_BR.format(resultado.descontoIR));


        if (resultado.salarioFamilia > 0) {
            txtSalarioFamilia.setText("Salário-Família: " + MOEDA_BR.format(resultado.salarioFamilia));
            txtSalarioFamilia.setVisibility(View.VISIBLE);
        } else {
            txtSalarioFamilia.setVisibility(View.GONE);
        }


        txtSalarioLiquido.setText("Salário Líquido: " + MOEDA_BR.format(resultado.salarioLiquido));


        layoutResultados.setVisibility(View.VISIBLE);
    }


    private void limparFormulario() {
        edtxtNomeCalcular.setText("");
        edtxtSalarioBruto.setText("");
        edtxtNumFilhos.setText("");
        rdGrupo.clearCheck();

        txtCabecalho.setText("Aguardando Entradas...");
        layoutResultados.setVisibility(View.GONE);


        edtxtNomeCalcular.requestFocus();
    }
}





