import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;


public class GWackClientGUI extends JFrame implements ItemListener {


    private JLabel name ;
    private JLabel ip ;
    private JLabel port ;
    private JLabel msg;
    private JLabel compose ;
    private JLabel mem;
    private JLabel theme ;
    private JTextField nameText ;
    private JTextField ipText ;
    private JTextField portText ;
    private JTextArea msgText ;
    private JTextArea composeText;
    private JTextArea memText ;
    private Color defaultColor;
    private JComboBox<String> themeCombo;
    private JButton disconnect ;
    private JButton send ;
    private JButton connect;
    private JPanel p1 ;
    private JPanel p2 ;
    private JPanel p3 ;
    private JPanel p4;
    private JPanel p5; //text
    private JPanel p6; //text
    private JPanel p7; //text
    private JPanel p8; //text
    private JPanel p9; //text
    private JPanel p10; // for the bottom of the gui
    private JPanel p11; // for themes
    private GWackChannel c;

    public GWackClientGUI() {
        super();
        this.setTitle("GWack -- GW Slack Simulator (connected)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
        disconnect = new JButton("Disconnect");
        connect = new JButton("Connect");
        send = new JButton("Send");
        send.setPreferredSize(new Dimension(200, 40));
            
        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());
        p4 = new JPanel(new FlowLayout()); 
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel(); 
        p9 = new JPanel();
        p10 = new JPanel();
        p11 = new JPanel();


        name = new JLabel("Name");
        ip = new JLabel("IP Address");
        theme = new JLabel("Theme");
        port = new JLabel("Port");
        msg = new JLabel("Messages");
        compose = new JLabel("Compose");
        mem = new JLabel("Members Online");
        nameText = new JTextField(8);
        ipText = new JTextField("ssh-cs2113.adamaviv.com",20);
        portText = new JTextField(4);
        msgText = new JTextArea(15,80);
        composeText = new JTextArea(15,80);
        memText = new JTextArea(31,15);
        memText.setEditable(false);
        msgText.setEditable(false);
        defaultColor= p1.getBackground ();
        
        
        String themeselect[] = {"light","dark","cloud","rainbow"};
        themeCombo = new JComboBox<String>(themeselect);

        send.addActionListener((e) -> { c.sendButtonmsg(composeText.getText());
        System.out.println(composeText.getText()+" 1");});
        themeCombo.addItemListener(this);
        disconnect.addActionListener((e) -> { c.disconnect();
        p4.remove(disconnect);
        p4.add(connect);
        clearall();
        this.seteditable();});

        connect.addActionListener((e) -> {c=new GWackChannel(ipText.getText(),Integer.parseInt(portText.getText()),nameText.getText(),msgText,composeText,memText,this);
        c.start();
        this.setuneditable();
        p4.remove(connect);
        p4.add(disconnect);});
                                                
        

        p1.add(name);
        p1.add(nameText);
        p2.add(ip);
        p2.add(ipText);
        p3.add(port);
        p3.add(portText);
        p11.add(theme);
        p11.add(themeCombo);

        p4.add(p11);
        p4.add(p1);
        p4.add(p2);
        p4.add(p3);
        p4.add(connect);
        p8.setLayout(new FlowLayout());
        p5.setLayout(new BorderLayout());
        p5.add(mem,BorderLayout.NORTH);
        p5.add(memText,BorderLayout.SOUTH);
        p8.add(p5);
        p6.setLayout(new BorderLayout());
        p6.add(msg, BorderLayout.NORTH);
        p6.add(msgText, BorderLayout.SOUTH);
        p7.setLayout(new BorderLayout());
        p7.add(compose, BorderLayout.NORTH);
        p7.add(composeText, BorderLayout.SOUTH);
        p9.setLayout(new BorderLayout());
        p9.add(p6, BorderLayout.NORTH);
        p9.add(p7, BorderLayout.SOUTH);
        p8.add(p9);
        p10.setLayout(new BorderLayout());
        p10.add(send, BorderLayout.EAST);

        

        this.getContentPane().add(p4, BorderLayout.NORTH);
        this.getContentPane().add(p5, BorderLayout.WEST);
        this.getContentPane().add(p8, BorderLayout.CENTER);
        this.getContentPane().add(p10, BorderLayout.SOUTH);
        this.getContentPane().setBackground(Color.BLACK);

    }
    
