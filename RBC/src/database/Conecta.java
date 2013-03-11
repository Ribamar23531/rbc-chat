package database;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
  
public class Conecta {  
  
    private static final String STR_DRIVER = "com.mysql.jdbc.Driver";  
    private static final String DATABASE = "banco";  
    private static final String IP = "localhost";  //"192.168.0.1"; //"clientes";   
    private static final String STR_CON = "jdbc:mysql://" + IP + ":3306/" + DATABASE;  
    private static final String USER = "root";  
    private static final String PASSWORD = "root";  
  
    public static Connection getConexao() throws Exception {  
        Connection con = null;  
        try {  
            Class.forName(STR_DRIVER);  
            con = DriverManager.getConnection(STR_CON, USER, PASSWORD);  
            System.out.println("[ConnectionManager]: Obtendo conexao");  
            return con;  
        } catch (ClassNotFoundException e) {  
            String errorMsg = "Driver nao encontrado";  
            throw new Exception(errorMsg, e);  
        } catch (SQLException e) {  
            String errorMsg = "Erro ao obter a conexao";  
            throw new Exception(errorMsg, e);  
        }  
    }  
  
    public static void closeAll(Connection con) {  
        try {  
            if (con != null) {  
                con.close();  
            }  
        } catch (Exception e) {  
            String errorMsg = "Nao foi possivel fechar a conexao com o banco";  
            System.out.println(errorMsg); 
        }  
    }  
  
    public static void closeAll(Connection con, Statement stmt, ResultSet rs) {  
        try {  
            if (con != null || stmt != null) {  
                closeAll(con, stmt);  
            }  
            if (rs != null) {  
                rs.close();  
            }  
        } catch (Exception e) {  
            String errorMsg = "Nao foi possivel fechar o resultSet do banco";  
            System.out.println(errorMsg);
        }  
    }  
  
    public static void closeAll(Connection con, Statement stmt) {  
        try {  
            if (con != null) {  
                closeAll(con);  
            }  
            if (stmt != null) {  
                stmt.close();  
            }  
        } catch (Exception e) {  
            String errorMsg = "Nao foi possivel fechar a statement do banco";  
            System.out.println(errorMsg);  
        }  
    }  
}  
