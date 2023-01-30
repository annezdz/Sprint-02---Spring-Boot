package sprint02ex02.entities;

public class Filme {

    private int id;
    private String nome;
    private String descricao;
    private int ano;

    public Filme() {}
    public Filme(int id, String nome, String descricao, int ano) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getAno() {
        return ano;
    }

    @Override
    public String toString() {
        return " Filme " + id + " : " + nome + '\'' +
                " Descrição: " + descricao + '\'' +
                " Ano : " + ano;
    }
}
