package sprint02ex01.dao;


import sprint02ex01.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final Connection conexao;

    public ProdutoDAO(Connection connection) {
        this.conexao = connection;
    }

    public List<Produto> criarListaProduto() {
        return List.of(
                new Produto("Batata", "Alimenticio", 10, 1.99),
                new Produto("Alho", "Alimenticio", 5, 2.99),
                new Produto("Abacaxi", "Alimenticio", 10, 3.90)
        );
    }

    public void adicionaProdutos() {
        String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, QUANTIDADE, PRECO) VALUES (? ,? ,? ,?);";
        var produtosList = criarListaProduto();
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (Produto produto : produtosList) {
                preparedStatement.setString(1, produto.getNome());
                preparedStatement.setString(2, produto.getDescricao());
                preparedStatement.setInt(3, produto.getQuantidade());
                preparedStatement.setDouble(4, produto.getPreco());
                preparedStatement.executeUpdate();
            }
            System.out.println("Cadastro dos produtos realizados com sucesso.");
        } catch (SQLException e) {
            System.out.println("Item já existente... Erro: "+ e.getMessage());
        }
    }


    public void updateProduto(String coluna, String valor) {
        String query = "update produto set " + coluna + " = ? where id = 1";
        boolean flag = false;
        try (PreparedStatement preparedStatement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            while (!flag) {
                if (coluna.equalsIgnoreCase("nome") || coluna.equalsIgnoreCase("descricao")) {
                    preparedStatement.setString(1, valor);
                    flag = true;
                } else if (coluna.equalsIgnoreCase("quantidade")) {
                    preparedStatement.setInt(1, Integer.parseInt(valor));
                    flag = true;
                } else if (coluna.equalsIgnoreCase("preco")) {
                    preparedStatement.setDouble(1, Double.parseDouble(valor));
                    flag = true;
                }
            }
            preparedStatement.execute();
            System.out.println("Alteração realizada com sucesso");
            for (Produto produto : listarProdutos()) {
                System.out.println(produto.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduto() throws SQLException {
        int id = 2;
        List<Produto> lista = listarProdutos();
        boolean foiEncontrado = lista.stream().noneMatch(c -> c.getId() == id);
        if(validaProdutosCadastrados() > 0) {
            if (foiEncontrado) {
                System.out.println("Produto não encontrado! Segue a listagem dos produtos cadastrados: \n");
                lista.forEach(System.out::println);
            }
        } else {
            System.out.println("Não existem produtos cadastrados na base de dados.\n" +
                    "Quantidade de produtos cadastrados: " + validaProdutosCadastrados());
        }

        String query = "delete from produto where id = " + id;
        try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {
            lista.stream().filter(c -> c.getId() == id).forEach((c -> {
                try {
                    preparedStatement.execute();
                    System.out.println("Produto " + c.getNome() + " excluído com sucesso!");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }));
        }
    }

    public List<Produto> listarProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT ID, NOME, DESCRICAO, QUANTIDADE, PRECO FROM PRODUTO";
        try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
            pstm.execute();
            trasformarResultSetEmProduto(produtos, pstm);
        }
        return produtos;
    }

    public int validaProdutosCadastrados() throws SQLException {
        List<Produto> listarProdutos = listarProdutos();
        return listarProdutos.size();
    }

    private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Produto produto =
                        new Produto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getDouble(5));
                produtos.add(produto);
            }
        }
    }
}
