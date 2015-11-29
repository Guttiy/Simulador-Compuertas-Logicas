
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyFrame extends javax.swing.JFrame implements ActionListener{
   
	private static final long serialVersionUID = 1L;
	private MyPanel jPanel1;
    private JMenu menu;
    private JMenu menu2;
    private JMenuItem item1;
    private JMenuItem item2;
    private JMenuItem item3;
    private JMenuItem item4;
    private JMenuItem item5;
    private JMenuItem item6;
    private JMenuItem item7;
    private JMenuItem item8;
    private JMenuItem item9;
    private JMenuItem item10;
    private JMenuItem item11;
    private JMenuItem item12;
    private JMenuItem item13;
    private JMenuItem item14;    
    private JMenuBar bar;
    public MyFrame(){
    this.setSize(new Dimension (830,520));
    this.setPreferredSize(new Dimension (830,520));
    this.setTitle("Simulador Circuitos");
    initComponents();
    }
       
    
    private void initComponents(){
        jPanel1= new MyPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);//para poder cerrar el panel
        getContentPane().add(jPanel1,java.awt.BorderLayout.CENTER);//poner el panel principal y ubicarlo en el centro 
        bar = new JMenuBar();
        menu = new JMenu ("CREAR");
        menu2 = new JMenu ("ENT/SAL");
        item1 = new JMenuItem ("AND");
        item2 = new JMenuItem ("NAND");
        item3 = new JMenuItem ("OR");
        item4 = new JMenuItem ("XOR");
        item5 = new JMenuItem ("INV");
        item6 = new JMenuItem ("Cable");
        item7 = new JMenuItem ("ENT 0");
        item8 = new JMenuItem ("ENT 1");
        item9 = new JMenuItem ("SAL");
        item10 = new JMenuItem ("SIMULAR");
        item11 = new JMenuItem ("GUARDAR");
        item12 = new JMenuItem ("ABRIR");
        item13 = new JMenuItem ("MULTI");
        item14 = new JMenuItem ("LIMPIAR");
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);
        menu.add(item5);
        menu.add(item6);
        menu.add(item13);
        bar.add(menu);
        bar.add(menu2);
        menu2.add(item7);
        menu2.add(item8);
        menu2.add(item9);
        bar.add(item10);
        bar.add(item11);
        bar.add(item12);
        bar.add(item14);
        this.setJMenuBar(bar);
        item1.addActionListener (this);
        item2.addActionListener (this);
        item3.addActionListener (this);
        item4.addActionListener (this);
        item5.addActionListener (this);
        item6.addActionListener (this);
        item7.addActionListener (this);
        item8.addActionListener (this);
        item9.addActionListener (this);
        item10.addActionListener (this);
        item11.addActionListener (this);
        item12.addActionListener (this);
        item13.addActionListener (this);
        item14.addActionListener (this);
        jPanel1.addMouseListener(jPanel1);
        jPanel1.addMouseMotionListener(jPanel1);
        pack();
    }
    
    
    public static void main (String[] args){
  
      new MyFrame().setVisible(true);
      
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==item1){
            MyPanel.figpaint = 0;
        }else {if (e.getSource()==item2){
            MyPanel.figpaint = 1;
        }else {
            if(e.getSource()==item3){
            MyPanel.figpaint = 2;
            }else{
                if(e.getSource()==item4){
                    MyPanel.figpaint = 3;
                }else{
                    if(e.getSource()==item5){
                        MyPanel.figpaint = 4;
                    }else{
                        if(e.getSource()==item6){
                            MyPanel.figpaint = 5;
                        }else{
                            if(e.getSource()==item7){
                                MyPanel.figpaint = 6;
                            }else{
                                if(e.getSource()==item8){
                                        MyPanel.figpaint = 7;
                                }else{
                                    if(e.getSource()==item9){
                                        MyPanel.figpaint = 8;
                                    }else{
                                        if(e.getSource()==item10){
                                            Simulador.Simular();
                                        }else{
                                            if(e.getSource()==item11){
                                                MyPanel.guardar("Circuito.txt");
                                            }else{
                                                if(e.getSource()==item12){
                                                    MyPanel.leer("Circuito.txt");
                                                }else{
                                                if(e.getSource()==item13){
                                                    MyPanel.figpaint= 9;
                                                }else{
                                                    if(e.getSource()==item14){
                                                        MyPanel.leer("Limpio.txt");
                                                        MyPanel.disponible();
                                                    }
                                                }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        }
        jPanel1.repaint();
    }
    }
}
}

