package juegochino;

public class main {
    public static void main(String[] args) {
        
        gestionJugadores gestorJugadores = new gestionJugadores();
        inicio in = new inicio(gestorJugadores); 
        in.setVisible(true);
        
        
    }
}

