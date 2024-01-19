package datos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Festival {

	protected int id;
	protected String nombre;
	protected Date fechaInicio;
	protected Date fechaFin;
	protected ArrayList<Concierto> conciertos;
	protected String descripcion;
	protected float precio;
	protected ArrayList<Genero> generos;

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

	public Date getFechaInicio() {
	return fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio) {
	this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
	return fechaFin;
	}
	
	public void setFechaFin(Date fechaFin) {
	this.fechaFin = fechaFin;
	}

	public ArrayList<Concierto> getConciertos() {
	return conciertos;
	}
	
	public void setConciertos(ArrayList<Concierto> conciertos) {
	this.conciertos = conciertos;
	}

	public String getDescripcion() {
	return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
	}

	public float getPrecio() {
	return precio;
	}
	
	public void setPrecio(float precio) {
	this.precio = precio;
	}

	public ArrayList<Genero> getGeneros() {
		return generos;
	}
	
	public void setGeneros(ArrayList<Genero> generos) {
		this.generos = generos;
	}
	
	public Festival(int id, String nombre, Date fechaInicio, Date fechaFin, ArrayList<Concierto> conciertos,
			String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.conciertos = conciertos;
		this.descripcion = descripcion;
        Float p = 0f;
        if(this.conciertos != null) { 
        	for(Concierto c: this.conciertos) p =+ c.getPrecio();
        	}
        
		this.precio = p;
				
		this.generos = this.conciertos.stream()
                .map(Concierto::getGenero)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
	}
	
	@Override
	public String toString() {
		return "Festival [id=" + id + ", nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", conciertos=" + conciertos + ", descripcion=" + descripcion + ", precio=" + precio + ", generos="
				+ generos + "]";
	}
	
	public String toStringPers() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String conciertos = "";

		conciertos = conciertos + Concierto.toStringPersonalizado(this.conciertos);
		return nombre + ": ( " + sdf.format(fechaInicio) + " -> " + sdf.format(fechaFin) + ". Conciertos: " + conciertos + ". " + precio + "€";
	}

}
