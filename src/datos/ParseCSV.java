//
package datos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ParseCSV {

    public static ArrayList<Concierto> leerConciertos(String archivo) {
        ArrayList<Concierto> conciertos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); 
            int id = 0;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                String nombre = datos[0];
                Genero genero = Genero.valueOf(datos[1].toUpperCase());
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(datos[2]);
                String artista = datos[3];
                String descripcion = datos[4];
                float precio = Float.parseFloat(datos[5]);
                int aforo = Integer.parseInt(datos[6]);

                Concierto concierto = new Concierto(id, nombre, genero, fecha, artista, descripcion, precio, aforo);
                conciertos.add(concierto);
                id++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return conciertos;
    }

    public static ArrayList<Festival> leerFestivales(String archivo, ArrayList<Concierto> conciertos) {
        ArrayList<Festival> festivales = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); 
            int id = 0;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                Date fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(datos[1]);
                Date fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(datos[2]);
                String descripcion = datos[3];
                ArrayList<Concierto> conciertosFestival = new ArrayList<>();
                for (int i = 4; i < datos.length; i++) {
                    String nomConcierto = (datos[i]);
                    Concierto concierto = encontrarConciertoPorNombre(conciertos, nomConcierto);
                    if (concierto != null) {
                        conciertosFestival.add(concierto);
                    }
                }

                

                Festival festival = new Festival(id, nombre, fechaInicio, fechaFin, conciertosFestival, descripcion);
                festivales.add(festival);
                id++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return festivales;
    }

    public static Concierto encontrarConciertoPorNombre(ArrayList<Concierto> conciertos, String nombre) {
        for (Concierto concierto : conciertos) {
            if (concierto.getNombre().equals(nombre)) {
                return concierto;
            }
        }
        return null;
    }
    public static Festival encontrarFestivalPorNombre(ArrayList<Festival> festivales, String nombre) {
    	for(Festival f: festivales) {
    		if(f.getNombre().equals(nombre)) {
    			return f;
    		}
    	}
    	return null;
    }
    
    public static void main(String[] args) {
		ArrayList<Concierto> c = leerConciertos("resources/CSV/Conciertos.csv");
		System.out.println(c);
		ArrayList<Festival> f = leerFestivales("resources/CSV/Festivales.csv", c);
		System.out.println(f);
	}
}
