package br.com.vivaviatravel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.vivaviatravel.factory.ConnectionFactory;
import br.com.vivaviatravel.model.Passagem;

public class PassagemDAO {
	
	/*
	 * CRUD
	 * C - create
	 * R - read
	 * U - update
	 * D - delete
	 */

	//create
	public void save(Passagem passagem) {
		
		String sql = "insert into Passagem(classe_passagem, origem_passagem, destino_passagem, data_passagem, preco_passagem) values ( ?, ?, ?, ?, ?)";

		Connection conn = null;
		java.sql.PreparedStatement pstm = null;
		
		try {
			//criar conexao com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//criamos uma PreparedStatement para executar uma query
			pstm = (java.sql.PreparedStatement) conn.prepareStatement(sql);
			
			//adicionar os valores que sao esperados pela query
			pstm.setString(1, passagem.getClassePassagem());
			pstm.setString(2, passagem.getOrigemPassagem());
			pstm.setString(3, passagem.getDestinoPassagem());
			pstm.setString(4, passagem.getDataPassagem());
			pstm.setFloat(5, passagem.getPrecoPassagem());
			
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
	public Passagem getById(int idPassagem) throws Exception {
	    String sql = "SELECT * FROM passagem WHERE id_passagem = ?";
	    Connection conn = null;
	    java.sql.PreparedStatement pstm = null;
	    ResultSet rset = null;
	    Passagem passagem = null;

	    try {
	        conn = ConnectionFactory.createConnectionToMySQL();
	        pstm = (java.sql.PreparedStatement) conn.prepareStatement(sql);
	        pstm.setInt(1, idPassagem);
	        rset = pstm.executeQuery();

	        if (rset.next()) {
	            passagem = new Passagem();
	            passagem.setIdPassagem(rset.getInt("id_passagem"));
				passagem.setClassePassagem(rset.getString("classe_passagem"));
				passagem.setOrigemPassagem(rset.getString("origem_passagem"));
				passagem.setDestinoPassagem(rset.getString("destino_passagem"));
				passagem.setDataPassagem(rset.getString("data_passagem"));
				passagem.setPrecoPassagem(rset.getFloat("preco_passagem"));
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

	    return passagem;
	}

	//read
	public List<Passagem> getPassagensDestino(String destinoPassagem) throws Exception {
	    String sql = "SELECT * FROM Passagem WHERE destino_passagem LIKE ?";
		
		List<Passagem> passagensDestino = new ArrayList<Passagem>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//classe que vai recuperar os dados do banco ***SELECT***
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, destinoPassagem);

			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Passagem passagem = new Passagem();
				
				//recupera os dados da tabela passagem
				passagem.setIdPassagem(rset.getInt("id_passagem"));
				passagem.setClassePassagem(rset.getString("classe_passagem"));
				passagem.setOrigemPassagem(rset.getString("origem_passagem"));
				passagem.setDestinoPassagem(rset.getString("destino_passagem"));
				passagem.setDataPassagem(rset.getString("data_passagem"));
				passagem.setPrecoPassagem(rset.getFloat("preco_passagem"));

				passagensDestino.add(passagem);
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
		
		return passagensDestino;
	}

	//read
	public List<Passagem> getTodasPassagens(){
		
		String sql = "Select * from Passagem" ;
		
		List<Passagem> TodasPassagens = new ArrayList<Passagem>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//classe que vai recuperar os dados do banco ***SELECT***
		ResultSet rset = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Passagem passagem = new Passagem();
				
				//recupera os dados da tabela passagem
				passagem.setIdPassagem(rset.getInt("id_passagem"));
				passagem.setClassePassagem(rset.getString("classe_passagem"));
				passagem.setOrigemPassagem(rset.getString("origem_passagem"));
				passagem.setDestinoPassagem(rset.getString("destino_passagem"));
				passagem.setDataPassagem(rset.getString("data_passagem"));
				passagem.setPrecoPassagem(rset.getFloat("preco_passagem"));

				TodasPassagens.add(passagem);
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
		
		return TodasPassagens;
	}

	//update
	public void update(Passagem passagem) {
		
		String sql = "UPDATE passagem SET classe_passagem = ?, origem_passagem = ?, destino_passagem = ?, data_passagem = ?, preco_passagem = ?"
				+ "Where id_passagem =  ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//criar conexao com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//criar a classe para executar  query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//add os valores para atualizar
			pstm.setString(1, passagem.getClassePassagem());
			pstm.setString(2, passagem.getOrigemPassagem());
			pstm.setString(3, passagem.getDestinoPassagem());
			pstm.setString(4, passagem.getDataPassagem());
			pstm.setFloat(5, passagem.getPrecoPassagem());
			
			//id do registro q deseja atualizar
			pstm.setInt(6, passagem.getIdPassagem());
			
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

	
	//delete
	public void deleteById(int idPassagem) {
		
		String sql = "DELETE FROM passagem WHERE id_passagem = ?";
	
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
		conn = ConnectionFactory.createConnectionToMySQL();
		
		pstm = conn.prepareStatement(sql);
		
		pstm.setInt(1, idPassagem);
		
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