    public void updatemsgText(String txt){
        msgText.append(txt+"\n");
    }
    public void updatememText(String txt){
        memText.append(txt+"\n");
    }
    public void clearmemText(){
        memText.setText("");
    }
    
    
    private void setuneditable(){
        nameText.setEditable(false);
        portText.setEditable(false);
        ipText.setEditable(false);
    }
    private void seteditable(){
        nameText.setEditable(true);
        portText.setEditable(true);
        ipText.setEditable(true);
    }
    private void clearall(){
        memText.setText("");
        composeText.setText("");
        msgText.setText("");
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == themeCombo) {
            if(themeCombo.getSelectedItem().equals("light")){
                p1.setBackground(defaultColor);
                p2.setBackground(defaultColor);
                p3.setBackground(defaultColor);
                p4.setBackground(defaultColor);
                p6.setBackground(defaultColor);
                p7.setBackground(defaultColor);
                p9.setBackground(defaultColor);
                p8.setBackground(defaultColor);
                p5.setBackground(defaultColor);
                p10.setBackground(defaultColor);
                p11.setBackground(defaultColor);
                name.setForeground(Color.BLACK);
                ip.setForeground(Color.BLACK);
                port.setForeground(Color.BLACK);
                msg.setForeground(Color.BLACK);
                compose.setForeground(Color.BLACK);
                mem.setForeground(Color.BLACK);
                theme.setForeground(Color.BLACK);
                nameText.setBackground(Color.white);
                ipText.setBackground(Color.white);
                portText.setBackground(Color.white);
                composeText.setBackground(Color.white);
                memText.setBackground(Color.white);
                msgText.setBackground(Color.white);
                nameText.setForeground(Color.BLACK);
                ipText.setForeground(Color.BLACK);
                portText.setForeground(Color.BLACK);
                composeText.setForeground(Color.BLACK);
                memText.setForeground(Color.BLACK);
                msgText.setForeground(Color.BLACK);
                send.setBackground(Color.white);
                send.setForeground(Color.BLACK);
                disconnect.setBackground(Color.white);
                disconnect.setForeground(Color.BLACK);
                connect.setBackground(Color.white);
                connect.setForeground(Color.BLACK);
                themeCombo.setBackground(Color.white);
                themeCombo.setForeground(Color.black);
            }
            if(themeCombo.getSelectedItem().equals("dark")){
                p1.setBackground(Color.BLACK);
                p2.setBackground(Color.BLACK);
                p3.setBackground(Color.BLACK);
                p4.setBackground(Color.BLACK);
                p6.setBackground(Color.BLACK);
                p7.setBackground(Color.BLACK);
                p9.setBackground(Color.BLACK);
                p8.setBackground(Color.BLACK);
                p5.setBackground(Color.BLACK);
                p10.setBackground(Color.BLACK);
                p11.setBackground(Color.BLACK);
                name.setForeground(Color.white);
                ip.setForeground(Color.white);
                port.setForeground(Color.white);
                msg.setForeground(Color.white);
                compose.setForeground(Color.white);
                mem.setForeground(Color.white);
                theme.setForeground(Color.white);
                nameText.setBackground(Color.gray);
                ipText.setBackground(Color.gray);
                portText.setBackground(Color.gray);
                composeText.setBackground(Color.GRAY);
                memText.setBackground(Color.GRAY);
                msgText.setBackground(Color.GRAY);
                nameText.setForeground(Color.white);
                ipText.setForeground(Color.white);
                portText.setForeground(Color.white);
                composeText.setForeground(Color.white);
                memText.setForeground(Color.white);
                msgText.setForeground(Color.white);
                send.setBackground(Color.black);
                send.setForeground(Color.white);
                disconnect.setBackground(Color.black);
                disconnect.setForeground(Color.white);
                connect.setBackground(Color.black);
                connect.setForeground(Color.white);
                themeCombo.setBackground(Color.gray);
                themeCombo.setForeground(Color.white);
            }
            if(themeCombo.getSelectedItem().equals("cloud")){
                p1.setBackground(Color.white);
                p2.setBackground(Color.white);
                p3.setBackground(Color.white);
                p4.setBackground(Color.white);
                p6.setBackground(Color.white);
                p7.setBackground(Color.white);
                p9.setBackground(Color.white);
                p8.setBackground(Color.white);
                p5.setBackground(Color.white);
                p10.setBackground(Color.white);
                p11.setBackground(Color.white);
                name.setForeground(Color.yellow);
                ip.setForeground(Color.yellow);
                port.setForeground(Color.yellow);
                msg.setForeground(Color.yellow);
                compose.setForeground(Color.yellow);
                mem.setForeground(Color.yellow);
                theme.setForeground(Color.yellow);
                nameText.setBackground(Color.yellow);
                ipText.setBackground(Color.yellow);
                portText.setBackground(Color.yellow);
                composeText.setBackground(Color.yellow);
                memText.setBackground(Color.yellow);
                msgText.setBackground(Color.yellow);
                nameText.setForeground(Color.white);
                ipText.setForeground(Color.white);
                portText.setForeground(Color.white);
                composeText.setForeground(Color.white);
                memText.setForeground(Color.white);
                msgText.setForeground(Color.white);
                send.setBackground(Color.yellow);
                send.setForeground(Color.white);
                disconnect.setBackground(Color.yellow);
                disconnect.setForeground(Color.white);
                connect.setBackground(Color.yellow);
                connect.setForeground(Color.white);
                themeCombo.setBackground(Color.white);
                themeCombo.setForeground(Color.yellow);
            }
            if(themeCombo.getSelectedItem().equals("rainbow")){
                p1.setBackground(Color.red);
                p2.setBackground(Color.red);
                p3.setBackground(Color.red);
                p4.setBackground(Color.red);
                p6.setBackground(Color.red);
                p7.setBackground(Color.red);
                p9.setBackground(Color.red);
                p8.setBackground(Color.red);
                p5.setBackground(Color.red);
                p10.setBackground(Color.red);
                p11.setBackground(Color.red);
                name.setForeground(Color.blue);
                ip.setForeground(Color.blue);
                port.setForeground(Color.blue);
                msg.setForeground(Color.blue);
                compose.setForeground(Color.blue);
                mem.setForeground(Color.blue);
                theme.setForeground(Color.blue);
                nameText.setBackground(Color.black);
                ipText.setBackground(Color.black);
                portText.setBackground(Color.black);
                composeText.setBackground(Color.black);
                memText.setBackground(Color.black);
                msgText.setBackground(Color.black);
                nameText.setForeground(Color.blue);
                ipText.setForeground(Color.blue);
                portText.setForeground(Color.blue);
                composeText.setForeground(Color.blue);
                memText.setForeground(Color.blue);
                msgText.setForeground(Color.blue);
                send.setBackground(Color.black);
                send.setForeground(Color.blue);
                disconnect.setBackground(Color.black);
                disconnect.setForeground(Color.blue);
                connect.setBackground(Color.black);
                connect.setForeground(Color.blue);
                themeCombo.setBackground(Color.red);
                themeCombo.setForeground(Color.blue);
            }

            System.out.print(themeCombo.getSelectedItem() + " selected");
        }
    }
    public static void main(String args[]){
        GWackClientGUI f = new GWackClientGUI();
        f.pack();
        f.setVisible(true);
        
    }
    
    
}