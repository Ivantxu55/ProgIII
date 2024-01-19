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
    public static Concierto encontrarConciertoPorId(ArrayList<Concierto> conciertos, int id) {
        for (Concierto concierto : conciertos) {
            if (concierto.getId() == id) {
                return concierto;
            }
        }
        return null;
    }
    
}
