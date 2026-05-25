package co.edu.poli.Examen3.controlador;

import co.edu.poli.Examen3.modelo.Examen;

public interface OperacionCRUD {
    
    String crear(Examen p);
    
    Examen[] leerTodo();
    
    void serializar(Examen[] examenes, String path, String name);
    
    Examen[] deserializar(String path, String name);
}