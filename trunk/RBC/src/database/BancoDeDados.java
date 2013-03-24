package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDados {

    /**
     * Classe responsavel pela criação e conexão com o bando de dados(SGBDR
     * MySQL) do projeto.
     */
    public static Connection CONNECTION;
    public static Statement STATEMENT;
    public static PreparedStatement PREPAREDSTATEMENT;
    public static ResultSet RESULTSET;
    private String sql;

    public BancoDeDados() {
        conectarLocal();
        criarBanco();
        conectarBanco();
        criarTabelas();
    }

    /**
     * Conectar Banco de Dados padrão em MySQL
     *
     * @exception Exceção SQL
     */
    private void conectarLocal() {
        try {
            Class.forName(util.Banco.DRIVER_MYSQL);

            CONNECTION = DriverManager.getConnection(util.Banco.CONEXAO_BANCO_LOCAL,
                    util.Banco.USER_MYSQL,
                    util.Banco.PASSWORD_MYSQL);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Conectar Banco de Dados RBC em MySQL
     *
     * @exception Exceção SQL
     */
    private void conectarBanco() {
        try {
            Class.forName(util.Banco.DRIVER_MYSQL);

            CONNECTION = DriverManager.getConnection(
                    util.Banco.CONEXAO_BANCO_LOCAL + util.Banco.NOME_BANCO,
                    util.Banco.USER_MYSQL, util.Banco.PASSWORD_MYSQL);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();

        }
    }

    /**
     * Criar Banco de Dados RBC se não existir.
     *
     * @exception Exceção SQL
     * @exception Exceção SQL
     */
    private void criarBanco() {
        try {
            sql = "CREATE DATABASE IF NOT EXISTS RBC;";
            STATEMENT = CONNECTION.createStatement();

            STATEMENT.executeUpdate(sql);
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }

    /**
     * Criar tabelas no Banco de Dados RBC.
     *
     * @param comando Comando a ser executado em SQL para a criação de tabela
     * @exception Exceção SQL
     * @exception Exceção SQL
     */
    private void adicionarTabelas(String comando) {

        try {
            STATEMENT = CONNECTION.createStatement();

            STATEMENT.executeUpdate(comando);

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }

    /**
     * Cria todas as Tabelas no BD RCB caso elas não existam.
     */
    private void criarTabelas() {
        adicionarTabelaCaso();
    }

    /**
     * Metodo para adicionar a tabela caso ao bando de dados.
     */
    private void adicionarTabelaCaso() {

        sql = "CREATE TABLE IF NOT EXISTS caso ("
                + "id INT NOT NULL AUTO_INCREMENT,"
                + "descricao TEXT NOT NULL,"
                + "solucao TEXT,"
                + "avaliacao INT(1),"
                + "FULLTEXT (descricao),"
                + "CONSTRAINT pk_id PRIMARY KEY (id)"
                + ") ENGINE=InnoBD DEFAULT CHARSET=utf8;";

        adicionarTabelas(sql);
    }
}