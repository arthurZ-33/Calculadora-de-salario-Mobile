package br.ulbra.calculadoradesalario;


public class CalculadoraSalario {


    private static final double L1_INSS = 1212.00;
    private static final double L2_INSS = 2427.35;
    private static final double L3_INSS = 3641.03;
    private static final double L4_INSS = 7087.22;


    private static final double A1_INSS = 0.075;
    private static final double A2_INSS = 0.09;
    private static final double A3_INSS = 0.12;
    private static final double A4_INSS = 0.14;


    private static final double L1_IR = 1903.98; // Isento
    private static final double L2_IR = 2826.65;
    private static final double L3_IR = 3751.05;
    private static final double L4_IR = 4664.68;


    private static final double A1_IR = 0.0; // Isento (0%)
    private static final double A2_IR = 0.075;
    private static final double A3_IR = 0.15;
    private static final double A4_IR = 0.225;


    private static final double VALOR_POR_FILHO = 56.47;
    private static final double LIMITE_SALARIO_FAMILIA = 1212.00;


    public static class ResultadoCalculo {
        public double salarioBruto;
        public double descontoINSS;
        public double descontoIR;
        public double salarioFamilia;
        public double salarioLiquido;

    }

    public static ResultadoCalculo calcular(double salarioBruto, int numeroFilhos) {
        ResultadoCalculo resultado = new ResultadoCalculo();
        resultado.salarioBruto = salarioBruto; // Guarda o original


        double aliquotaInss;
        if (salarioBruto <= L1_INSS) {
            aliquotaInss = A1_INSS;
        } else if (salarioBruto <= L2_INSS) {
            aliquotaInss = A2_INSS;
        } else if (salarioBruto <= L3_INSS) {
            aliquotaInss = A3_INSS;
        } else {
            aliquotaInss = A4_INSS;
        }
        resultado.descontoINSS = salarioBruto * aliquotaInss;


        double aliquotaIr;
        if (salarioBruto <= L1_IR) {
            aliquotaIr = A1_IR;
        } else if (salarioBruto <= L2_IR) {
            aliquotaIr = A2_IR;
        } else if (salarioBruto <= L3_IR) {
            aliquotaIr = A3_IR;
        } else {
            aliquotaIr = A4_IR;
        }
        resultado.descontoIR = salarioBruto * aliquotaIr;

        if (salarioBruto <= LIMITE_SALARIO_FAMILIA) {
            resultado.salarioFamilia = VALOR_POR_FILHO * numeroFilhos;
        } else {
            resultado.salarioFamilia = 0.0;
        }

        resultado.salarioLiquido = salarioBruto - resultado.descontoINSS - resultado.descontoIR + resultado.salarioFamilia;

        return resultado;
    }
}


