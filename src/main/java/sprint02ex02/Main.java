package sprint02ex02;

import org.json.simple.parser.ParseException;
import sprint02ex02.dao.FilmeDAO;
import sprint02ex02.entities.Filme;
import sprint02ex02.factory.ConnectionFactory;
import sprint02ex02.factory.FactoryUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ParseException {
        Connection connection = new ConnectionFactory().recuperarConexao();
        FactoryUtils.createDatabase("filmes", connection);
        FactoryUtils.criarTabela(connection);
        FilmeDAO filmeDAO = new FilmeDAO(connection);
        filmeDAO.adicionaFilmes();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Quantos filmes deseja filtrar ?  -> ");
        int quantidadeFiltro = scanner.nextInt();
        System.out.print("Qual página deseja exibir? -> ");
        int paginaFiltro = scanner.nextInt();
        List<Filme> filmesDoBanco = FilmeDAO.getAllFilmes(((paginaFiltro -1) * quantidadeFiltro),
                quantidadeFiltro, connection);
        System.out.println();
        for(Filme filme : filmesDoBanco) {
            System.out.println(filme.toString());
        }
        System.out.println(" " + quantidadeFiltro + " filmes ------------------------------- página: " + paginaFiltro);
        FactoryUtils.dropTable("filme",connection);
        FactoryUtils.dropDatabase("filmes",connection);
    }
}
