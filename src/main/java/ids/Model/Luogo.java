package ids.model;

public class Luogo {

    private String nome;
    private double latitudine;
    private double longitudine;

    public Luogo () {

    }

    public Luogo(String nome, double latitudine, double longitudine) {

        this.nome = nome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

}
