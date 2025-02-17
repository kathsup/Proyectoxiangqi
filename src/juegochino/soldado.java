package juegochino;

public class soldado extends piezas{

    public soldado(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }
    
    @Override
    public String getNombre(){
    return "Peon";
    }
    
   
        @Override
    public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
        // posicion inicial
        int actualX = this.getX();
        int actualY = this.getY();

        // verificar en que lado esta 
        boolean estaEnSuLado = (!this.esRojo() && actualX >= 5) || (this.esRojo() && actualX <= 4);

        // limites dentro del tablero
        if (nuevaX < 0 || nuevaX >= 10 || nuevaY < 0 || nuevaY >= 9) {
            return false;  // Movimiento fuera del tablero
        }

        // verificando el retroceso- no se puede
        if (this.esRojo() && nuevaX < actualX) {
            return false; //el rojo no puede retroceder
        } else if (!this.esRojo() && nuevaX > actualX) {
            return false;  // el negro no puede retroceder
        }

        //verificar si estan en la ultima fila
        if ((this.esRojo() && actualX == 9) || (!this.esRojo() && actualX == 0)) {
            // si estan en la ultima solo se mueven hacia los lados
            if (nuevaX == actualX && Math.abs(nuevaY - actualY) == 1) {
                return true;
            }
            return false;  // No puede moverse hacia adelante o en diagonal en la última fila
        }

        // Si esta antes del rio en su propio lado
        if (estaEnSuLado) {
            //solo se mueve hacia delante
            if (nuevaY == actualY && Math.abs(nuevaX - actualX) == 1) {
                return true;
            }
            return false;  //en su lado no puede moverse en otra dirección
        }

        // si cruza el rio, campo enemigo
        if (!estaEnSuLado) {
            //puede moverse tambien en diagonal y hacia delante, no retroceder
            if ((Math.abs(nuevaX - actualX) == 1 && nuevaY == actualY)
                    || // Movimiento hacia adelante
                    (Math.abs(nuevaX - actualX) == 1 && Math.abs(nuevaY - actualY) == 1)) {  // Movimiento en diagonal
                return true;
            }
            return false;  // No lateralmente si no en la última fila
        }

        // para capturar, si es del otro color y etc
        piezas destino = tablero[nuevaX][nuevaY];
        if (destino != null && destino.esRojo() != this.esRojo()) {
            return true;//si es del otro color si puede
        }

        // si nada se cumple invalido
        return false;
    }
}
