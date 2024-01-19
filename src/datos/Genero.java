//IAG Google Bard
//FILE: ivan.casado-opendeusto.txt
//ADAPTADO

package datos;

import java.awt.Color;

public enum Genero {
	BLUES(Color.BLUE),
    ELECTRONICA(Color.CYAN),
    HIP_HOP(Color.ORANGE),
    METAL(Color.DARK_GRAY),
    REGGAE(Color.GREEN),
    REGGAETON(Color.MAGENTA),
    FOLK(Color.YELLOW),
    CLASICA(Color.LIGHT_GRAY),
    LATINA(Color.PINK),
    POP(Color.LIGHT_GRAY),
    ROCK(Color.RED),
    DANCE(Color.YELLOW),
    JAZZ(Color.GREEN),
    TRAP(Color.ORANGE),
    DISCO(Color.MAGENTA);
	
    private final Color color;

    Genero(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
