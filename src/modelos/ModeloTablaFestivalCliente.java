package modelos;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import datos.Concierto;
import datos.Festival;

public class ModeloTablaFestivalCliente extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Festival> festivales;
	private List<String> headers = Arrays.asList("Nombre", "Fecha inicio", "Fecha fin", "Conciertos", "Descripción",
			"Precio", "Generos");

	public ModeloTablaFestivalCliente(List<Festival> festivales) {
		this.festivales = festivales;
	}

	public List<Festival> getFestivales() {
		return this.festivales;
	}

	public void setFestivales(List<Festival> festivales) {
		this.festivales = festivales;
		fireTableDataChanged();
	}

	public int getRowCount() {
		return festivales.size();
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
		Festival festival = festivales.get(row);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		switch (column) {
		case 0:
			return festival.getNombre();
		case 1:
			return dateFormat.format(festival.getFechaInicio());
		case 2:
			return dateFormat.format(festival.getFechaFin());
		case 3:
			return Concierto.toStringPersonalizado(festival.getConciertos());
		case 4:
			return festival.getDescripcion();
		case 5:
			return festival.getPrecio() + "€";
		case 6:
			return festival.getGeneros();
		default:
			return null;
		}
	}
	
    public void eliminarFila(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < festivales.size()) {
            // Elimina la fila de la lista de festivales
            festivales.remove(rowIndex);

            // Notifica a la vista de la tabla que se eliminó una fila
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }

    public Festival getFestival(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < festivales.size()) {
            return festivales.get(rowIndex);
        } else {
        	 return null; 
		}
    }
}