package br.com.vivaviatravel.model;

public class Passagem {
    private int idPassagem;
    private String classePassagem;
    private String origemPassagem;
    private String destinoPassagem;
    private String dataPassagem;
    private float precoPassagem;
    private Reserva reserva;

    public Passagem() {
    	
    }
    
    public Passagem(int idPassagem, String classePassagem, String origemPassagem, String destinoPassagem, String dataPassagem, float precoPassagem, Reserva reserva) {
        this.idPassagem = idPassagem;
        this.classePassagem = classePassagem;
        this.origemPassagem = origemPassagem;
        this.destinoPassagem = destinoPassagem;
        this.dataPassagem = dataPassagem;
        this.precoPassagem = precoPassagem;
        this.reserva = reserva;
    }

    public int getIdPassagem() {
        return idPassagem;
    }

    public void setIdPassagem(int idPassagem) {
        this.idPassagem = idPassagem;
    }

    public String getClassePassagem() {
        return classePassagem;
    }

    public void setClassePassagem(String classePassagem) {
        this.classePassagem = classePassagem;
    }

    public String getOrigemPassagem() {
        return origemPassagem;
    }

    public void setOrigemPassagem(String origemPassagem) {
        this.origemPassagem = origemPassagem;
    }

    public String getDestinoPassagem() {
        return destinoPassagem;
    }

    public void setDestinoPassagem(String destinoPassagem) {
        this.destinoPassagem = destinoPassagem;
    }

    public String getDataPassagem() {
        return dataPassagem;
    }

    public void setDataPassagem(String dataPassagem) {
        this.dataPassagem = dataPassagem;
    }

    public float getPrecoPassagem() {
        return precoPassagem;
    }

    public void setPrecoPassagem(float precoPassagem) {
        this.precoPassagem = precoPassagem;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

	public static void status(Passagem p) {
		System.out.println("|   Id: " + p.getIdPassagem() + " | Origem: " + p.getOrigemPassagem() + " | Destino: " + p.getDestinoPassagem() + " | Classe: " + p.getClassePassagem() + " | Data: " + p.getDataPassagem() + " | Preço: " + p.getPrecoPassagem() + "   |");
	}
    
    
}
