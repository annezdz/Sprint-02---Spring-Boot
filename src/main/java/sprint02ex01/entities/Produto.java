package sprint02ex01.entities;

public class Produto {

    private int id;
    private final String nome;
    private final String descricao;
    private final int quantidade;
    private final double preco;

    public Produto(String nome, String descricao, int quantidade, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Produto(int id, String nome, String descricao, int quantidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Produto : " +
                " id: " + id +
                ", Nome: " + nome  +
                ", Descricao: " + descricao +
                ", Quantidade: " + quantidade +
                ", Preco: R$ " + preco + "\n";
    }
}
