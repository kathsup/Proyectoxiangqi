package juegochino;

import javax.swing.JOptionPane;

public class juego {
private piezas [][] tablero; 
public static boolean esTurnoRojo=true;
private boolean juegoTerminado = false; 
private boolean retiroPress = false;
public int reyR =1; 
public int reyN =1; 
private board btab;
private jugador jugadorRojo;
private jugador jugadorNegro;

public juego(board btab, jugador jugadorRojo, jugador jugadorNegro){
this.btab=btab;
this.jugadorRojo = jugadorRojo;
this.jugadorNegro = jugadorNegro;
esTurnoRojo = true;
}

public void inicializarTablero(){

tablero = new piezas[10][9];
tablero[0][0]=new carroGuerra(0,0,true);
tablero[0][1]=new caballo(0,1,true);
tablero[0][2]= new elefante(0,2,true);
tablero[0][3]=new oficial(0,3,true);
tablero[0][4]=new general(0,4,true);
tablero[0][5]=new oficial(0,5,true);
tablero[0][6]= new elefante(0,6,true);
tablero[0][7]=new caballo(0,7,true);
tablero[0][8]=new carroGuerra(0,8,true);
tablero[2][1]=new canon(2,1,true);
tablero[2][7]=new canon(2,7,true);
tablero[3][0]=new soldado(3,0,true);
tablero[3][2]=new soldado(3,2,true);
tablero[3][4]=new soldado(3,4,true);
tablero[3][6]=new soldado(3,6,true);
tablero[3][8]=new soldado(3,8,true);

tablero[6][0]=new soldado(6,0,false);
tablero[6][2]=new soldado(6,2,false);
tablero[6][4]=new soldado(6,4,false);
tablero[6][6]=new soldado(6,6,false);
tablero[6][8]=new soldado(6,8,false);
tablero[7][1]=new canon(7,1,false);
tablero[7][7]=new canon(7,7,false);
tablero[9][0]=new carroGuerra(9,0,false);
tablero[9][1]=new caballo(9,1,false);
tablero[9][2]= new elefante(9,2,false);
tablero[9][3]=new oficial(9,3,false);
tablero[9][4]=new general(9,4,false);
tablero[9][5]=new oficial(9,5,false);
tablero[9][6]= new elefante(9,6,false);
tablero[9][7]=new caballo(9,7,false);
tablero[9][8]=new carroGuerra(9,8,false);
}

 
public boolean moverPieza(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
    piezas pieza = tablero[filaOrigen][colOrigen]; // Obtener la pieza en posicion inicial

    // verificar si hay una pieza en la posición de inicio
    if (pieza == null) {
        btab.mostrarEnInfoMovimientos("No hay ninguna pieza en la posición de origen.");
        return false;
    }

    // Verificar turno
    if ((esTurnoRojo && !pieza.esRojo()) || (!esTurnoRojo && pieza.esRojo())) {
        btab.mostrarEnInfoMovimientos("No es tu turno.");
        return false; 
    }
    piezas piezaDestino = tablero[filaDestino][colDestino]; // Obtener la pieza en la casilla de destino
    String mensajeMovimiento = "La pieza " + pieza.getNombre() + " se mueve de (" + filaOrigen + ", " + colOrigen + ") a (" + filaDestino + ", " + colDestino + ").";
    
    if (piezaDestino != null) {
        // No se pueden capturar piezas del mismo color
        if (pieza.esRojo() == piezaDestino.esRojo()) {
            btab.mostrarEnInfoMovimientos("No puedes capturar tus propias piezas.");
            return false; 
        }
        mensajeMovimiento += " Captura a la pieza " + piezaDestino.getNombre() + ".";
    }

    // Verificar movimiento
    if (pieza.movimientoValido(filaDestino, colDestino, tablero)) {
        // Realizar el movimiento
        tablero[filaDestino][colDestino] = pieza; // Mover la pieza a la nueva posición
        tablero[filaOrigen][colOrigen] = null; // Vaciar la casilla de origen
        pieza.setPosicion(filaDestino, colDestino); // Actualizar la posición de la pieza

        // Verificar si se hace gane
        if (piezaDestino instanceof general && piezaDestino.esRojo()) {
            reyR = 0;
            ganar();
            return true;
        }

        if (piezaDestino instanceof general && !piezaDestino.esRojo()) {
            reyN = 0;
            ganar();
            return true;
        }

        // Verificar la visión directa entre los reyes
        if (!verificarVisionDirecta()) {
            btab.mostrarEnInfoMovimientos("Movimiento inválido: Los reyes están en visión directa.");
            // Revertir el movimiento
            tablero[filaOrigen][colOrigen] = pieza;
            tablero[filaDestino][colDestino] = piezaDestino; // Si había una pieza en el destino, restaurarla
            pieza.setPosicion(filaOrigen, colOrigen); // Restaurar la posición de la pieza
            return false;
        }

         btab.mostrarEnInfoMovimientos(mensajeMovimiento);
         
        if (!juegoTerminado) {
            turno(); // Cambiar de turno si el juego no ha terminado
        }
        return true; // Movimiento exitoso
    } else {
        btab.mostrarEnInfoMovimientos("Movimiento no válido.");
        return false;
    }
}

public boolean verificarVisionDirecta() {
    int filaReyRojo = -1, colReyRojo = -1;
    int filaReyNegro = -1, colReyNegro = -1;

    // Buscar la posición del rey rojo y negro en el tablero
    for (int fila = 0; fila < tablero.length; fila++) {
        for (int col = 0; col < tablero[fila].length; col++) {
            piezas pieza = tablero[fila][col];
            if (pieza instanceof general) {
                if (pieza.esRojo()) {
                    filaReyRojo = fila;
                    colReyRojo = col;
                } else {
                    filaReyNegro = fila;
                    colReyNegro = col;
                }
            }
        }
    }

    // Verificar si están en la misma columna
    if (colReyRojo == colReyNegro) {
        // Verificar si hay piezas entre los dos reyes
        int filaInicio = Math.min(filaReyRojo, filaReyNegro) + 1;
        int filaFin = Math.max(filaReyRojo, filaReyNegro);
        for (int fila = filaInicio; fila < filaFin; fila++) {
            if (tablero[fila][colReyRojo] != null) {
                return true; // Hay una pieza entre los reyes, no hay visión directa
            }
        }
        return false; // No hay piezas entre los reyes, hay visión directa
    }

    return true; // Los reyes no estan en la misma columna, no tenemos problema
}


