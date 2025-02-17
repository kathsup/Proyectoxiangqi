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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class log extends JFrame {
     public JPanel panel; 
    JFrame currentFrame = this; 
    private gestionJugadores gestorJugadores;
    private jugador jugador;
    JTextField u;
    JPasswordField c;
    FondoPanel fondo = new FondoPanel();
    
            
  public log(gestionJugadores gestor, jugador jugador){
  this.gestorJugadores = gestor;
  this.jugador = jugador;
  this.setSize(712,506);
  setTitle("LogIn");  
  setLocationRelativeTo(null);      
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  this.setContentPane(fondo);
  iniciarComponentes();
       
  }  
  
  private void iniciarComponentes(){
      colocarEtiquetas();
      colocarBotones();
      colocarText();
  }
  
  private void colocarEtiquetas(){
      panel = new JPanel();
      panel.setLayout(null);
      this.getContentPane().add(panel);
      panel.setOpaque(false);
      fondo.add(panel);
     
      JLabel etiqueta = new JLabel("LOG IN");
      fondo.add(etiqueta);
      etiqueta.setBounds(280,85,300,50);
      etiqueta.setForeground(Color.decode("#d23b43"));
      etiqueta.setFont(new Font("Tahoma",Font.BOLD,36));
     
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
      String usuario = u.getText().toLowerCase().trim();
      char[] pass = c.getPassword(); 
      String contra = new String(pass).trim();
      
     
      jugador jugador = gestorJugadores.logIn(usuario, contra);
                if (jugador != null) {
                    new principal(gestorJugadores,jugador).setVisible(true);
                    dispose(); 
                } else {
                    
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
       
}
  };
  salir.addActionListener(s);
  
  }
  
  private void colocarText(){
  
  JLabel usser = new JLabel("USUARIO:");
      fondo.add(usser);
      usser.setBounds(200,140,300,50);
      usser.setForeground(Color.BLACK);
      usser.setFont(new Font("Tahoma",Font.BOLD,18));
      
      
      u = new JTextField();
      fondo.add(u);
      u.setBounds(200, 180, 290, 37);
  
      
      JLabel contra = new JLabel("CONTRASEÑA:  (5 caracteres)");
      fondo.add(contra);
      contra.setBounds(200,220,300,50);
      contra.setForeground(Color.BLACK);
      contra.setFont(new Font("Tahoma",Font.BOLD,18));
      
      c = new JPasswordField();
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
  
}
