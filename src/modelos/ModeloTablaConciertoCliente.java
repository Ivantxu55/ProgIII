package modelos;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import datos.Concierto;

public class ModeloTablaConciertoCliente extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Concierto> conciertos;
	private List<String> headers = Arrays.asList("Nombre", "Genero", "Fecha", "Artista", "Descripción", "Precio",
			"Aforo");

	public ModeloTablaConciertoCliente(List<Concierto> conciertos) {
		this.conciertos = conciertos;
	}

	public List<Concierto> getConciertos() {
		return this.conciertos;
	}

	public void setConciertos(List<Concierto> conciertos) {
		this.conciertos = conciertos;
	}

	public int getRowCount() {
		return conciertos.size();
	}

	@Override
	public int getColumnCount() {
		return headers.size();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
	}

	@Override
	public String getColumnName(int column) {
		return headers.get(column);
	}

	public Object getValueAt(int row, int column) {
		Concierto Concierto = conciertos.get(row);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		switch (column) {
		case 0:
			return Concierto.getNombre();
		case 1:
			return Concierto.getGenero();
		case 2:
			return dateFormat.format(Concierto.getFecha());
		case 3:
			return Concierto.getArtista();
		case 4:
			return Concierto.getDescripcion();
		case 5:
			return Concierto.getPrecio();
		case 6:
			return Concierto.getAforo();
		default:
			return null;
		}
	}

    public void removeRow(int rowIndex) {
    	conciertos.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
}
