package database;

import java.sql.SQLException;

public abstract class Operacoes {

	/**
	 * Metodo responsavel por realizar o Statement para inserir dados no banco.
	 * 
	 * @param sql
	 *            Sql a ser usada para inserir os dados.
	 */
	protected void executeStatement(String sql) {
		try {
			BancoDeDados.STATEMENT = BancoDeDados.CONNECTION
					.createStatement();

			try {
				BancoDeDados.STATEMENT.executeUpdate(sql);
			} catch (SQLException sQLException) {
				sQLException.printStackTrace();
			}

			BancoDeDados.STATEMENT.close();

		} catch (SQLException sQLException) {
			sQLException.printStackTrace();
		}
	}
}
