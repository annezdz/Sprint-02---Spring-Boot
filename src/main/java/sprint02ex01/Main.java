package sprint02ex01;

import sprint02ex01.dao.ProdutoDAO;
import sprint02ex01.entities.Menu;
import sprint02ex01.factory.ConnectionFactory;
import sprint02ex01.factory.FactoryUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main   {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = new ConnectionFactory().recuperarConexao();
        FactoryUtils.createDatabase("produtos", connection);
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        FactoryUtils.criarTabela(connection);
        Menu.menuOpcoes();
        System.out.print("-> ");
        int opcao = scanner.nextInt();
        while(opcao != 0) {
            Menu.escolheOpcao(opcao, produtoDAO);
            Menu.menuOpcoes();
            System.out.print("-> ");
            opcao = scanner.nextInt();
        }
        FactoryUtils.dropTable("produto", connection);
        FactoryUtils.dropDatabase("produtos", connection);
    }
}
