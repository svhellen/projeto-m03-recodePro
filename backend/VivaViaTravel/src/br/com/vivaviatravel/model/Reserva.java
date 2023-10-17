package br.com.vivaviatravel.model;

public class Reserva {
    private int idReserva;
    private Passagem passagem;
    private Cliente cliente;

    
    public Reserva() {

    }
    
    public Reserva(Passagem passagem, Cliente cliente) {
        this.passagem = passagem;
        this.cliente = cliente;
    }
    
    public Reserva(int idReserva, Passagem passagem, Cliente cliente) {
        this.idReserva = idReserva;
        this.passagem = passagem;
        this.cliente = cliente;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    
    
}
