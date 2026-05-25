package co.edu.poli.Examen3.modelo;

import java.io.Serializable;

public abstract class Examen implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String codigo;
    private String nombrePaciente;
    private String fecha;
    private int costo;

    public Examen(String codigo, String nombrePaciente, String fecha, int costo) {
    	this.codigo = codigo;
        this.nombrePaciente = nombrePaciente;
        this.fecha = fecha;
        this.costo = costo;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Paciente: " + nombrePaciente + " | Fecha: " + fecha + " | Costo: $" + costo;
    }
}