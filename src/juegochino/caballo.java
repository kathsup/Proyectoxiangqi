package juegochino;

public class caballo extends piezas {

    public caballo(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }

    @Override
    public String getNombre(){
    return "Caballo";
    }
    
    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero){
    
        int diferenciaX = Math.abs(nuevaX - x);
        int diferenciaY = Math.abs(nuevaY - y);

        // 1. Verificar que  movimiento sea en L: (2,1) o (1,2)
        if ((diferenciaX == 2 && diferenciaY == 1) || (diferenciaX == 1 && diferenciaY == 2)) {
            
            if (diferenciaX == 2) {
                
                int pivoteX = (nuevaX + x) / 2; 
                if (tablero[pivoteX][y] != null) {
                    return false; // revisar si tiene una pieza enmedio, movimiento no permitido
                }
            } else if (diferenciaY == 2) {
                
                int pivoteY = (nuevaY + y) / 2; 
                if (tablero[x][pivoteY] != null) {
                    return false; // revisar si tiene una pieza enmedio, movimiento no permitido
                }
            }
            
            // todo valido
            return true;
        }

        // Cualquier otro movimiento no es válido
        return false;
   
    } 
}
