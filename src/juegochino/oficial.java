package juegochino;

public class oficial extends piezas{

    public oficial(int x, int y, boolean rojo) {
        super(x, y, rojo);
    }
    
    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY, piezas[][] tablero){
    
    int diferenciaX = Math.abs(nuevaX - x);
    int diferenciaY = Math.abs(nuevaY - y);
    
    
    //1. verificar que se mueva una intersección en diagonal
    if(diferenciaX==1 && diferenciaY==0){
    
        //2. verificar que no salga del palacio
        if (rojo && (nuevaX < 0 || nuevaX > 2 || nuevaY < 3 || nuevaY > 5)) {
            return false;
        } else if (!rojo && (nuevaX < 7 || nuevaX > 9 || nuevaY < 3 || nuevaY > 5)) {
            return false;
        }
        
        return true;
    }   
    return false;
    }
}
