package models;

public class Dependente {

    public static double pensao = 189.59;

    private String nome;
    private String dtNascimento;

    public Dependente(String nome, String dtNascimento) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
    }

    public String getNome() {
        return "Carlos";
    }

    public String getDtNascimento() {
        return "25/03/1990";
    }
}