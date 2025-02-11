package juegochino;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class inicio extends JFrame {
    public JPanel panel; //crear panel como atributo 
    JFrame currentFrame = this; 
    private gestionJugadores gestorJugadores;
    private jugador jugador;
    FondoPanel fondo = new FondoPanel();
    private JPanel panelBotones;
    
     
    
    
  public inicio(gestionJugadores gestor){//creación de ventana
  this.gestorJugadores = gestor;
  this.setSize(712,506);//establecer tamaño de la ventana
  setTitle("Menu Inicio"); //titulo 
  //setLocation(250,100);//establece la posición inicial 
  //setBounds(250,100,712,506);//une setsize y location, primero location y luego size, ancho/alto
  setLocationRelativeTo(null);      //pone la pantalla centrada
  setDefaultCloseOperation(EXIT_ON_CLOSE);//cerrar el programa con el boton
  this.setContentPane(fondo);
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
      panel.setOpaque(false);
      fondo.add(panel);
      //panel.setBackground(Color.PINK);//color del panel 
      //panel.getContentPane().setBackground(new Color(0xEBB8DD)); HEX COLRS 
      
      JLabel etiqueta = new JLabel("Menú Inicio");
      fondo.add(etiqueta);//agregar etiqueta al panel
      etiqueta.setBounds(245,83,300,50);//tamaño y posición de la etiqueta
      etiqueta.setForeground(Color.decode("#c7326b"));//ponerle color a las letras 
      etiqueta.setFont(new Font("Tahoma",Font.BOLD,36));
      
      
      //para ref de como poner imagenes en un label 
      //JLabel nombre = new JLabel (new ImageIcon("nombredelArchivo.png)); y se importan las librerias 
  
  }
  
  private void colocarBotones(){
  panelBotones = new JPanel();
  panelBotones.setLayout(null);
  panelBotones.setOpaque(false);
  fondo.setLayout(null);
  fondo.add(panelBotones);
  
  JButton logIn = new JButton("LOG IN");//O usar set text 
  logIn.setBounds(240, 170, 214, 46);
  logIn.setForeground(Color.WHITE);
  logIn.setFont(new Font("Tahoma",Font.BOLD,24));
  logIn.setBackground(Color.decode("#a1162b"));
  fondo.add(logIn);
  
   logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new log(gestorJugadores,jugador).setVisible(true); // Abre ventana de Login.
                dispose(); // Cierra la ventana actual.
            }
        });
  
  JButton crearPlayer = new JButton("CREAR PLAYER");//O usar set text 
  crearPlayer.setBounds(240, 240, 214, 46);
  crearPlayer.setForeground(Color.WHITE);
  crearPlayer.setFont(new Font("Tahoma",Font.BOLD,22));
  crearPlayer.setBackground(Color.decode("#a1162b"));
  fondo.add(crearPlayer);
  
  crearPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new nuevojugador(gestorJugadores).setVisible(true); // Abre ventana para crear jugador.
                dispose(); 
            }
        });
  
  JButton salir = new JButton("SALIR");//O usar set text 
  salir.setBounds(240, 310, 214, 46);
  salir.setForeground(Color.WHITE);
  salir.setFont(new Font("Tahoma",Font.BOLD,24));
  salir.setBackground(Color.decode("#a1162b"));
  fondo.add(salir);
  
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
  
  /*public class PanelConFondo extends JPanel {
        private Image fondo;

        public PanelConFondo(String rutaImagen) {
            fondo = new ImageIcon(rutaImagen).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this); // Dibujar la imagen en todo el panel
        }
    }*/
  
  class FondoPanel extends JPanel{
  
      private Image imagen; 
      
      @Override
      public void paintComponent(Graphics g){
          super.paintComponent(g);
          imagen = new ImageIcon(getClass().getResource("/imagenes/inicio.jpg")).getImage();
          g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
          
      
      }
      
      
      
  
  }
 
    
    
}
