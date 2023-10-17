package br.com.vivaviatravel.model;

public class Cliente {
    private int idCliente;
    private String nomeCliente;
    private String emailCliente;
    private String senhaCliente;
    private String telefoneCliente;
    private Reserva reserva;

	public Cliente() {
		
	}
	
    public Cliente(String nomeCliente, String emailCliente, String senhaCliente, String telefoneCliente) {
          this.nomeCliente = nomeCliente;
          this.emailCliente = emailCliente;
          this.senhaCliente = senhaCliente;
          this.telefoneCliente = telefoneCliente;
	}

	public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
    
    public void status() {
        //System.out.println("Dados do cliente: ");
        System.out.println("Id: " + this.getIdCliente());
        System.out.println("Nome: " + this.getNomeCliente());
        System.out.println("Email: " + this.getEmailCliente());
        System.out.println("Senha: " + this.getSenhaCliente());
        System.out.println("Telefone: " + this.getTelefoneCliente());
    }

    
}
