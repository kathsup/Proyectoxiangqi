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

public class nuevojugador extends JFrame{
  public JPanel panel; 
  JFrame currentFrame = this; 
  private gestionJugadores gestorJugadores;
  JTextField u;
  JTextField c;
  FondoPanel fondo = new FondoPanel();
  
  public nuevojugador(gestionJugadores gestor){
  this.gestorJugadores = gestor;
  this.setSize(712,506);
  setTitle("Crear Jugador"); 
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
      JLabel etiqueta = new JLabel("CREAR JUGADOR");
      fondo.add(etiqueta);
      etiqueta.setBounds(208,65,300,50);
      etiqueta.setForeground(Color.decode("#cf293a"));
      etiqueta.setFont(new Font("Tahoma",Font.BOLD,30));
      
  }
  
  private void colocarBotones(){
  JButton crear = new JButton("CREAR PLAYER");//O usar set text 
  crear.setBounds(200, 350, 160, 29);
  crear.setForeground(Color.decode("#f6d9cf"));
  crear.setFont(new Font("Tahoma",Font.BOLD,16));
  crear.setBackground(Color.decode("#cf293a"));
  fondo.setLayout(null);
  fondo.add(crear);
  
  //funciones del boton para crear el usuario
  crear.addActionListener(new ActionListener(){
  
      public void actionPerformed(ActionEvent e){
      
          String usser = u.getText().toLowerCase().trim();
          String contra = c.getText().trim();
          
          boolean jugadorCreado = gestorJugadores.crearJugador(usser, contra);

        if (jugadorCreado) {
            JOptionPane.showMessageDialog(null, "Jugador creado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            
            jugador nuevoJugador = gestorJugadores.buscarJugador(usser); 
            new principal(gestorJugadores, nuevoJugador).setVisible(true); 
            dispose(); 
        } else {
            //errores
        }
      
      }
  });
  
  JButton salir = new JButton("SALIR");//O usar set text 
  salir.setBounds(390, 350, 95, 29);
  salir.setForeground(Color.decode("#f6d9cf"));
  salir.setFont(new Font("Tahoma",Font.BOLD,19));
  salir.setBackground(Color.decode("#cf293a"));
  fondo.add(salir);
  
  ActionListener s = new ActionListener(){
  public void actionPerformed(ActionEvent ae){
        currentFrame.dispose();
        new inicio(gestorJugadores).setVisible(true);
        //System.exit(0);
}
  };
  salir.addActionListener(s);
  
  
  //ImageIcon nombrefoto = new ImageIcon("foto.png");
  //salir.setIcon(new Image(nombrefoto.getImage().getScaledInstance(ancho,alto,Image.SCALE_SMOOTH)));
  
  }
  
  private void colocarText(){
  
  JLabel usser = new JLabel("USUARIO: ");
      fondo.add(usser);
      usser.setBounds(200,140,300,50);
      usser.setForeground(Color.BLACK);
      usser.setFont(new Font("Tahoma",Font.BOLD,18));
      
      
      u = new JTextField();
      fondo.add(u);
      u.setBounds(200, 180, 290, 37);
  
      
      JLabel contra = new JLabel("CONTRASEÑA:   (5 caracteres)");
      fondo.add(contra);
      contra.setBounds(200,220,300,50);
      contra.setForeground(Color.BLACK);
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
          imagen = new ImageIcon(getClass().getResource("/imagenes/crear.jpg")).getImage();
          g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
          
      
      }
  }
  
}
