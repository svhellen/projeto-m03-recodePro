package br.com.vivaviatravel.aplicacao;

import java.util.Scanner;
import br.com.vivaviatravel.dao.PassagemDAO;
import br.com.vivaviatravel.model.Passagem;

public class PassagemApp {
	
	public static void addPassagem(Scanner entrada) throws Exception {
		PassagemDAO passagemDAO = new PassagemDAO();
		
		System.out.println("Bem vindo a tela de cadastro de passagens");
		System.out.println("Digite os dados a seguir para cadastrar uma nova passagem");
		Passagem passagem1 = new Passagem();
		
		System.out.println("Classe: ");
		passagem1.setClassePassagem(entrada.nextLine());
		
		System.out.println("Origem: ");
		passagem1.setOrigemPassagem(entrada.nextLine());
		
		System.out.println("Destino: ");
		passagem1.setDestinoPassagem(entrada.nextLine());
		
		System.out.println("Data: ");
		passagem1.setDataPassagem(entrada.nextLine());
		
		System.out.println("Preço: ");
		passagem1.setPrecoPassagem(entrada.nextFloat());
		entrada.nextLine();
		
		//passagem1.status();
		
		passagemDAO.save(passagem1);
 		System.out.println("Pronto, passagem para " + passagem1.getDestinoPassagem() + " cadastrada com sucesso.");
	}
	
	//update
	public static void atualizarPassagem(Scanner entrada) {
		
		PassagemDAO passagemDAO = new PassagemDAO();
		Passagem passagem2 = new Passagem();
		
		System.out.println("Digite o id da passagem: ");
		int codPassagem = entrada.nextInt();
		entrada.nextLine();
		
		System.out.println("Digite a atualização da classe: ");
		String novaClasse = entrada.nextLine();
		
		System.out.println("Digite a atualização da origem: ");
		String novaOrigem = entrada.nextLine();
		
		System.out.println("Digite a atualização do destino: ");
		String novoDestino = entrada.nextLine();
		
		System.out.println("Digite a atualização da data: ");
		String novaData = entrada.nextLine();
		
		System.out.println("Digite a atualização do preço: ");
		float novoPreco = entrada.nextFloat();
		entrada.nextLine();
		
		
		passagem2.setClassePassagem(novaClasse);
		passagem2.setOrigemPassagem(novaOrigem);
		passagem2.setDestinoPassagem(novoDestino);
		passagem2.setDataPassagem(novaData);
		passagem2.setPrecoPassagem(novoPreco);
		passagem2.setIdPassagem(codPassagem);
		
		System.out.println("Dados atualizados: ");
		Passagem.status(passagem2);
		
		//confirmacao atualizacao
		System.out.println("Digite 's' para confirmar a atualização dos dados: ");
		String res = entrada.nextLine();
		if(res.equals("s")) {
			passagemDAO.update(passagem2);
			System.out.println("Dados atualizados com sucesso!");
		}else {
			System.out.println("Dados não atualizados!");
		}
	}
	
	//vizualizacao dos registros das passagens por destino
	public static void buscarPassagens(Scanner entrada) throws Exception {
		PassagemDAO passagemDAO = new PassagemDAO();
		System.out.println("Digite a cidade e/ou UF do destino desejado: ");
		String aux = entrada.nextLine();
		String destino = ("%" + aux + "%");
		for(Passagem p : passagemDAO.getPassagensDestino(destino)) {
			Passagem.status(p);
		}
	}

	//vizualizacao dos registros de TODAS as passagens no bando de dados
	public static void listarPassagens() {
		PassagemDAO passagemDAO = new PassagemDAO();
		System.out.println("Dados da passagem:");
		for(Passagem p : passagemDAO.getTodasPassagens()) {
			Passagem.status(p);
		}
	}

	//deletar o passagem pelo id
	public static void deletePassagem(Scanner entrada) {

		PassagemDAO passagemDAO = new PassagemDAO();
		
		System.out.println("Digite o id da passagem: ");
		int codPassagem = entrada.nextInt();
		entrada.nextLine();
		
		//confirmacao exclusao
		System.out.println("Digite 's' para confirmar a exclusão da passagem: ");
		String res = entrada.nextLine();
		if(res.equals("s")) {
			//deletar o passagem pelo id
			passagemDAO.deleteById(codPassagem);
			System.out.println("Passagem excluida com sucesso!");
		}else {
			System.out.println("Passagem não excluida.");
		}
	}
	
	
}
