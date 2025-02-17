package juegochino;

public interface manejarJugadores {
    
   jugador buscarJugador(String usser);
   
   boolean crearJugador (String usser, String password);
   
   jugador logIn(String usser, String password);
   
   boolean cambiarP(jugador usser, String vieja, String nueva);
   
   boolean eliminarCuenta(jugador loggedInUser, String password);
   
   void ordenarJugadoresPorPuntos();
   
   void mostrarRanking();
   
}
