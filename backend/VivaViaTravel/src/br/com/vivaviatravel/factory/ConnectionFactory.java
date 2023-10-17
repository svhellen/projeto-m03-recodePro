package br.com.vivaviatravel.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	//nome do usuario mySQL
	private static final String USERNAME = "root";
	
	//senha do mySQL
	private static final String PASSWORD = "12345678";
	
	//dados de caminho, porta e nome da base de dados que ira ser feita a conexao
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/vivaviatravel";
    /**
     * cria um conexao com o banco de dados MySQL utilizando o nome de usuario e senha fornecidos
     * @param username
     * @param senha
     * @return uma conexao com o banco de dados
     * @throws Exception
     */
	
	public static Connection createConnectionToMySQL() throws Exception{
		//faz com que a classe seja carreada pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		
		//cria conexao com o banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			
			return connection;
	}
	
	public static void main(String[] args) throws Exception{
		//recupera uma conexao com o banco de dados
		Connection con = createConnectionToMySQL();
		
		//testa se a conexao é nula
		if(con != null) {
			System.out.println("Conexao obtida com sucesso!" + con);
			con.close();
		}
	}
}
