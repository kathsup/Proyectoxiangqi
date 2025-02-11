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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class log extends JFrame {
     public JPanel panel; //crear panel como atributo 
    JFrame currentFrame = this; 
    private gestionJugadores gestorJugadores;
    private jugador jugador;
    JTextField u;
    JTextField c;
    FondoPanel fondo = new FondoPanel();
    
            
  public log(gestionJugadores gestor, jugador jugador){//creación de ventana
  this.gestorJugadores = gestor;
  this.jugador = jugador;
  this.setSize(712,506);//establecer tamaño de la ventana
  setTitle("LogIn"); //titulo 
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
      colocarText();
  }
  
  private void colocarEtiquetas(){
      panel = new JPanel();// instanciar panel 
      panel.setLayout(null);
      this.getContentPane().add(panel);//agregar panel a la ventana
      panel.setOpaque(false);
      fondo.add(panel);
     // panel.setBackground(Color.decode("#80c6ff"));//color del panel 
      //panel.getContentPane().setBackground(new Color(0xEBB8DD)); HEX COLRS 
      
      JLabel etiqueta = new JLabel("LOG IN");
      fondo.add(etiqueta);//agregar etiqueta al panel
      etiqueta.setBounds(280,85,300,50);//tamaño y posición de la etiqueta
      etiqueta.setForeground(Color.decode("#d23b43"));//ponerle color a las letras 
      etiqueta.setFont(new Font("Tahoma",Font.BOLD,36));
      
      
      //para ref de como poner imagenes en un label 
      //JLabel nombre = new JLabel (new ImageIcon("nombredelArchivo.png)); y se importan las librerias 
  
  }
  
  private void colocarBotones(){
  JButton login = new JButton("Log In");//O usar set text 
  login.setBounds(240, 350, 95, 29);
  login.setForeground(Color.decode("#f7f0e0"));
  login.setFont(new Font("Tahoma",Font.BOLD,18));
  login.setBackground(Color.decode("#d23b42"));
  fondo.setLayout(null);
  fondo.add(login);
  
  login.addActionListener(new ActionListener(){ 
  
      public void actionPerformed(ActionEvent e){
      String usuario = u.getText();
      String contra = c.getText(); 
     
      jugador jugador = gestorJugadores.logIn(usuario, contra);
                if (jugador != null) {
                    new principal(gestorJugadores,jugador).setVisible(true);
                    dispose(); 
                } else {
                    //JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                    new inicio(gestorJugadores).setVisible(true);
                    dispose(); 
                }
    
}
  
  });
  
  JButton salir = new JButton("SALIR");//O usar set text 
  salir.setBounds(370, 350, 95, 29);
  salir.setForeground(Color.decode("#f7f0e0"));
  salir.setFont(new Font("Tahoma",Font.BOLD,19));
  salir.setBackground(Color.decode("#d23b42"));
  fondo.add(salir);
  
  ActionListener s = new ActionListener(){
  public void actionPerformed(ActionEvent ae){
        currentFrame.dispose();
        new inicio(gestorJugadores).setVisible(true);
       // System.exit(0);
}
  };
  salir.addActionListener(s);
  
  
  //ImageIcon nombrefoto = new ImageIcon("foto.png");
  //salir.setIcon(new Image(nombrefoto.getImage().getScaledInstance(ancho,alto,Image.SCALE_SMOOTH)));
  
  }
  
  private void colocarText(){
  
  JLabel usser = new JLabel("USSERNAME");
      fondo.add(usser);//agregar etiqueta al panel
      usser.setBounds(200,140,300,50);//tamaño y posición de la etiqueta
      usser.setForeground(Color.BLACK);//ponerle color a las letras 
      usser.setFont(new Font("Tahoma",Font.BOLD,18));
      
      
      u = new JTextField();
      fondo.add(u);
      u.setBounds(200, 180, 290, 37);
  
      
      JLabel contra = new JLabel("PASSWORD");
      fondo.add(contra);//agregar etiqueta al panel
      contra.setBounds(200,220,300,50);//tamaño y posición de la etiqueta
      contra.setForeground(Color.BLACK);//ponerle color a las letras 
      contra.setFont(new Font("Tahoma",Font.BOLD,18));
      
      c = new JTextField();
      fondo.add(c);
      c.setBounds(200, 260, 290, 37);
  
  
  }
  
  
  class FondoPanel extends JPanel{
  
      private Image imagen; 
      
      @Override
      public void paintComponent(Graphics g){
          super.paintComponent(g);
          imagen = new ImageIcon(getClass().getResource("/imagenes/login.jpg")).getImage();
          g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
          
      
      }
  }
  
  
  /* public static void main(String[] args) {
        log l = new log();
        l.setVisible(true);
    }*/
}
