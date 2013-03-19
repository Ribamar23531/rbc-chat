package database;

import java.sql.SQLException;
import java.util.ArrayList;

public class BancoCaso extends Operacoes {
	
	private String sql;

	/**
	 * M�todo que cadastra o caso ao banco de dados.
	 * 
	 * @param novoCaso
	 */
	public void cadastrarCaso(Object novoCaso) {

		sql = "INSERT INTO rbc.caso (id, descricao, solucao, avaliacao)"
				+ "VALUES ('" + "')";

		executeStatement(sql);
	}

	/**
	 * Lista todos os caso cadastrados no sistema.
	 * 
	 * @return um ArrayList de String com nome-matricula dos usu�rios
	 *         cadastrados.
	 */
	public ArrayList<Object> listarUsuario() {

		ArrayList<Object> listaCaso = new ArrayList<>();

		sql = "SELECT descricao FROM rbc.caso;";

		try {
			BancoDeDados.STATEMENT = BancoDeDados.CONNECTION
					.createStatement();
			BancoDeDados.RESULTSET = BancoDeDados.STATEMENT
					.executeQuery(sql);

			try {
				while (BancoDeDados.RESULTSET.next()) {

					// Variavel tempor�ria para a consulta dos dados
					String consulta = BancoDeDados.RESULTSET
							.getString("descricao")
							+ " - "
							+ BancoDeDados.RESULTSET.getInt("id");

					// Adicionando nome-matricula ao Array de String
					listaCaso.add(consulta);

				}
			} catch (SQLException sQLException) {
				sQLException.printStackTrace();
			}

		} catch (SQLException sQLException) {
			sQLException.printStackTrace();
		}
		return listaCaso;
	}

	/**
	 * Atualiza os dados do caso no banco de dados.
	 * 
	 * @param caso
	 *            � o objeto caso que ter� os dados modificados.
	 */
	public void atualizarCaso(Object caso) {

		sql = "UPDATE rbc.caso SET descricao = ?, solucao = ?, avaliacao = ? WHERE id = ?;";

		try {
			BancoDeDados.PREPAREDSTATEMENT = BancoDeDados.CONNECTION
					.prepareStatement(sql);
			BancoDeDados.PREPAREDSTATEMENT.setString(1, "descricao");
			BancoDeDados.PREPAREDSTATEMENT.setString(2, "solucao");
			BancoDeDados.PREPAREDSTATEMENT.setInt(3, 00); // Obs

			BancoDeDados.PREPAREDSTATEMENT.execute();
			BancoDeDados.PREPAREDSTATEMENT.close();
		} catch (SQLException sQLException) {
			sQLException.printStackTrace();
		}
	}

	/**
	 * Remove o caso do banco de dados da aplica��o.
	 * 
	 * @param caso
	 *            � o usu�rio que ser� removido
	 */
	public void removerUsuario(Object caso) {

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

}