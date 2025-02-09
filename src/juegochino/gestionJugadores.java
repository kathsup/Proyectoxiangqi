package juegochino;

import javax.swing.JOptionPane;

public class gestionJugadores {
    public jugador[] jugadores;
    private int numjugadores; 
    
    public gestionJugadores() {
        jugadores = new jugador[100];
        numjugadores = 0;
    }
    
    public jugador buscarJugador(String usser) {
        for (jugador j : jugadores) {
            if (j != null && j.getUsername().equals(usser)) {
                return j;
            }
        }
        return null;
    }
    
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
    //JOptionPane.showMessageDialog(null, "Jugador creado con éxito.");
    
    // Correcto, abre el menú principal
    return true;
        
    }
    
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
        JOptionPane.showMessageDialog(null,"Error. usser o password incorrecto. Vuelva a intentarlo.");
        return null;
        }
        
    }
    
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
    
}
    
    