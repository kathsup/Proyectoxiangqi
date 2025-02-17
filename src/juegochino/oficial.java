package juegochino;

public class oficial extends piezas{

    public oficial(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }
    
    @Override
    public String getNombre(){
    return "Oficial";
    }
    
   
    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero) {
        // posición inicial
        int actualX = this.getX();
        int actualY = this.getY();

        // movimiento diagonal
        if (Math.abs(nuevaX - actualX) != 1 || Math.abs(nuevaY - actualY) != 1) {
            return false;  //solo que sea en diagonal
        }

        // verificar rojo o negro
        if (this.esRojo()) {
            // verificar si esta dentro del palacio rojo
            if (nuevaX < 0 || nuevaX > 2 || nuevaY < 3 || nuevaY > 5) {
                return false;  // Movimiento fuera del palacio rojo
            }
        } else {
            // Verificar si  movimiento esta dentro del palacio negro 
            if (nuevaX < 7 || nuevaX > 9 || nuevaY < 3 || nuevaY > 5) {
                return false;  // Movimiento fuera del palacio negro
            }
        }

        // Verificar si la nueva posición esta ocupada por una pieza del mismo equipo
        piezas destino = tablero[nuevaX][nuevaY];
        if (destino != null && destino.esRojo() == this.esRojo()) {
            return false;  // No capturar una pieza del mismo equipo
        }

        // valido si se cumplen las reglas
        return true;
    }
}
