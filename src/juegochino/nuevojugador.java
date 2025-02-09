package juegochino;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class nuevojugador extends JFrame{
  public JPanel panel; //crear panel como atributo 
  JFrame currentFrame = this; 
  private gestionJugadores gestorJugadores;
  JTextField u;
  JTextField c;
  
  public nuevojugador(gestionJugadores gestor){//creación de ventana
  this.gestorJugadores = gestor;
  this.setSize(712,506);//establecer tamaño de la ventana
  setTitle("Crear Player"); //titulo 
  //setLocation(250,100);//establece la posición inicial 
  //setBounds(250,100,712,506);//une setsize y location, primero location y luego size, ancho/alto
  setLocationRelativeTo(null);      //pone la pantalla centrada
  setDefaultCloseOperation(EXIT_ON_CLOSE);//cerrar el programa con el boton
  iniciarComponentes();
       
  }  
  
  private void iniciarComponentes(){
      colocarEtiquetas();
      colocarBotones();
      colocarText();
  }
  
  private void colocarEtiquetas(){
  panel = new JPanel();// instanciar panel 
      panel.setLayout(null);
      this.getContentPane().add(panel);//agregar panel a la ventana
      panel.setBackground(Color.decode("#fc05ae"));//color del panel 
      //panel.getContentPane().setBackground(new Color(0xEBB8DD)); HEX COLRS 
      
      JLabel etiqueta = new JLabel("CREAR PLAYER");
      panel.add(etiqueta);//agregar etiqueta al panel
      etiqueta.setBounds(225,50,300,50);//tamaño y posición de la etiqueta
      etiqueta.setForeground(Color.blue);//ponerle color a las letras 
      etiqueta.setFont(new Font("Tahoma",Font.BOLD,36));
      
      
      //para ref de como poner imagenes en un label 
      //JLabel nombre = new JLabel (new ImageIcon("nombredelArchivo.png)); y se importan las librerias 
  
  }
  
  private void colocarBotones(){
  JButton crear = new JButton("CREAR PLAYER");//O usar set text 
  crear.setBounds(200, 350, 160, 29);
  crear.setForeground(Color.decode("#b40afc"));
  crear.setFont(new Font("Tahoma",Font.BOLD,16));
  crear.setBackground(Color.decode("#dc96fa"));
  panel.add(crear);
  
  //funciones del boton para crear el usuario
  crear.addActionListener(new ActionListener(){
  
      public void actionPerformed(ActionEvent e){
      
          String usser = u.getText();
          String contra = c.getText();
          
          boolean jugadorCreado = gestorJugadores.crearJugador(usser, contra);

        if (jugadorCreado) {
            JOptionPane.showMessageDialog(null, "Jugador creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Cierra la ventana actual y abre el menú principal
            jugador nuevoJugador = gestorJugadores.buscarJugador(usser); // Obtener el jugador recién creado
            new principal(gestorJugadores, nuevoJugador).setVisible(true); // Abre el menú principal
            dispose(); // Cierra la ventana de creación de jugador
        } else {
            // Si hubo un error, el mensaje de error ya se muestra desde crearJugador
        }
      
      }
  });
  
  JButton salir = new JButton("SALIR");//O usar set text 
  salir.setBounds(390, 350, 95, 29);
  salir.setForeground(Color.BLUE);
  salir.setFont(new Font("Tahoma",Font.BOLD,19));
  salir.setBackground(Color.cyan);
  panel.add(salir);
  
  ActionListener s = new ActionListener(){
  public void actionPerformed(ActionEvent ae){
        currentFrame.dispose();
        //System.exit(0);
}
  };
  salir.addActionListener(s);
  
  
  //ImageIcon nombrefoto = new ImageIcon("foto.png");
  //salir.setIcon(new Image(nombrefoto.getImage().getScaledInstance(ancho,alto,Image.SCALE_SMOOTH)));
  
  }
  
  private void colocarText(){
  
  JLabel usser = new JLabel("USSERNAME:");
      panel.add(usser);//agregar etiqueta al panel
      usser.setBounds(200,140,300,50);//tamaño y posición de la etiqueta
      usser.setForeground(Color.BLACK);//ponerle color a las letras 
      usser.setFont(new Font("Tahoma",Font.BOLD,18));
      
      
      u = new JTextField();
      panel.add(u);
      u.setBounds(200, 180, 290, 37);
  
      
      JLabel contra = new JLabel("PASSWORD:");
      panel.add(contra);//agregar etiqueta al panel
      contra.setBounds(200,220,300,50);//tamaño y posición de la etiqueta
      contra.setForeground(Color.BLACK);//ponerle color a las letras 
      contra.setFont(new Font("Tahoma",Font.BOLD,18));
      
      c = new JTextField();
      panel.add(c);
      c.setBounds(200, 260, 290, 37);
  
  
  }
  
  
   /* public static void main(String[] args) {
        nuevojugador n = new nuevojugador();
        n.setVisible(true);
    }*/
    
    
}
