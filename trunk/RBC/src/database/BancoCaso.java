package database;

import engine.Caso;
import engine.Entrada;
import engine.Saida;
import java.sql.SQLException;

public class BancoCaso {

    private String sql;

    public BancoCaso() {
        BancoDeDados bancoDeDados = new BancoDeDados();
    }

    /**
     * Método que cadastra o caso ao banco de dados.
     *
     * @param novoCaso
     */
    public void cadastrarCaso(Caso novoCaso) {

        sql = "INSERT INTO rbc.caso (descricao, solucao, avaliacao)"
                + "VALUES ('" + novoCaso.getProblema().getTexto() + "'"
                + ",'" + novoCaso.getSolucao().getTexto() + "'"
                + ",'" + novoCaso.getAvaliacao() + "');";

        executeStatement(sql);
    }

    /**
     * Metodo para encontrar o caso mais semelhante no base de dados.
     *
     * @param caso é caso novo.
     * @return Retorna o caso mais selhementa, se houver, senão retorna null.
     */
    public Caso casoSemelhante(Caso caso) {

        sql = "SELECT descricao, solucao, avaliacao "
                + "FROM rbc.caso WHERE MATCH(descricao) AGAINST ('"
                + caso.getProblema().getTexto() + "' IN NATURAL LANGUAGE MODE);";

        try {
            BancoDeDados.PREPAREDSTATEMENT = BancoDeDados.CONNECTION.prepareStatement(sql);
            BancoDeDados.RESULTSET = BancoDeDados.PREPAREDSTATEMENT.executeQuery();

            if (BancoDeDados.RESULTSET.next()) {

                String descricao = BancoDeDados.RESULTSET.getString("descricao");
                String solucao = BancoDeDados.RESULTSET.getString("solucao");
                int avaliacao = BancoDeDados.RESULTSET.getInt("avaliacao");

                Caso casoSemelhante = new Caso(new Entrada(descricao), new Saida(solucao));
                casoSemelhante.setAvaliacao(avaliacao);

                return casoSemelhante;
            }

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }

        return null;
    }

    /**
     * Metodo responsavel pelo retorno do valor do match para a avaliação de
     * aproximação em linguagem natural com o novo caso.
     *
     * @param caso É o caso novo que deve ser tratado.
     * @return Retorna o valor de semelhança (match) entre a descrição dos
     * casos.
     */
    public double casoSemelhanteMatch(Caso caso) {

        double match = 0;

        sql = "SELECT MATCH (descricao) AGAINST ('"
                + caso.getProblema().getTexto() + "'"
                + " IN NATURAL LANGUAGE MODE) AS 'match' FROM rbc.caso "
                + "WHERE MATCH (descricao) AGAINST ('"
                + caso.getProblema().getTexto() + "');";

        try {
            BancoDeDados.PREPAREDSTATEMENT = BancoDeDados.CONNECTION.prepareStatement(sql);
            BancoDeDados.RESULTSET = BancoDeDados.PREPAREDSTATEMENT.executeQuery();

            if (BancoDeDados.RESULTSET.next()) {

                match = BancoDeDados.RESULTSET.getDouble("match");

            }

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return match;
    }

    /**
     * Atualiza os dados do caso no banco de dados.
     *
     * @param caso É o caso que tera os dados modificados.
     */
    public void atualizarCaso(Caso caso) {

        sql = "UPDATE rbc.caso SET descricao = ?, solucao = ?, avaliacao = ? WHERE id = ?;";

        try {
            BancoDeDados.PREPAREDSTATEMENT = BancoDeDados.CONNECTION
                    .prepareStatement(sql);
            BancoDeDados.PREPAREDSTATEMENT.setString(1, caso.getProblema().getTexto());
            BancoDeDados.PREPAREDSTATEMENT.setString(2, caso.getSolucao().getTexto());
            BancoDeDados.PREPAREDSTATEMENT.setInt(3, caso.getAvaliacao());

            BancoDeDados.PREPAREDSTATEMENT.execute();
            BancoDeDados.PREPAREDSTATEMENT.close();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }

    /**
     * Remove o caso do banco de dados da aplicação.
     *
     * @param caso é o caso que será removido
     */
    public void removerCaso(Caso caso) {

        sql = "DELETE FROM rbc.caso WHERE id = ?;";

        try {

            BancoDeDados.PREPAREDSTATEMENT = BancoDeDados.CONNECTION
                    .prepareStatement(sql);
            BancoDeDados.PREPAREDSTATEMENT.setInt(0, 000); // Obs

            BancoDeDados.PREPAREDSTATEMENT.execute();
            BancoDeDados.PREPAREDSTATEMENT.close();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }

    /**
     * Metodo responsavel por realizar o Statement para inserir dados no banco.
     *
     * @param sql Sql a ser usada para inserir os dados.
     */
    private void executeStatement(String sql) {
        try {
            BancoDeDados.STATEMENT = BancoDeDados.CONNECTION
                    .createStatement();

            BancoDeDados.STATEMENT.executeUpdate(sql);

            BancoDeDados.STATEMENT.close();

        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }
}
