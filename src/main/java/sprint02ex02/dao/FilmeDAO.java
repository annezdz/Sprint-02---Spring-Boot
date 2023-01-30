package sprint02ex02.dao;

import org.json.simple.parser.ParseException;
import sprint02ex02.entities.Filme;
import sprint02ex02.utils.Utils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    private final Connection conexao;

    public FilmeDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void adicionaFilmes() throws IOException, ParseException {
        String sql = "INSERT INTO FILME (NOME, DESCRICAO, ANO) VALUES (? ,? ,?);";
        var filmesList = Utils.readFile("src/main/java/sprint02ex02/utils/filmes.json");
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (Filme filme : filmesList) {
                preparedStatement.setString(1, filme.getNome());
                preparedStatement.setString(2, filme.getDescricao());
                preparedStatement.setInt(3, filme.getAno());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Item já existente... Erro: "+ e.getMessage());
        }
    }

    public static List<Filme> getAllFilmes(Connection connection) {
        List<Filme> list = new ArrayList<>();
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM FILME")) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(getFilme(result));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<Filme> getAllFilmes(int offset, int noOfRecords, Connection connection) {
        String query = "SELECT SQL_CALC_FOUND_ROWS * from filme limit " + offset + ", " + noOfRecords;
        List<Filme> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                list.add(getFilme(rs));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static Filme getFilme(ResultSet result) throws SQLException {
        int id = result.getInt(1);
        String nome = result.getString(2);
        String descricao = result.getString(3);
        int ano = result.getInt(4);
        return new Filme(id, nome, descricao, ano);
    }

    public List<Filme> getFilmePorQuantidadeEPagina(int beginIndex, int pageSize, Connection connection) {
        List<Filme> list = new ArrayList<>();
        try (PreparedStatement statement =
                     connection.prepareStatement
                             ("SELECT * FROM FILME ORDER BY ID LIMIT ? OFFSET ?")) {
            statement.setInt(1, pageSize);
            statement.setInt(2, beginIndex-1);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(getFilme(result));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
