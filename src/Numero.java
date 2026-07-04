public class Numero {

    private int id;
    private double numero;
    private double resultado;

    public Numero() {

    }

    public Numero(double numero, double resultado) {
        this.numero = numero;
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }   
}
