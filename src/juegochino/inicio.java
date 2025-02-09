package juegochino;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class inicio extends JFrame {
    public JPanel panel; //crear panel como atributo 
    JFrame currentFrame = this; 
    private gestionJugadores gestorJugadores;
    private jugador jugador;
    
    
  public inicio(gestionJugadores gestor){//creación de ventana
  this.gestorJugadores = gestor;
  this.setSize(712,506);//establecer tamaño de la ventana
  setTitle("Menu Inicio"); //titulo 
  //setLocation(250,100);//establece la posición inicial 
  //setBounds(250,100,712,506);//une setsize y location, primero location y luego size, ancho/alto
  setLocationRelativeTo(null);      //pone la pantalla centrada
  setDefaultCloseOperation(EXIT_ON_CLOSE);//cerrar el programa con el boton
  iniciarComponentes();
       
  }  
  
  private void iniciarComponentes(){
      colocarEtiquetas();
      colocarBotones();
  }
  
  private void colocarEtiquetas(){
  panel = new JPanel();// instanciar panel 
      panel.setLayout(null);
      this.getContentPane().add(panel);//agregar panel a la ventana
      panel.setBackground(Color.PINK);//color del panel 
      //panel.getContentPane().setBackground(new Color(0xEBB8DD)); HEX COLRS 
      
      JLabel etiqueta = new JLabel("Menú Inicio");
      panel.add(etiqueta);//agregar etiqueta al panel
      etiqueta.setBounds(225,50,300,50);//tamaño y posición de la etiqueta
      etiqueta.setForeground(Color.MAGENTA);//ponerle color a las letras 
      etiqueta.setFont(new Font("Tahoma",Font.BOLD,36));
      
      
      //para ref de como poner imagenes en un label 
      //JLabel nombre = new JLabel (new ImageIcon("nombredelArchivo.png)); y se importan las librerias 
  
  }
  
  private void colocarBotones(){
  JButton logIn = new JButton("LOG IN");//O usar set text 
  logIn.setBounds(240, 150, 214, 46);
  logIn.setForeground(Color.MAGENTA);
  logIn.setFont(new Font("Tahoma",Font.BOLD,24));
  logIn.setBackground(Color.decode("#d5adff"));
  panel.add(logIn);
  
   logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new log(gestorJugadores,jugador).setVisible(true); // Abre ventana de Login.
                dispose(); // Cierra la ventana actual.
            }
        });
  
  JButton crearPlayer = new JButton("CREAR PLAYER");//O usar set text 
  crearPlayer.setBounds(240, 220, 214, 46);
  crearPlayer.setForeground(Color.RED);
  crearPlayer.setFont(new Font("Tahoma",Font.BOLD,22));
  crearPlayer.setBackground(Color.decode("#ff94c2"));
  panel.add(crearPlayer);
  
  crearPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new nuevojugador(gestorJugadores).setVisible(true); // Abre ventana para crear jugador.
                dispose(); 
            }
        });
  
  JButton salir = new JButton("SALIR");//O usar set text 
  salir.setBounds(240, 290, 214, 46);
  salir.setForeground(Color.decode("#ac58f5"));
  salir.setFont(new Font("Tahoma",Font.BOLD,24));
  salir.setBackground(Color.decode("#00d9ce"));
  panel.add(salir);
  
  ActionListener s = new ActionListener(){
  public void actionPerformed(ActionEvent ae){
        //currentFrame.dispose();
        System.exit(0);
}
  };
  salir.addActionListener(s);
  //ImageIcon nombrefoto = new ImageIcon("foto.png");
  //salir.setIcon(new Image(nombrefoto.getImage().getScaledInstance(ancho,alto,Image.SCALE_SMOOTH)));
  
  }
  
 
    
    
}
