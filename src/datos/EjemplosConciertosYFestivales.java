//IAG ChatGPT v3.5
//File: fran.martin-opendeusto_20023-10-26_16-54
//Adaptado
package datos;

import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class EjemplosConciertosYFestivales {

    public static void main(String[] args) {
        // Crear 10 ejemplos de conciertos
        ArrayList<Concierto> conciertos = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Concierto concierto = crearConcierto("Concierto " + i, Genero.values()[i - 1], 1000 + i * 100, 40.0f + i);
            conciertos.add(concierto);
        }
    }

        // Crear 7 ejemplos de festivales que contienen conciertos
//        ArrayList<Festival> festivales = new ArrayList<>();
//        for (int i = 1; i <= 7; i++) {
//            ArrayList<Concierto> conciertosFestival = new ArrayList<>();
//            List<Genero> generosFestival = new ArrayList<>();
//
//            for (int j = i; j < i + 3; j++) {
//                Concierto concierto = conciertos.get(j - 1);
//                conciertosFestival.add(concierto);
//
//                // Agregar el género del concierto al festival si no está ya en la lista
//                if (!generosFestival.contains(concierto.getGenero())) {
//                    generosFestival.add(concierto.getGenero());
//                }
//            }
//
//            Festival festival = crearFestival("Festival " + i, 80.0f + i * 10, generosFestival, conciertosFestival);
//            festivales.add(festival);
//        }
//
//        // Mostrar la información de los festivales y conciertos
//        for (Festival festival : festivales) {
//            System.out.println(festival);
//            System.out.println(festival.conciertos);
//        }
//    }

    private static Concierto crearConcierto(String nombre, Genero genero, int aforo, float precio) {
        int id = ThreadLocalRandom.current().nextInt(1, 1000);
        Date fecha = generarFechaAleatoria();
        String artista = "Artista " + id;
        String descripcion = "Descripción " + id;

        return new Concierto(id, nombre, genero, fecha, artista, descripcion, precio, aforo);
    }

//    private static Festival crearFestival(String nombre, float precio, List<Genero> generos, ArrayList<Concierto> conciertos) {
//        int id = ThreadLocalRandom.current().nextInt(1, 1000);
//        Date fechaInicio = generarFechaAleatoria();
//        Date fechaFin = generarFechaAleatoriaDespuesDe(fechaInicio);
//        String descripcion = "Descripción " + id;
//
//        return new Festival(id, nombre, fechaInicio, fechaFin, conciertos, descripcion, precio, null);
//    }

    private static Date generarFechaAleatoria() {
        long minDay = new Date().getTime();
        long maxDay = new Date().getTime() + (365 * 24 * 60 * 60 * 1000L); // Un año en milisegundos
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return new Date(randomDay);
    }

    private static Date generarFechaAleatoriaDespuesDe(Date fecha) {
        long minDay = fecha.getTime();
        long maxDay = fecha.getTime() + (365 * 24 * 60 * 60 * 1000L); // Un año en milisegundos
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return new Date(randomDay);
    }
}
