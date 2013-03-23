package database;

import java.sql.SQLException;
import java.util.ArrayList;

import engine.Caso;

public class BancoCaso extends Operacoes {
	
	private String sql;

	/**
	 * M�todo que cadastra o caso ao banco de dados.
	 * 
	 * @param novoCaso
	 */
	public void cadastrarCaso(Caso novoCaso) {

		sql = "INSERT INTO rbc.caso (id, descricao, solucao, avaliacao)"
				+ "VALUES ('" + novoCaso.getProblema().getTexto() + "', " 
				+ "'" + novoCaso.getSolucao().getTexto()
				+ "', "	+ novoCaso.getAvaliacao() + "')";

		executeStatement(sql);
	}

	/**
	 * Lista todos os caso cadastrados no sistema.
	 * 
	 * @return um ArrayList de String com descricao-id dos casos
	 *         cadastrados.
	 */
	public ArrayList<Object> listarCaso() {

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
	 *            � o caso que ter� os dados modificados.
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
	 * Remove o caso do banco de dados da aplica��o.
	 * 
	 * @param caso
	 *            � o caso que ser� removido
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

}
