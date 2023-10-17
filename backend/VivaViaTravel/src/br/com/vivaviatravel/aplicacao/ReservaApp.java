package br.com.vivaviatravel.aplicacao;

import java.util.Scanner;
import br.com.vivaviatravel.dao.PassagemDAO;
import br.com.vivaviatravel.dao.ReservaDAO;
import br.com.vivaviatravel.dao.ClienteDAO;
import br.com.vivaviatravel.model.Cliente;
import br.com.vivaviatravel.model.Passagem;
import br.com.vivaviatravel.model.Reserva;

public class ReservaApp {
	
	public static void fazerReserva(int codCliente, Scanner entrada) throws Exception {
		PassagemDAO passagemDAO = new PassagemDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		ReservaDAO reservaDAO = new ReservaDAO();
		
		Cliente cliente1 = clienteDAO.getById(codCliente);
		
		System.out.println("Digite o id da passagem para fazer uma reserva");
		int codPassagem = entrada.nextInt();
		entrada.nextLine();
		
		Passagem passagem1 = passagemDAO.getById(codPassagem);
		Reserva reserva1 = new Reserva(passagem1, cliente1);
		
		reservaDAO.save(reserva1);
 		System.out.println("Prontinho, reserva da passagem para " + passagem1.getDestinoPassagem() + " feita com sucesso.");
		System.out.println("Voltando pro menu inicial...");
	}
	
	public static void atualizarReserva(int codCliente, Scanner entrada) throws Exception {
		PassagemDAO passagemDAO = new PassagemDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		ReservaDAO reservaDAO = new ReservaDAO();
		
		System.out.println("Digite o id da reserva: ");
		int codReserva = entrada.nextInt();
		entrada.nextLine();
		
		System.out.println("Digite a atualização do id da passagem: ");
		int novaPassagem = entrada.nextInt();
		entrada.nextLine();

		Reserva reserva2 = new Reserva();
		Passagem passagem2 = passagemDAO.getById(novaPassagem);
		Cliente cliente2 = clienteDAO.getById(codCliente);
		reserva2 = new Reserva(codReserva, passagem2, cliente2);
		
		//confirmacao atualizacao
		System.out.println("Digite 's' para confirmar a atualização dos dados: ");
		String res = entrada.nextLine();
		if(res.equals("s")) {
			reservaDAO.update(reserva2);
			System.out.println("Dados atualizados com sucesso!");
		}else {
			System.out.println("Dados não atualizados!");
		}
	}
	
	//vizualizacao dos registros de TODAS as passagens no bando de dados
	public static void listarReservasCliente(int codCliente) {
		ReservaDAO reservaDAO = new ReservaDAO();
		for(Reserva r : reservaDAO.getReservasCliente(codCliente)) {
			System.out.println("Dados da reserva nº: " + r.getIdReserva());
			System.out.println("Feita por: " + r.getCliente().getNomeCliente());
			System.out.println("Passagem para: " + r.getPassagem().getDestinoPassagem());
		}
	}

	//vizualizacao dos registros de TODAS as passagens no bando de dados
	public static void listarReservas() {
		ReservaDAO reservaDAO = new ReservaDAO();
		for(Reserva r : reservaDAO.getReservas()) {
			System.out.println("Dados da reserva nº: " + r.getIdReserva());
			System.out.println("Feita por: " + r.getCliente().getNomeCliente());
			System.out.println("Passagem para: " + r.getPassagem().getDestinoPassagem());
		}
	}

	//deletar o reserva pelo id
	public static void deleteReserva(Scanner entrada) throws Exception {

		ReservaDAO reservaDAO = new ReservaDAO();
		System.out.println("Digite o id da reserva: ");
		int codReserva = entrada.nextInt();
		entrada.nextLine();
		
		//confirmacao exclusao
		System.out.println("Digite 's' para confirmar a exclusão/cancelamento da reserva: ");
		String res = entrada.nextLine();
		if(res.equals("s")) {
			//deletar o reserva pelo id
			reservaDAO.deleteById(codReserva);
			System.out.println("Reserva excluida com sucesso!");
		}else {
			System.out.println("Reserva não excluida.");
		}
	}
	
	
	
}