    //obtener una pieza en una posición  -para el tablero
    public piezas getPieza(int fila, int col) {
        return tablero[fila][col];
    }
    
    public void turno() {
        if (!juegoTerminado) {
            esTurnoRojo = !esTurnoRojo; // Alternar el turno
            mostrarTurno();
        }
    }
    
    public void mostrarTurno() {
        
        if (esTurnoRojo) {
            btab.mostrarEnInfoMovimientos("Turno de: "+jugadorRojo.getUsername());
        } else {
            btab.mostrarEnInfoMovimientos("Turno de: "+jugadorNegro.getUsername());
        }
    }

    public boolean ganar(){
    
        if(reyR==0){
            JOptionPane.showMessageDialog(null, jugadorNegro.getUsername() + " VENCIO A "+jugadorRojo.getUsername()+". FELICIDADES HAS GANADO 3 PUNTOS", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            btab.mostrarEnInfoMovimientos(jugadorNegro.getUsername() + " VENCIO A "+jugadorRojo.getUsername()+". FELICIDADES HAS GANADO 3 PUNTOS");
            jugadorNegro.sumarPuntos(3); 
            jugadorNegro.agregarLogPartida(jugadorNegro.getUsername() + " le GANO a "+ jugadorRojo.getUsername()); // Agregar log al jugador negro
            jugadorRojo.agregarLogPartida(jugadorRojo.getUsername() + " PERDIÓ contra " + jugadorNegro.getUsername()); // Log del perdedor
            juegoTerminado = true;
            finalizarJuego();
        }
        if(reyN==0){
            JOptionPane.showMessageDialog(null, jugadorRojo.getUsername() + " VENCIO A "+jugadorNegro.getUsername()+". FELICIDADES HAS GANADO 3 PUNTOS", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            btab.mostrarEnInfoMovimientos(jugadorRojo.getUsername() + " VENCIO A "+jugadorNegro.getUsername()+". FELICIDADES HAS GANADO 3 PUNTOS");
            jugadorRojo.sumarPuntos(3); 
            jugadorRojo.agregarLogPartida(jugadorRojo.getUsername() + " le GANO a "+ jugadorNegro.getUsername());
            jugadorNegro.agregarLogPartida(jugadorNegro.getUsername() + " PERDIÓ contra " + jugadorRojo.getUsername());
            juegoTerminado = true;
            finalizarJuego();
        }
        if(esTurnoRojo && retiroPress){
            JOptionPane.showMessageDialog(null, jugadorRojo.getUsername()+" se ha retirado. "+jugadorNegro.getUsername()+ " HA GANADO 3 PUNTOS.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
           btab.mostrarEnInfoMovimientos(jugadorRojo.getUsername()+" se ha retirado. "+jugadorNegro.getUsername()+ " HA GANADO 3 PUNTOS.");
            jugadorNegro.sumarPuntos(3); 
            jugadorNegro.agregarLogPartida(jugadorRojo.getUsername()+" se ha retirado. "+jugadorNegro.getUsername()+ " ha ganado.");
            jugadorRojo.agregarLogPartida(jugadorRojo.getUsername() + " PERDIÓ por retiro contra " + jugadorNegro.getUsername());
            juegoTerminado = true;
            finalizarJuego();
        }
        if(!esTurnoRojo && retiroPress){
            JOptionPane.showMessageDialog(null, jugadorNegro.getUsername() + " se ha retirado. " + jugadorRojo.getUsername() + " HA GANADO 3 PUNTOS.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            btab.mostrarEnInfoMovimientos(jugadorNegro.getUsername() + " se ha retirado. " + jugadorRojo.getUsername() + " HA GANADO 3 PUNTOS.");
            jugadorRojo.sumarPuntos(3); 
            jugadorRojo.agregarLogPartida(jugadorNegro.getUsername() + " se ha retirado. " + jugadorRojo.getUsername() + " ha ganado.");
            jugadorNegro.agregarLogPartida(jugadorNegro.getUsername() + " PERDIÓ por retiro contra " + jugadorRojo.getUsername() + " por retiro");
            juegoTerminado = true;
            finalizarJuego();
        
        }
   
        
    return juegoTerminado; 
    }
    
    public void retirarse(){
    retiroPress=true; 
    ganar();
    }
    
    private void finalizarJuego() {
        // Cerrar el tablero y volver al menú principal
        btab.cerrar();
        
    }
    
    
    
  
    
}