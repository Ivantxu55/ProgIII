package datos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	TipoTarjeta tipo;
	String nombre;
	Long numTarjeta;
	
	public Cliente(TipoTarjeta tipo, String nombre, Long numTarjeta) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.numTarjeta = numTarjeta;
	}

	public TipoTarjeta getTipo() {
		return tipo;
	}

	public void setTipo(TipoTarjeta tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Long getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(Long numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	
	public void store() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("resources/Serial/cliente.bat"));
			Cliente c = this;
			oos.writeObject(c);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
