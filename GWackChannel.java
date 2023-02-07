import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GWackChannel extends Thread implements KeyListener{
    private Socket sock;
    private String name;
    private JTextArea msgTxt;
    private JTextArea compTxt;
    private JTextArea memTxt;
    private PrintWriter pw;
    private PrintWriter out;
    private BufferedReader in;
    GWackChannel(String ip, int port, String name, JTextArea msgTxt, JTextArea compTxt, JTextArea memTxt,GWackClientGUI gui){
        sock=null;       
        this.name=name;
        this.compTxt=compTxt;
        this.msgTxt=msgTxt;
        this.memTxt=memTxt;
        try{
           sock = new Socket(ip,port);
        }catch(Exception e){
            compTxt.setText("Cannot Connect");
            System.err.println("Cannot Connect");
            System.exit(1);
        }
    }
    public void run(){
        try{
            pw = new PrintWriter(sock.getOutputStream());
            pw.println("SECRET");
            pw.flush();
            pw.println("3c3c4ac618656ae32b7f3431e75f7b26b1a14a87");
            pw.flush();
            pw.println("NAME");
            pw.flush();
            pw.println(this.name);
            pw.flush();
            
            compTxt.addKeyListener(this);
            
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            
            out = new PrintWriter(sock.getOutputStream(), true);
            String reply = in.readLine();
            
            boolean members = true;
            boolean clear=false;
            boolean skip=false;
            while(reply!=null) {   
                if(reply.equals("END_CLIENT_LIST")){
                    System.out.println("w");
                    members=false;
                    skip=true;
                }
                
                if(reply.contains("START_CLIENT_LIST")){
                    System.out.println("w2");
                    members=true;
                    clear=true;
                    skip=true;
                }             
                System.out.println(reply);
                if (!skip){
                    if(members){
                        if (clear){
                            
                            memTxt.setText("");
                            clear=false;
                        }
                        System.out.println("member append"+reply);
                        memTxt.append(reply+"\n");                    
                    }
                    else{

                        msgTxt.append(reply+"\n");
                    
                    }
                }
                
                reply = in.readLine();
                
                
                skip = false;
                
            }   
            

            System.out.print("error test number 1");
            pw.close();
            in.close();
            sock.close();
        }catch(Exception e){
            System.out.print("error test number 2");
            compTxt.setText("IOException");
            System.err.print("IOException");
        }
    }
    public void sendButtonmsg(String msg){
            System.out.println(msg+" 2");
            String mess=msg.replace("\n", "");
            out.println(mess);
            compTxt.setText("");
    }
    public void disconnect(){
        pw.close();
        
        try {
            in.close();
            sock.close();
        } catch (IOException e) {
            System.err.print("Could not close socket");
            e.printStackTrace();
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            Toolkit.getDefaultToolkit().beep(); 
            this.sendButtonmsg(compTxt.getText());
            compTxt.setText("");
        }
    }
}