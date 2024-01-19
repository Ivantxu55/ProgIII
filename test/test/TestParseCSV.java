package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import datos.Concierto;
import datos.Festival;
import datos.Genero;
import datos.ParseCSV;

public class TestParseCSV {
	private static ArrayList<Concierto> conciertos;
	private static ArrayList<Festival> festivales;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conciertos = ParseCSV.leerConciertos("resources/CSV/Conciertos.csv");
		festivales = ParseCSV.leerFestivales("resources/CSV/Festivales.csv", conciertos);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testConciertos() {
		//La lista de conciertos no es null
		assertNotNull(conciertos);
		
		//El tamaño de la lista de conciertos es 4
		assertEquals(4, conciertos.size()); 
		
		//El precio del segundo concierto es 25 €
		assertEquals(Float.parseFloat("25"), conciertos.get(1).getPrecio(), 0); 
		
		//La descripción del último concierto es "Experiencia unica de Jazz"
		assertEquals("Experiencia unica de Jazz", conciertos.get(conciertos.size()-1).getDescripcion()); 
		
		//El género del primer concierto es Pop
		assertEquals(Genero.POP, conciertos.get(0).getGenero());
		
	}
	
	@Test
	public void testFestivales() {
		//La lista de festivales no es null
		assertNotNull(festivales);
		
		//El tamaño de la lista de festivales es 2
		assertEquals(2, festivales.size()); 

		//El primer festival se compone de 3 conciertos
		assertEquals(3, festivales.get(0).getConciertos().size());
		
		//El nombre del segundo festival es "Festival2"
		assertEquals("Festival2", festivales.get(1).getNombre());
		
		//La lista de generos del primer festival es POP, ROCK y ELECTRONICA
		assertEquals(new ArrayList<Genero>(Arrays.asList(Genero.POP, Genero.ROCK, Genero.ELECTRONICA)), festivales.get(0).getGeneros());
		
	}

}
