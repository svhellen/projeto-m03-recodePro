package br.com.vivaviatravel.aplicacao;

import java.util.Scanner;
import br.com.vivaviatravel.dao.ClienteDAO;
import br.com.vivaviatravel.model.Cliente;

public class ClienteApp {
	
	public static void cadastrarCliente(Scanner entrada) throws Exception {
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		System.out.println("Bem vindo a tela de cadastro");
		Cliente c1 = new Cliente();
		
		System.out.println("Para começar digite o seu nome: ");
		c1.setNomeCliente(entrada.nextLine());
		
		System.out.println("Agora digite o seu email: ");
		c1.setEmailCliente(entrada.nextLine());
		
		System.out.println("Defina uma senha pra sua conta: ");
		c1.setSenhaCliente(entrada.nextLine());
		
		System.out.println("Finalmente digite seu telefone: ");
		c1.setTelefoneCliente(entrada.nextLine());
		
		//c1.status();
		//clienteDAO = c1;
		
		clienteDAO.save(c1);
 		System.out.println("Pronto " + c1.getNomeCliente() + ". Sua conta foi cadastrada com sucesso.");
	}
	
	public static void loginCliente(Scanner entrada) throws Exception {
	    ClienteDAO clienteDAO = new ClienteDAO();

	    System.out.println("Olá, digite o seu código de cliente para continuar: ");
	    int codCliente = entrada.nextInt();
	    entrada.nextLine();
	    
	    // cliente by id
	    Cliente cliente = new Cliente();
	    cliente = clienteDAO.getById(codCliente);

		if (cliente != null) {
		    System.out.println("Perfeito, " + cliente.getNomeCliente() + ".");
		    System.out.println("Agora digite a sua senha para confirmar: ");

		    // constantes para o número máximo de tentativas e a senha do cliente
		    final int MAX_TENTATIVAS = 3;
		    final String senhaCliente = cliente.getSenhaCliente();
		    
		    boolean senhaCorreta = false;
		    
		    // loop para tentativas de senha
		    for (int tentativa = 1; tentativa <= MAX_TENTATIVAS; tentativa++) {
		        String senhaDigitada = entrada.nextLine();
		        //entrada.nextLine();
		        if (senhaDigitada.equals(senhaCliente)) {
		            senhaCorreta = true;
		            break; // Senha correta, sair do loop
		        } else {
		            System.out.println("Senha incorreta. Tentativa " + tentativa + "/" + MAX_TENTATIVAS);
		        }
		    }
		    
		    if (senhaCorreta) {
		    	if(codCliente == 1) {
		    		Main.menuAdmin(entrada);
		    	}else {
		    		menuCliente(codCliente, entrada);
		    	}
		    } else {
		        System.out.println("Número máximo de tentativas excedido. Acesso negado.");
		    }
		} else {
		    System.out.println("Cliente não encontrado.");
		}
	}
	
	//funcao menu cliente
	public static void menuCliente(int codCliente, Scanner entrada) throws Exception {
		
		boolean sair = false;
		while(sair == false) {
			System.out.println("--------------------------------------------------");
			System.out.println("                    Boas Vindas                   ");
			System.out.println("--------------------------------------------------");
			System.out.println("Escolha uma das opções abaixo:");
			System.out.println("1 - Ver informações da sua conta");
			System.out.println("2 - Atualizar dados da sua conta");
			System.out.println("3 - Buscar passagens");
			System.out.println("4 - Ver todas as passagens disponíveis");
			System.out.println("5 - Ver reservas vinculadas a sua conta ");
			System.out.println("6 - Fazer uma reserva ");
			System.out.println("7 - Atualizar dados de uma reserva ");
			System.out.println("8 - Cancelar reserva");
			System.out.println("9 - Excluir a sua conta");
			System.out.println("0 - Voltar para o menu principal");
			
			System.out.println("Resposta: ");
			int opcao = entrada.nextInt();
			entrada.nextLine();
			
			switch (opcao) {
			case 1:
				infoCliente(codCliente);
				break;
			case 2:
				atualizarCliente(codCliente, entrada);
				break;
			case 3:
				PassagemApp.buscarPassagens(entrada);
				break;
			case 4:
				PassagemApp.listarPassagens();
				break;
			case 5:
				ReservaApp.listarReservasCliente(codCliente);
				break;
			case 6:
				ReservaApp.fazerReserva(codCliente, entrada);
				break;
			case 7:
				ReservaApp.atualizarReserva(codCliente, entrada);
				break;
			case 8:
				ReservaApp.deleteReserva(entrada);
				break;
			case 9:
				deleteCliente(codCliente, entrada);
				sair = true;
				break;
			case 0:
				System.out.println("Voltando...");
				sair = true;
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		}
	}
	
	public static void atualizarCliente(int codCliente, Scanner entrada) {
	
		System.out.println("Digite a atualização do nome: ");
		String novoNome = entrada.nextLine();
		
		System.out.println("Digite a atualização do email: ");
		String novoEmail = entrada.nextLine();
		
		System.out.println("Digite a atualização da senha: ");
		String novaSenha = entrada.nextLine();
		
		System.out.println("Digite a atualização do telefone: ");
		String novoTelefone = entrada.nextLine();
		//int codCliente = entrada.nextInt();
		
		//atualizar o cliente pelo id
		ClienteDAO clienteDAO = new ClienteDAO();
		
		Cliente c2 = new Cliente();

		c2.setNomeCliente(novoNome);
		c2.setEmailCliente(novoEmail);
		c2.setSenhaCliente(novaSenha);
		c2.setTelefoneCliente(novoTelefone);
		c2.setIdCliente(codCliente);
		
		System.out.println("Dados atualizados: ");
		c2.status();
		
		//confirmacao atualizacao
		System.out.println("Digite 's' para confirmar a atualização dos dados: ");
		String res = entrada.nextLine();
		if(res.equals("s")) {
			clienteDAO.update(c2);
			System.out.println("Dados atualizados com sucesso!");
		}else {
			System.out.println("Dados não atualizados!");
		}
	}
	
	public static void infoCliente(int codCliente) throws Exception {
		ClienteDAO clienteDAO = new ClienteDAO();
	    // cliente by id
	    Cliente cliente = new Cliente();
	    cliente = clienteDAO.getById(codCliente);
	    
	    cliente.status();
	}

	//vizualizacao dos registros de TODOS os clientes no bando de dados
	public static void listarClientes() {
		for(Cliente c : ClienteDAO.getClientes()) {
			System.out.println("cliente: " + c.getNomeCliente());
		}
	}

	//deletar o cliente pelo id
	public static void deleteCliente(int codCliente, Scanner entrada) {
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		//confirmacao exclusao
		System.out.println("Digite 's' para confirmar a exclusão da conta: ");
		String res = entrada.nextLine();
		if(res.equals("s")) {
			//deletar o cliente pelo id
			clienteDAO.deleteById(codCliente);
			System.out.println("Conta excluida com sucesso!");
		}else {
			System.out.println("Conta não excluida.");
		}
	}
	
	
}
