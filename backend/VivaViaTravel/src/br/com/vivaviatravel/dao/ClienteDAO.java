package br.com.vivaviatravel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.vivaviatravel.factory.ConnectionFactory;
import br.com.vivaviatravel.model.Cliente;

public class ClienteDAO {
	
	/*
	 * CRUD
	 * C - create
	 * R - read
	 * U - update
	 * D - delete
	 */

	//create
	public void save(Cliente cliente) {
		
		String sql = "insert into Cliente(nome_cliente, email_cliente, senha_cliente, telefone_cliente) values ( ?, ?, ?, ?)";
		
		Connection conn = null;
		java.sql.PreparedStatement pstm = null;
		
		try {
			//criar conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//criamos uma PreparedStatement para executar uma query
			pstm = (java.sql.PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar os valores que sao esperados pela query
			pstm.setString(1, cliente.getNomeCliente());
			pstm.setString(2, cliente.getEmailCliente());
			pstm.setString(3, cliente.getSenhaCliente());
			pstm.setString(4, cliente.getTelefoneCliente());
			
			//executar a query
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//fechar as conexoes
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if (conn != null) {
					conn.close();
				}	
			}catch ( Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//read
	public Cliente getById(int idCliente) throws Exception {
	    String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
	    Connection conn = null;
	    java.sql.PreparedStatement pstm = null;
	    ResultSet rset = null;
	    Cliente cliente = null;

	    try {
	        conn = ConnectionFactory.createConnectionToMySQL();
	        pstm = (java.sql.PreparedStatement) conn.prepareStatement(sql);
	        pstm.setInt(1, idCliente);
	        rset = pstm.executeQuery();

	        if (rset.next()) {
	            cliente = new Cliente();
	            cliente.setIdCliente(rset.getInt("id_cliente"));
	            cliente.setNomeCliente(rset.getString("nome_cliente"));
	            cliente.setEmailCliente(rset.getString("email_cliente"));
	            cliente.setSenhaCliente(rset.getString("senha_cliente"));
	            cliente.setTelefoneCliente(rset.getString("telefone_cliente"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rset != null) {
	                rset.close();
	            }
	            if (pstm != null) {
	                pstm.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return cliente;
	}

	
	//read
	public static List<Cliente> getClientes(){
		
		String sql = "Select * from Cliente" ;
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//classe que vai recuperar os dados do banco ***SELECT***
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Cliente cliente = new Cliente();
				
				//recupera os dados da tabela cliente
				cliente.setIdCliente(rset.getInt("id_cliente"));
				cliente.setNomeCliente(rset.getString("nome_cliente"));
				cliente.setEmailCliente(rset.getString("email_cliente"));
				cliente.setSenhaCliente(rset.getString("senha_cliente"));
				cliente.setTelefoneCliente(rset.getString("telefone_cliente"));
				
				clientes.add(cliente);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}
				
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return clientes;
	}

	
	//update
	public void update(Cliente cliente) {
		
		String sql = "UPDATE cliente SET nome_cliente = ?, email_cliente = ?, senha_cliente = ?, telefone_cliente = ? "
				+ "Where id_cliente =  ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//criar conexao com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//criar a classe para executar  query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//add os valores para atualizar
			pstm.setString(1, cliente.getNomeCliente());
			pstm.setString(2, cliente.getEmailCliente());
			pstm.setString(3, cliente.getSenhaCliente());
			pstm.setString(4, cliente.getTelefoneCliente());
			
			//id do registro q deseja atualizar
			pstm.setInt(5, cliente.getIdCliente());
			
			//executar a query
			pstm.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteById(int idCliente) {
		
		String sql = "DELETE FROM cliente WHERE id_cliente = ?";
	
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
		conn = ConnectionFactory.createConnectionToMySQL();
		
		pstm = conn.prepareStatement(sql);
		
		pstm.setInt(1, idCliente);
		
		pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}