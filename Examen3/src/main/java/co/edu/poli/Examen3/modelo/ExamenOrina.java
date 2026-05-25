package co.edu.poli.Examen3.modelo;

public class ExamenOrina extends Examen {
    private static final long serialVersionUID = 1L;

    private int ph;
    private String nivelGlucosa;


    public ExamenOrina(String codigo, String nombrePaciente, String fecha, int costo, int ph, String nivelGlucosa) {
		super(codigo, nombrePaciente, fecha, costo);
		this.ph = ph;
		this.nivelGlucosa = nivelGlucosa;
	}


    public int getPh() {
        return ph;
    }

    public void setPh(int ph) {
        this.ph = ph;
    }

    public String getNivelGlucosa() {
        return nivelGlucosa;
    }

    public void setNivelGlucosa(String nivelGlucosa) {
        this.nivelGlucosa = nivelGlucosa;
    }

    @Override
    public String toString() {
        return super.toString() + " | pH: " + ph + " | Glucosa: " + nivelGlucosa;
    }
}