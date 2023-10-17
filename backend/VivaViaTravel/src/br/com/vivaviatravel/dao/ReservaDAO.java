package br.com.vivaviatravel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.vivaviatravel.factory.ConnectionFactory;
import br.com.vivaviatravel.model.Cliente;
import br.com.vivaviatravel.model.Passagem;
import br.com.vivaviatravel.model.Reserva;

public class ReservaDAO {
	
	/*
	 * CRUD
	 * C - create
	 * R - read
	 * U - update
	 * D - delete
	 */

	
	//create
	public void save(Reserva reserva) {
		
		String sql = "insert into Reserva(fk_Passagem_id, fk_Cliente_id) values ( ?, ?)";
		
		Connection conn = null;
		java.sql.PreparedStatement pstm = null;
		
		try {
			//criar conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//criamos uma PreparedStatement para executar uma query
			pstm = (java.sql.PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar os valores que sao esperados pela query
			pstm.setInt(1, reserva.getPassagem().getIdPassagem());
			pstm.setInt(2, reserva.getCliente().getIdCliente());
			
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
	
	public static Reserva getById(int idReserva) throws Exception {
	    String sql = "SELECT * FROM reserva WHERE id_reserva = ?";
	    Connection conn = null;
	    java.sql.PreparedStatement pstm = null;
	    ResultSet rset = null;
	    Reserva reserva = null;
	    Cliente cliente = null;
	    Passagem passagem = null;

	    try {
	        conn = ConnectionFactory.createConnectionToMySQL();
	        pstm = (java.sql.PreparedStatement) conn.prepareStatement(sql);
	        pstm.setInt(1, idReserva);
	        rset = pstm.executeQuery();

	        if (rset.next()) {
	            reserva = new Reserva();
	            reserva.setIdReserva(rset.getInt("id_reserva"));
	            
	            cliente = new Cliente();
	            cliente.setNomeCliente(rset.getString("nome_cliente"));
	            cliente.setEmailCliente(rset.getString("email_cliente"));
	            cliente.setTelefoneCliente(rset.getString("telefone_cliente"));
	            
	            passagem = new Passagem();
	            passagem.setIdPassagem(rset.getInt("id_passagem"));
				passagem.setClassePassagem(rset.getString("classe_passagem"));
				passagem.setOrigemPassagem(rset.getString("origem_passagem"));
				passagem.setDestinoPassagem(rset.getString("destino_passagem"));
				passagem.setDataPassagem(rset.getString("data_passagem"));
				passagem.setPrecoPassagem(rset.getFloat("preco_passagem"));
	            
				reserva.setCliente(cliente);
				reserva.setPassagem(passagem);
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

	    return reserva;
	}

	
	public List<Reserva> getReservasCliente(int codCliente){
		
		String sql = "select r.*, c.*, p.*  \r\n"
				+ "from reserva as r \r\n"
				+ "join Passagem as p on r.fk_Passagem_id = p.id_passagem join Cliente as c on r.fk_Cliente_id = c.id_cliente\r\n"
				+ "where r.fk_Cliente_id = ?";
		
		List<Reserva> reservas = new ArrayList<Reserva>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//classe que vai recuperar os dados do banco ***SELECT***
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, codCliente);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Reserva reserva = new Reserva();
	            reserva.setIdReserva(rset.getInt("id_reserva"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setNomeCliente(rset.getString("nome_cliente"));
	            cliente.setEmailCliente(rset.getString("email_cliente"));
	            cliente.setTelefoneCliente(rset.getString("telefone_cliente"));
	            
	            Passagem passagem = new Passagem();
	            passagem.setIdPassagem(rset.getInt("id_passagem"));
				passagem.setOrigemPassagem(rset.getString("origem_passagem"));
				passagem.setDestinoPassagem(rset.getString("destino_passagem"));
				passagem.setClassePassagem(rset.getString("classe_passagem"));
				passagem.setDataPassagem(rset.getString("data_passagem"));
				passagem.setPrecoPassagem(rset.getFloat("preco_passagem"));
	            
				reserva.setCliente(cliente);
				reserva.setPassagem(passagem);
				
				reservas.add(reserva);
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
		
		return reservas;
	}

	
	public List<Reserva> getReservas(){
		
		String sql = "select r.*, c.*, p.* \r\n"
				+ "from reserva as r \r\n"
				+ "join Passagem as p on r.fk_Passagem_id = p.id_passagem join Cliente as c on r.fk_Cliente_id = c.id_cliente";
		
		List<Reserva> reservas = new ArrayList<Reserva>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//classe que vai recuperar os dados do banco ***SELECT***
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Reserva reserva = new Reserva();
	            reserva.setIdReserva(rset.getInt("id_reserva"));
	            
	            Cliente cliente = new Cliente();
	            cliente.setNomeCliente(rset.getString("nome_cliente"));
	            cliente.setEmailCliente(rset.getString("email_cliente"));
	            cliente.setTelefoneCliente(rset.getString("telefone_cliente"));
	            
	            Passagem passagem = new Passagem();
	            passagem.setIdPassagem(rset.getInt("id_passagem"));
				passagem.setClassePassagem(rset.getString("classe_passagem"));
				passagem.setOrigemPassagem(rset.getString("origem_passagem"));
				passagem.setDestinoPassagem(rset.getString("destino_passagem"));
				passagem.setDataPassagem(rset.getString("data_passagem"));
				passagem.setPrecoPassagem(rset.getFloat("preco_passagem"));
	            
				reserva.setCliente(cliente);
				reserva.setPassagem(passagem);
				
				reservas.add(reserva);
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
		
		return reservas;
	}

	public void update(Reserva reserva) {
		
		String sql = "UPDATE reserva SET fk_Passagem_id = ?, fk_Cliente_id = ? WHERE id_reserva =  ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//criar conexao com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//criar a classe para executar  query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//add os valores para atualizar
			pstm.setInt(1, reserva.getPassagem().getIdPassagem());
			pstm.setInt(2, reserva.getCliente().getIdCliente());
			
			//id do registro q deseja atualizar
			pstm.setInt(3, reserva.getIdReserva());
			
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

	public void deleteById(int idReserva) {
		
		String sql = "DELETE FROM reserva WHERE id_reserva = ?";
	
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
		conn = ConnectionFactory.createConnectionToMySQL();
		
		pstm = conn.prepareStatement(sql);
		
		pstm.setInt(1, idReserva);
		
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