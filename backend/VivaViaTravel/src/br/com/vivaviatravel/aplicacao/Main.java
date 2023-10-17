package br.com.vivaviatravel.aplicacao;

import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) throws Exception {

		menuInicial();
		
	}

	//funcao menu
	public static void menuInicial() throws Exception {
		
		Scanner entrada = null;
		int opcao = 0;
		boolean sair = false;
		
		//try {
			entrada = new Scanner(System.in);
		while(sair == false) {
			System.out.println("--------------------------------------------------");
			System.out.println("                   Menu Inicial                   ");
			System.out.println("--------------------------------------------------");
			System.out.println("Escolha uma das opções abaixo:");
			System.out.println("1 - Fazer seu cadastro");
			System.out.println("2 - Entrar na sua conta");
			System.out.println("3 - Apenas ver passagens");
			System.out.println("4 - Entrar como Admin");
			System.out.println("0 - Sair");
			
			System.out.println("Resposta: ");
			opcao = entrada.nextInt();
			entrada.nextLine();
			//opcao = escolha(opcao);
			
			switch (opcao) {
			case 1:
				ClienteApp.cadastrarCliente(entrada);
				break;
			case 2:
				ClienteApp.loginCliente(entrada);
				break;
			case 3:
				PassagemApp.listarPassagens();
				break;
			case 4:
				menuAdmin(entrada);
				sair = true;
				break;
			case 0:
				sair = true;
				System.out.println("Saindo...");
				System.out.println("Até a próxima! ");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		}
		//}catch(Exception e) {
			////
		//}finally {
		//	if(entrada != null) {
		//		entrada.close();
		//	}
		//}
		entrada.close();

		
	}
	
	//funcao menu admin
	public static void menuAdmin(Scanner entrada) throws Exception {
		
		//Scanner entrada = null;
		int opcao2 = 0;
		boolean sair = false;
		
		while(sair == false) {
			System.out.println("--------------------------------------------------");
			System.out.println("                 Menu Admin                  ");
			System.out.println("--------------------------------------------------");
			System.out.println("Escolha uma das opções abaixo:");
			System.out.println("1 - Adicionar passagem");
			System.out.println("2 - Atualizar passagem");
			System.out.println("3 - Listar clientes");
			System.out.println("4 - Listar reservas");
			System.out.println("5 - Listar passagens");
			System.out.println("6 - Excluir passagem");
			System.out.println("0 - Voltar para o menu principal");
			
			System.out.println("Resposta: ");
			opcao2 = entrada.nextInt();
			entrada.nextLine();
			
			switch (opcao2) {
			case 1:
				PassagemApp.addPassagem(entrada);
				break;
			case 2:
				PassagemApp.atualizarPassagem(entrada);
				break;
			case 3:
				ClienteApp.listarClientes();
				break;
			case 4:
				ReservaApp.listarReservas();
				break;
			case 5:
				PassagemApp.listarPassagens();
				break;
			case 6:
				PassagemApp.deletePassagem(entrada);
				break;
			case 0:
				sair = true;
				menuInicial();
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		}
	
	}
	
	
}
