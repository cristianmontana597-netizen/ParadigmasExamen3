package co.edu.poli.Examen3.modelo;

public class ExamenSangre extends Examen {
private int rh;
private String tipoSangre;

public ExamenSangre(String codigo, String nombrePaciente, String fecha, int costo, int rh, String tipoSangre) {
	super(codigo, nombrePaciente, fecha, costo);
	this.rh = rh;
	this.tipoSangre = tipoSangre;
}
public int getRh() {
	return rh;
}
public void setRh(int rh) {
	this.rh = rh;
}
public String getTipoSangre() {
	return tipoSangre;
}
public void setTipoSangre(String tipoSangre) {
	this.tipoSangre = tipoSangre;
}

}
