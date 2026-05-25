package co.edu.poli.Examen3.modelo;

public class ExamenRayosX extends Examen {
private int Radiacion;
private String zonaCuerpo;

public ExamenRayosX(String codigo, String nombrePaciente, String fecha, int costo, int radiacion, String zonaCuerpo) {
	super(codigo, nombrePaciente, fecha, costo);
	Radiacion = radiacion;
	this.zonaCuerpo = zonaCuerpo;
}
public int getRadiacion() {
	return Radiacion;
}
public void setRadiacion(int radiacion) {
	Radiacion = radiacion;
}
public String getZonaCuerpo() {
	return zonaCuerpo;
}
public void setZonaCuerpo(String zonaCuerpo) {
	this.zonaCuerpo = zonaCuerpo;
}

}
