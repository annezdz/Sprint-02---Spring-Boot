package sprint02ex01.factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class FactoryUtils {

    public static void createDatabase(String database, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS " + database + ";";
            statement.execute(sql);
            sql = "USE " + database +";";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropDatabase(String database, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String sql = "DROP DATABASE " + database;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void criarTabela(Connection connection) {
        String sql = "CREATE TABLE IF NOT EXISTS PRODUTO \n" +
                "                (ID INT AUTO_INCREMENT,\n" +
                "                 NOME VARCHAR(100) NOT NULL UNIQUE, \n" +
                "                 DESCRICAO VARCHAR(100), \n" +
                "                 QUANTIDADE INT, \n" +
                "                 PRECO FLOAT,\n" +
                "                 PRIMARY KEY (ID));";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void dropTable(String tabela, Connection connection) throws SQLException {
        String sql = "DROP TABLE " + tabela + ";";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        }
    }
}
