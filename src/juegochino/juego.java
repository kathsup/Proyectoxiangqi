package juegochino;

public class juego {
private piezas [][] tablero; 
private static boolean esTurnoRojo=true;
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

 // Método para mover una pieza
    public boolean moverPieza(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        piezas pieza = tablero[filaOrigen][colOrigen]; // Obtener la pieza en la casilla de origen

        // verificar si hay una pieza en la posición de origen
        if (pieza == null) {
            btab.mostrarEnInfoMovimientos("No hay ninguna pieza en la posición de origen.");
            return false;
        }

        if ((esTurnoRojo && !pieza.esRojo()) || (!esTurnoRojo && pieza.esRojo())) {
        btab.mostrarEnInfoMovimientos("No es tu turno.");
        return false; // Si no es el turno correcto
    }
       
        piezas piezaDestino = tablero[filaDestino][colDestino]; // pieza en la casilla de destino
    if (piezaDestino != null) {
       
        if (pieza.esRojo() == piezaDestino.esRojo()) {
             btab.mostrarEnInfoMovimientos("No puedes capturar tus propias piezas.");
            return false; 
        }
    }
    
    
        
        // Verificar si el movimiento es válido para esta pieza
        if (pieza.movimientoValido(filaDestino, colDestino, tablero)) {
            // Realizar el movimiento
            tablero[filaDestino][colDestino] = pieza; // Mover la pieza a la nueva posición
            tablero[filaOrigen][colOrigen] = null; // vaciar la casilla de origen

            // Actualizar la posición de la pieza (x, y)
            pieza.setPosicion(filaDestino, colDestino); 
            
            if(piezaDestino instanceof general && piezaDestino.esRojo()){
                reyR = 0;
                ganar();
                return true;
            }

            if (piezaDestino instanceof general && !piezaDestino.esRojo()) {
                reyN = 0;
                ganar();
                return true;
            }
            
            if (!juegoTerminado) {
            turno();
        }
            return true; // Movimiento exitoso
        } else {
            btab.mostrarEnInfoMovimientos("Movimiento no válido.");
            return false; 
        }
    }

    //obtener una pieza en una posición dada -para la clase Tablero
    public piezas getPieza(int fila, int col) {
        return tablero[fila][col];
    }
    
    public void turno() {
        
       /* if (esTurnoRojo) {
            System.out.println("Turno del rojo");
        } else {
            System.out.println("turno del negro");
        }
        esTurnoRojo = !esTurnoRojo;*/
       if(!juegoTerminado){
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
            btab.mostrarEnInfoMovimientos(jugadorNegro.getUsername()+" GANÓ");
           jugadorRojo.sumarPuntos(3); // Asignar 3 puntos al jugador rojo
            juegoTerminado = true;
            finalizarJuego();
        }
        if(reyN==0){
            btab.mostrarEnInfoMovimientos(jugadorRojo.getUsername()+" GANÓ");
            jugadorRojo.sumarPuntos(3); // Asignar 3 puntos al jugador rojo
            juegoTerminado = true;
            finalizarJuego();
        }
        if(esTurnoRojo && retiroPress){
           btab.mostrarEnInfoMovimientos(jugadorRojo.getUsername()+" se ha retirado. "+jugadorNegro.getUsername()+ " ha ganado.");
            jugadorRojo.sumarPuntos(3); // Asignar 3 puntos al jugador rojo
            juegoTerminado = true;
            finalizarJuego();
        }
        if(!esTurnoRojo && retiroPress){
        btab.mostrarEnInfoMovimientos(jugadorNegro.getUsername()+" se ha retirado. "+ jugadorRojo.getUsername()+ " ha ganado.");
            jugadorRojo.sumarPuntos(3); // Asignar 3 puntos al jugador rojo
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
    
    /*public String mostrarInfo(){
    
        return ("Usuario: "+jugadorRojo.getUsername()+"\nPuntos: "+jugadorRojo.getPuntos()
                +"\nFecha: "+jugadorRojo.getFechaIngresoFormateada()
                +"\nActivo: "+jugadorRojo.isActivo()
                );
       
    
    }*/
    
}