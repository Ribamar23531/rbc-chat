package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDeDados {

    /**
     * Classe respons�vel pela cria��o e conex�o com o bando de dados(SGBDR
     * MySQL) do projeto.
     */
    public static Connection CONNECTION;
    public static Statement STATEMENT;
    public static PreparedStatement PREPAREDSTATEMENT;
    public static ResultSet RESULTSET;
    private String sql;

    /**
     * Conectar Banco de Dados padr�o em MySQL
     *
     * @exception Exce��o SQL
     */
    public void conectarLocal() {
        try {
            Class.forName(util.Banco.DRIVER_MYSQL);
            try {
                CONNECTION = DriverManager.getConnection(util.Banco.CONEXAO_BANCO_LOCAL,
                        util.Banco.USER_MYSQL,
                        util.Banco.PASSWORD_MYSQL);
            } catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    /**
     * Conectar Banco de Dados RBC em MySQL
     *
     * @exception Exce��o SQL
     */
    public void conectarBanco() {
        try {
            Class.forName(util.Banco.DRIVER_MYSQL);
            try {
                CONNECTION = DriverManager.getConnection(
                        util.Banco.CONEXAO_BANCO_LOCAL + util.Banco.NOME_BANCO,
                        util.Banco.USER_MYSQL, util.Banco.PASSWORD_MYSQL);
            } catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();

        }
    }

    /**
     * Criar Banco de Dados RBC se n�o existir.
     *
     * @exception Exce��o SQL
     * @exception Exce��o SQL
     */
    public void criarBanco() {
        try {
            sql = "CREATE DATABASE IF NOT EXISTS RBC;";
            STATEMENT = CONNECTION.createStatement();

            try {
                STATEMENT.executeUpdate(sql);
            } catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }

    /**
     * Criar tabelas no Banco de Dados RBC.
     *
     * @param comando Comando a ser executado em SQL para a cria��o de tabela
     * @exception Exce��o SQL
     * @exception Exce��o SQL
     */
    public void adicionarTabelas(String comando) {

        try {
            STATEMENT = CONNECTION.createStatement();

            try {
                STATEMENT.executeUpdate(comando);

            } catch (SQLException sQLException) {
                sQLException.printStackTrace();
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

    }

    /**
     * Cria todas as Tabelas no BD RCB caso elas n�o existam.
     */
    public void criarTabelas() {
        adicionarTabelaCaso();
    }

    /**
     * M�todo para adicionar a tabela caso ao bando de dados.
     */
    public void adicionarTabelaCaso() {

        sql = "CREATE TABLE IF NOT EXISTS caso ("
                + "id INT NOT NULL AUTO_INCREMENT,"
                + "descricao TEXT NOT NULL,"
                + "solucao TEXT NOT NULL,"
                + "avaliacao BIT(1) NOT NULL DEFAULT 0,"
                + "FULLTEXT (descricao),"
                + "CONSTRAINT pk_id PRIMARY KEY (id)"
                + ") ENGINE=MyISAM DEFAULT CHARSET=utf8;";

        adicionarTabelas(sql);
    }
}