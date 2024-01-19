package datos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Concierto{
	
		private int id;
		private String nombre;
		private Genero genero;
		private Date fecha;
		private String artista;
		private String descripcion;
		private Float precio;
		private int aforo;
		
		public Concierto(int id, String nombre, Genero genero, Date fecha, String artista, String descripcion, Float precio,
				int aforo) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.genero = genero;
			this.fecha = fecha;
			this.artista = artista;
			this.descripcion = descripcion;
			this.precio = precio;
			this.aforo = aforo;
		}
		
		public Concierto() {
			super();
			this.id = 0;
			this.nombre = "";
			this.genero = Genero.BLUES;
			this.fecha = null;
			this.artista = "";
			this.descripcion = "";
			this.precio = (float) 0;
			this.aforo = 0;
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
		public Genero getGenero() {
			return genero;
		}
		public void setGenero(Genero genero) {
			this.genero = genero;
		}
		public Date getFecha() {
			return fecha;
		}
		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}
		public String getArtista() {
			return artista;
		}
		public void setArtista(String artista) {
			this.artista = artista;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public Float getPrecio() {
			return precio;
		}
		public void setPrecio(Float precio) {
			this.precio = precio;
		}
		public int getAforo() {
			return aforo;
		}
		public void setAforo(int aforo) {
			this.aforo = aforo;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(aforo, artista, descripcion, fecha, genero, id, nombre, precio);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Concierto other = (Concierto) obj;
			return aforo == other.aforo && Objects.equals(artista, other.artista)
					&& Objects.equals(descripcion, other.descripcion) && Objects.equals(fecha, other.fecha)
					&& Objects.equals(genero, other.genero)
					&& id == other.id && Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio);
		}
		
		@Override
		public String toString() {
			return "Concierto [id=" + id + ", nombre=" + nombre + ", genero=" + genero + ", fecha=" + fecha
					+ ", artista=" + artista + ", descripcion=" + descripcion + ", precio=" + precio + ", aforo="
					+ aforo + "]";
		}
		
		public static String toStringPersonalizado(List<Concierto> c) {
			String s = "";
			int i = 1;
			for (Concierto concierto : c) {
				s = s + "\n " + i + "º : " + concierto.getNombre() + " , por " + concierto.getArtista() + " de " + concierto.getGenero().toString() + ".";
				i++;
			}
			return s; 
		}
		
		public String toStringPers() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			return nombre + ", " + artista + ", " + sdf.format(fecha) + ", " + genero + ", " + precio + "€";
		}

}
		
