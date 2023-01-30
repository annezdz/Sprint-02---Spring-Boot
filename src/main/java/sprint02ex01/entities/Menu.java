package sprint02ex01.entities;

import sprint02ex01.dao.ProdutoDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static void menuOpcoes() {
        System.out.println("***********************************");
        System.out.println("* Escolha uma das opções:         *");
        System.out.println("* 1 - Cadastrar produtos          *");
        System.out.println("* 2 - Atualizar produtos          *");
        System.out.println("* 3 - Excluir produto             *");
        System.out.println("* 0 - Sair                        *");
        System.out.println("***********************************");
    }

    public static void escolheOpcao(int opcao, ProdutoDAO produtoDAO) throws SQLException {
        String coluna;
        String valor;
        Scanner scanner = new Scanner(System.in);
        if(opcao == 1) {
            produtoDAO.adicionaProdutos();
            for(Produto produto :produtoDAO.listarProdutos()) {
                System.out.println(produto.toString());
            }
        } else if(opcao == 2) {
            if(produtoDAO.validaProdutosCadastrados() > 0) {
                System.out.print("Informe o dado que deseja atualizar: \n - Nome, Descrição, Quantidade ou Valor: -> \n");
                System.out.print("Nome da Coluna -> ");
                coluna = scanner.next();
                if(coluna.matches("(?i).*nome|descricao|quantidade|preco")) {
                    System.out.print("Novo valor -> ");
                    valor = scanner.next();
                    produtoDAO.updateProduto(coluna,valor);
                } else {
                    System.out.println("Coluna inválida!Voltando ao menu inicial");
                }
            } else {
                System.out.println("Não existem produtos cadastrados na base de dados.\n" +
                        "Quantidade de produtos cadastrados: " + produtoDAO.validaProdutosCadastrados());
            }
        } else if(opcao == 3) {
            produtoDAO.deleteProduto();
        } else {
            System.out.println("Opção inválida!Selecione 1,2 ,3 ou 0 para sair!");
        }
    }
}
