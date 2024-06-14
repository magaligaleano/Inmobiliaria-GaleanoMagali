package ar.edu.unlam.pb1;

public class Cliente implements Comparable<Cliente>{

	private String nombre;
	private String apellido;
	private Integer dni;
	
	public Cliente(String nombre, String apellido, Integer dni) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + "]";
	}
	@Override
	public int compareTo(Cliente o) {
		return this.getDni() - o.getDni();
	}
	
	
	
}
