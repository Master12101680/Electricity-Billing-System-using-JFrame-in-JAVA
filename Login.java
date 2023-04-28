import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.*;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;


public class Login extends JFrame implements ActionListener{

    JButton login, cancel, signup;
    JTextField username, password;
    Login() {
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(300, 20, 100, 20);
        add(lblusername);
        
        username = new JTextField();
        username.setBounds(400, 20, 150, 20);
        add(username);
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(300, 60, 100, 20);
        add(lblpassword);
        
        password = new JTextField();
        password.setBounds(400, 60, 150, 20);
        add(password);        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("login.png"));
        Image i2 = i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        login = new JButton("Login", new ImageIcon(i2));
        login.setBounds(330, 160, 100, 20);
        login.addActionListener(this);
        add(login);
        
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel", new ImageIcon(i4));
        cancel.setBounds(450, 160, 100, 20);
        cancel.addActionListener(this);
        add(cancel);
        FocusListener highlighter = new FocusListener() {

            @Override
            
            public void focusGained(FocusEvent e) { e.getComponent().setBackground(Color.cyan);
            }
            @Override
            
            public void focusLost(FocusEvent e) {
            e.getComponent().setBackground (UIManager.getColor("TextField.background"));
            }
        };
            
            username.addFocusListener(highlighter); 
            password.addFocusListener(highlighter);
            login.addFocusListener(highlighter); 
            cancel.addFocusListener(highlighter); 


        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0, 0, 250, 250);
        add(image);
        
        setSize(640, 300);
        setLocation(400, 200);
        setVisible(true);

    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String susername = username.getText();
            String spassword = password.getText();
            String record = null;
            FileReader in = null;
            boolean isValid = false; // add a flag variable
            try {
                in = new FileReader("loginfile.txt");
                BufferedReader br = new BufferedReader(in);
                while ((record = br.readLine()) !=null) {
                    String[] split = record.split("\\s");
                    if (susername.equals(split[0]) && spassword.equals(split[1])) {
                        setVisible(false);
                        new ebill().setVisible(true);
                        isValid = true; // set the flag to true if match found
                        break;
                    }else{
                        username.setText("");
                        password.setText("");
                    }
                }
                in.close();
                if (!isValid) { // display the message only if flag is still false
                    JOptionPane.showMessageDialog(this, "INVALID USERNAME OR PASSWORD");
                }
            } catch (Exception e) {
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }
    
    
    public static void main(String[] args) {
        new Login();
    }
}