package co.edu.poli.Examen3.controlador;

import co.edu.poli.Examen3.modelo.Examen;
import co.edu.poli.Examen3.modelo.ExamenOrina;
import co.edu.poli.Examen3.controlador.OperacionCRUD;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController implements OperacionCRUD {

   
    @FXML private TextField txtCodigoExamen;
    @FXML private TextField txtNombrePaciente;
    @FXML private TextField txtCosto;
    @FXML private ComboBox<String> cmbNivelGlucosa;
    @FXML private TextField txtPh;
    @FXML private TextArea txtAreaMensajes; 

    private Examen[] baseDeDatos;
    private int contadorRegistros;

    private final String RUTA_DESTINO = "./";
    private final String NOMBRE_DEL_ARCHIVO = "examenes.dat";

    public PrimaryController() {
        this.baseDeDatos = new Examen[100];
        this.contadorRegistros = 0;
    }

    @FXML
    public void initialize() {
      
        if (cmbNivelGlucosa != null && cmbNivelGlucosa.getItems().isEmpty()) {
            cmbNivelGlucosa.getItems().addAll("Bajo", "Medio", "Alto");
        }
    }

   
    @Override
    public String crear(Examen p) {
        if (p == null) {
            return "[Error] Registro inválido.";
        }
        if (contadorRegistros >= baseDeDatos.length) {
            baseDeDatos = Arrays.copyOf(baseDeDatos, baseDeDatos.length * 2);
        }
        baseDeDatos[contadorRegistros] = p;
        contadorRegistros++;
        return "[Éxito] Examen añadido correctamente a la memoria local.";
    }

    @Override
    public Examen[] leerTodo() {
        return Arrays.copyOf(baseDeDatos, contadorRegistros);
    }

    @Override
    public void serializar(Examen[] examenes, String path, String name) {
        File archivo = new File(path, name);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(examenes);
        } catch (IOException e) {
            throw new RuntimeException("Error al serializar: " + e.getMessage(), e);
        }
    }

    @Override
    public Examen[] deserializar(String path, String name) {
        File archivo = new File(path, name);
        if (!archivo.exists()) {
            return new Examen[0];
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Examen[] datosRecuperados = (Examen[]) ois.readObject();
            this.baseDeDatos = Arrays.copyOf(datosRecuperados, Math.max(100, datosRecuperados.length * 2));
            this.contadorRegistros = datosRecuperados.length;
            return datosRecuperados;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error al deserializar: " + e.getMessage(), e);
        }
    }

  
    @FXML
    private void handleGuardar() {
        try {
            String codigo = txtCodigoExamen.getText().trim();
            String nombre = txtNombrePaciente.getText().trim();
            int costo = Integer.parseInt(txtCosto.getText().trim());
            String nivelGlucosa = cmbNivelGlucosa.getValue();
            int ph = Integer.parseInt(txtPh.getText().trim());
            String fechaHoy = LocalDate.now().toString();

            if (codigo.isEmpty() || nombre.isEmpty() || nivelGlucosa == null) {
                txtAreaMensajes.setText("Mensajes:\n[Error] Todos los campos del formulario son obligatorios.");
                return;
            }

            ExamenOrina nuevoExamen = new ExamenOrina(codigo, nombre, fechaHoy, costo, ph, nivelGlucosa);
            String respuestaMsg = crear(nuevoExamen);
            
            txtAreaMensajes.setText("Mensajes:\n" + respuestaMsg);
            handleLimpiar();

        } catch (NumberFormatException e) {
            txtAreaMensajes.setText("Mensajes:\n[Error] El Costo y el PH deben ser números enteros.");
        }
    }

    @FXML
    private void handleListar() {
        Examen[] examenesActuales = leerTodo();

        if (examenesActuales.length == 0) {
            txtAreaMensajes.setText("Mensajes:\nNo se han encontrado registros en el sistema.");
            return;
        }

        StringBuilder sb = new StringBuilder("--- REGISTROS ACTUALES EN EL SISTEMA ---\n");
        for (Examen examen : examenesActuales) {
            if (examen != null) {
                sb.append(examen.toString()).append("\n----------------------------------------\n");
            }
        }
        txtAreaMensajes.setText(sb.toString());
    }

    @FXML
    private void handleSerializar() {
        try {
            Examen[] datosASerializar = leerTodo();
            serializar(datosASerializar, RUTA_DESTINO, NOMBRE_DEL_ARCHIVO);
            txtAreaMensajes.setText("Mensajes:\n[Éxito] Archivo serializado y respaldado en: " + RUTA_DESTINO + NOMBRE_DEL_ARCHIVO);
        } catch (Exception e) {
            txtAreaMensajes.setText("Mensajes:\n[Error] Falla al serializar: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeserializar() {
        try {
            Examen[] recuperados = deserializar(RUTA_DESTINO, NOMBRE_DEL_ARCHIVO);
            txtAreaMensajes.setText("Mensajes:\n[Éxito] Copia restaurada desde el disco. Registros cargados: " + recuperados.length);
        } catch (Exception e) {
            txtAreaMensajes.setText("Mensajes:\n[Error] Falla al deserializar: " + e.getMessage());
        }
    }

    private void handleLimpiar() {
        txtCodigoExamen.clear();
        txtNombrePaciente.clear();
        txtCosto.clear();
        txtPh.clear();
        cmbNivelGlucosa.setValue(null);
    }
}