/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacOneServer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Artur
 */
public class TicTacOneServer{

    /**
     * @param args the command line arguments
     */
    private static JFrame fr = null;
    private static JButton btn1 = null;
    private static JButton btn2 = null;
    private static JButton btn3 = null;
    private static JButton btn4 = null;
    private static JButton btn5 = null;
    private static JButton btn6 = null;
    private static JButton btn7 = null;
    private static JButton btn8 = null;
    private static JButton btn9 = null;
    public static final int PORT=50007;
    static  PrintWriter toClient; 
    static boolean motion = false;
    static JPanel pane;
    static int i = 0;
    
    public static void initButtonAndFrame()
    {
      btn1 = new JButton("");
      btn1.setName("btn1");
      btn1.setFont(new Font("Arial", Font.PLAIN, 40));
      btn2 = new JButton("");
      btn2.setName("btn2");
      btn2.setFont(new Font("Arial", Font.PLAIN, 40));
      btn3 = new JButton("");
      btn3.setName("btn3");
      btn3.setFont(new Font("Arial", Font.PLAIN, 40));
      btn4 = new JButton("");
      btn4.setName("btn4");
      btn4.setFont(new Font("Arial", Font.PLAIN, 40));
      btn5 = new JButton("");
      btn5.setName("btn5");
      btn5.setFont(new Font("Arial", Font.PLAIN, 40));
      btn6 = new JButton("");
      btn6.setName("btn6");
      btn6.setFont(new Font("Arial", Font.PLAIN, 40));
      btn7 = new JButton("");
      btn7.setName("btn7");
      btn7.setFont(new Font("Arial", Font.PLAIN, 40));
      btn8 = new JButton("");
      btn8.setName("btn8");
      btn8.setFont(new Font("Arial", Font.PLAIN, 40));
      btn9 = new JButton("");
      btn9.setName("btn9");
      btn9.setFont(new Font("Arial", Font.PLAIN, 40));
      pane = new JPanel(new GridLayout(3,3));
      pane.setPreferredSize(new Dimension(500, 500));
    
      pane.add(btn1);
      pane.add(btn2);
      pane.add(btn3);
      pane.add(btn4);
      pane.add(btn5);  
      pane.add(btn6);
      pane.add(btn7);
      pane.add(btn8);
      pane.add(btn9);
  
      fr = new JFrame();
      fr.setContentPane(pane); 
      fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      fr.setLocation(100, 100);
      fr.pack();                
      fr.setVisible(true);
      fr.setTitle("Ruch przeciwnika");
    }
  
    public static void check(JButton button1, JButton button2, JButton button3)
    {
        if(button1.getText().equals("X") && button2.getText().equals("X") && button3.getText().equals("X"))
        {
           fr.setTitle("Wygrałeś!!!");
           reset();
        }
        else if(button1.getText().equals("O") && button2.getText().equals("O") && button3.getText().equals("O"))
        {
           fr.setTitle("Wygrał O");
           reset();
        }
    }
    
    public static void reset()
    {
        try
        {
            Thread.sleep(5000); 
        }
        catch(Exception ex){}
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        i=-1;
        System.out.println("i=" + i);
        if(fr.getTitle().equals("Wygrał O"))
        {
            fr.setTitle("Twoj ruch");
            motion=true;
        }
        else
        {
            fr.setTitle("Ruch przeciwnika");
            motion=false;
        }
    }
    
    public static void checkAll()
    {
      check(btn1, btn2, btn3);
      check(btn4, btn5, btn6);
      check(btn7, btn8, btn9);
      check(btn1, btn4, btn7);
      check(btn2, btn5, btn8);
      check(btn3, btn6, btn9);
      check(btn1, btn5, btn9);
      check(btn3, btn5, btn7);
      i++;
      if(i==9)
      {
          fr.setTitle("Koniec gry!");
          reset();
      }
    }
    
 public static void setXO(JButton btn, String txt)
 {
    String text = btn.getText();
    String title = fr.getTitle();
    if(!(text.equals("O") || text.equals("X")))
    {
        if(title.equals("Twoj ruch"))
        {
            if(motion == true)
            {
                btn.setText("X");
                toClient.println(txt);
                toClient.flush();
                motion=false;
                fr.setTitle("Ruch przeciwnika");
                checkAll();
            }
        }
    }  
 }
 
  public static void initButtonListener()
    {
        btn1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             String buttonText = ((JButton) e.getSource()).getName();
             setXO(btn1, buttonText);
          }
      }); 
        
          btn2.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             String buttonText = ((JButton) e.getSource()).getName();
             setXO(btn2, buttonText);
          }
      }); 
         
          btn3.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             String buttonText = ((JButton) e.getSource()).getName();
             setXO(btn3, buttonText);
          }
      }); 
          
          btn4.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             String buttonText = ((JButton) e.getSource()).getName();
             setXO(btn4, buttonText);
          }
      }); 
        
          btn5.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             String buttonText = ((JButton) e.getSource()).getName();
             setXO(btn5, buttonText);
          }
      }); 
         
          btn6.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             String buttonText = ((JButton) e.getSource()).getName();
             setXO(btn6, buttonText);
          }
      }); 
          
          btn7.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             String buttonText = ((JButton) e.getSource()).getName();
             setXO(btn7, buttonText);
          }
      }); 
        
          btn8.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             String buttonText = ((JButton) e.getSource()).getName();
             setXO(btn8, buttonText);
          }
      }); 
         
          btn9.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             String buttonText = ((JButton) e.getSource()).getName();
             setXO(btn9, buttonText);
          }
      }); 
    }
  
   public static void main(String args[]) throws IOException                  
   {
      initButtonAndFrame();
      initButtonListener();
      ServerSocket serv;                                                     
      serv=new ServerSocket(PORT);       
      Socket sock;                                                           
      sock=serv.accept(); 
      toClient=new PrintWriter(sock.getOutputStream());
      BufferedReader fromClient;                                                    
      fromClient=new BufferedReader(new InputStreamReader(sock.getInputStream()));  
      
      Component [] components = pane.getComponents();
      
      while(true)
      {
         String messageFromClient;                                                            
         messageFromClient=fromClient.readLine();
         for(int i=0;i<components.length;i++)
         {
             if(components[i].getName().equals(messageFromClient))
             {
                 JButton jbutton = (JButton)components[i];
                 jbutton.setText("O");
                 motion = true;
                 fr.setTitle("Twoj ruch");
                 checkAll();                 
             }
         }
      }                                                  
   }
}
