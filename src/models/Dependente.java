package models;

public class  Dependente {

    public static double pensao = 189.59;

    private String nome;
    private String dtNascimento;

    public Dependente(String nome, String dtNascimento) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDtNascimento() {
        return this.dtNascimento;
    }
}