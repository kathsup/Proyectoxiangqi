package juegochino;

import javax.swing.JOptionPane;

public class gestionJugadores implements manejarJugadores{
    public jugador[] jugadores;
    public int numjugadores; 
    
    public gestionJugadores() {
        jugadores = new jugador[100];
        numjugadores = 0;
    }
    
   
        private jugador buscarJugador(String usser, int index) {

        if (index >= jugadores.length || jugadores[index] == null) {
            return null;
        }

        if (jugadores[index].getUsername().equals(usser)) {
            return jugadores[index];
        }

        return buscarJugador(usser, index + 1);
    }

    @Override
    public jugador buscarJugador(String usser) {
        return buscarJugador(usser, 0); 
    }
    
    @Override
    public boolean crearJugador (String usser, String password){
    
        if (usser.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error. No debe dejar ningún campo vacío.");
        return false;
    }

    if (password.length() != 5) {
        JOptionPane.showMessageDialog(null, "Error. La contraseña debe tener exactamente 5 caracteres.");
        return false;
    }

    if (numjugadores >= 100) {
        JOptionPane.showMessageDialog(null, "Error. No se pueden crear más usuarios.");
        return false;
    }

    // Verificar si el jugador ya existe
    jugador jugadorExistente = buscarJugador(usser);
    if (jugadorExistente != null && jugadorExistente.getUsername().equals(usser)) {
        JOptionPane.showMessageDialog(null, "Error. El usuario ya está registrado.");
        return false;
    }

    // Crear nuevo jugador
    jugadores[numjugadores] = new jugador(usser, password);
    numjugadores++;
    
    
    // Correcto, abre el menú principal
    return true;
        
    }
    
    @Override
    public jugador logIn(String usser, String password){
    
        jugador j = buscarJugador(usser);
        
        if (usser.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error. No debe dejar ningún campo vacío.");
        return null;
        }
        
        if (j!=null && j.getPassword().equals(password)){
        //correcto y abre menu principal
        return j;
        }
        else{
        JOptionPane.showMessageDialog(null,"Error. usuario o contraseña incorrecto. Vuelva a intentarlo.");
        return null;
        }
        
    }
    
    @Override
    public boolean cambiarP(jugador usser, String vieja, String nueva){
    
        
        if (vieja.isEmpty() || nueva.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error. No debe dejar ningún campo vacío.");
        return false;
        }
      

    if (usser == null) {
        JOptionPane.showMessageDialog(null, "Error. El usuario no existe.");
        return false; 
    }

    
    if (!usser.getPassword().equals(vieja)) {
        JOptionPane.showMessageDialog(null, "Error. La contraseña actual es incorrecta.");
        return false; 
    }

   
    if (nueva.length() != 5) {
        JOptionPane.showMessageDialog(null, "Error. La nueva contraseña debe tener exactamente 5 caracteres.");
        return false; 
    }

    
    usser.setPassword(nueva);
    return true; 
    
    
    }
    
    @Override
    public boolean eliminarCuenta(jugador loggedInUser, String password) {
        
   
    if (loggedInUser == null) {
        JOptionPane.showMessageDialog(null, "Error. No hay un usuario loggeado.");
        return false;
    }

    
    if (!loggedInUser.getPassword().equals(password)) {
        JOptionPane.showMessageDialog(null, "Error. Contraseña incorrecta.");
        return false;
    }

    // Buscar la posición del usuario en el arreglo
    for (int i = 0; i < numjugadores; i++) {
        if (jugadores[i] != null && jugadores[i].getUsername().equals(loggedInUser.getUsername())) {
            jugadores[i] = null;

            // Ajustar el arreglo para mover los otros jugadores
            for (int j = i; j < numjugadores - 1; j++) {
                jugadores[j] = jugadores[j + 1];  // mover los jugadores a la izquierda
            }
            jugadores[numjugadores - 1] = null; // Limpiar el último
            numjugadores--; // Disminuir el contador de jugadores
            JOptionPane.showMessageDialog(null, "Cuenta eliminada con éxito.");

            return true;
        }
    }

    // Si no encuentra el jugador
    JOptionPane.showMessageDialog(null, "Error. No se pudo eliminar el usuario.");
    return false;
}
    
    @Override
     public void ordenarJugadoresPorPuntos() {
        // burbuja
        for (int i = 0; i < numjugadores - 1; i++) {
            for (int j = 0; j < numjugadores - i - 1; j++) {
                // Si el jugador j tiene menos puntos que el jugador j+1, los intercambiamos
                if (jugadores[j].getPuntos() < jugadores[j + 1].getPuntos()) {
                    // Intercambiar jugadores[j] con jugadores[j + 1]
                    jugador temp = jugadores[j];
                    jugadores[j] = jugadores[j + 1];
                    jugadores[j + 1] = temp;
                }
            }
        }
    }

    
    
     private void mostrarRanking(int index) {
    // Caso base
    if (index >= numjugadores) {
        return; // Detener la recursión
    }

    // si no es null se muestra
    if (jugadores[index] != null) {
        System.out.println((index + 1) + " - " + jugadores[index].getUsername() + " - " + jugadores[index].getPuntos() + " puntos");
    }

    //hace que se repita
    mostrarRanking(index + 1);
}
     
    
    @Override
  public void mostrarRanking() {
    ordenarJugadoresPorPuntos();
    mostrarRanking(0); 
}

   
   
   
   
}
    
    