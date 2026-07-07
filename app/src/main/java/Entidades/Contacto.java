package Entidades;

import androidx.annotation.NonNull;

public class Contacto {
    /// Datos Primer formulario: Agregar Contactos
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String tipoTelefono;
    private String email;
    private String tipoEmail;
    private String direccion;
    private String fechaNacimiento;


    /// Datos Segundo formulario: Mas Datos Contacto
    private String nivelEstudios;   // valor del RadioGroup
    private int interesDeporte;
    private int interesMusica;
    private int interesArte;
    private int interesTecnologia;
    private int recibirInformacion; // valor del Switch

    public Contacto() {
    }

    public Contacto(int id, String nombre, String apellido, String telefono, String tipoTelefono, String email, String tipoEmail, String direccion, String fechaNacimiento, String nivelEstudios, int interesDeporte, int interesMusica, int interesArte, int interesTecnologia, int recibirInformacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipoTelefono = tipoTelefono;
        this.email = email;
        this.tipoEmail = tipoEmail;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.nivelEstudios = nivelEstudios;
        this.interesDeporte = interesDeporte;
        this.interesMusica = interesMusica;
        this.interesArte = interesArte;
        this.interesTecnologia = interesTecnologia;
        this.recibirInformacion = recibirInformacion;
    }

    // --- GETTERS ---
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getTipoTelefono() { return tipoTelefono; }
    public String getEmail() { return email; }
    public String getTipoEmail() { return tipoEmail; }
    public String getDireccion() { return direccion; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getNivelEstudios() { return nivelEstudios; }
    public int getInteresDeporte() { return interesDeporte; }
    public int getInteresMusica() { return interesMusica; }
    public int getInteresArte() { return interesArte; }
    public int getInteresTecnologia() { return interesTecnologia; }
    public int getRecibirInformacion() { return recibirInformacion; }

    // --- SETTERS ---
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setTipoTelefono(String tipoTelefono) { this.tipoTelefono = tipoTelefono; }
    public void setEmail(String email) { this.email = email; }
    public void setTipoEmail(String tipoEmail) { this.tipoEmail = tipoEmail; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setNivelEstudios(String nivelEstudios) { this.nivelEstudios = nivelEstudios; }
    public void setInteresDeporte(int interesDeporte) { this.interesDeporte = interesDeporte; }
    public void setInteresMusica(int interesMusica) { this.interesMusica = interesMusica; }
    public void setInteresArte(int interesArte) { this.interesArte = interesArte; }
    public void setInteresTecnologia(int interesTecnologia) { this.interesTecnologia = interesTecnologia; }
    public void setRecibirInformacion(int recibirInformacion) { this.recibirInformacion = recibirInformacion; }

    @NonNull
    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipoTelefono='" + tipoTelefono + '\'' +
                ", email='" + email + '\'' +
                ", tipoEmail='" + tipoEmail + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", nivelEstudios='" + nivelEstudios + '\'' +
                ", interesDeporte=" + interesDeporte +
                ", interesMusica=" + interesMusica +
                ", interesArte=" + interesArte +
                ", interesTecnologia=" + interesTecnologia +
                ", recibirInformacion=" + recibirInformacion +
                '}';
    }
}
