package edu.eci.cvds.entities;

public class Usuario {
    private int id;
	private String nombre;
	private String contrasena;
    private String programa;
    private int rol;

	public Usuario() {
		super();
	}

	public Usuario(int id, String nombre, String contrasena, String programa, int rol) {
		this.id = id;
		this.nombre = nombre;
		this.contrasena = contrasena;
        this.programa=programa;
        this.rol=rol;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

	
	@Override
	public String toString() {
		return "Author [id=" + id + ", nombre=" + nombre + ", programa=" + programa + "]";
	}
}
