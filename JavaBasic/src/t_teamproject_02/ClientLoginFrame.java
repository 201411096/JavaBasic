package t_teamproject_02;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ClientLoginFrame extends JFrame implements ActionListener {

    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, signup;

    public ClientLoginFrame() {
        
        // User Label
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        
        // Password

        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();

        // Submit

        submit = new JButton("SUBMIT");
        signup = new JButton("SIGNUP");
        panel = new JPanel(new GridLayout(3, 2));

        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);

        message = new JLabel();
        panel.add(signup);
        panel.add(submit);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adding the listeners to components..
        submit.addActionListener(this);
        
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here !");
        setSize(300, 100);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = String.valueOf(password_text.getPassword());
        if (userName.trim().equals("admin") && password.trim().equals("admin")) {
            //아이디 비번이 맞을경우(..) 예시(admin, admin)
        	MainClientFrame mainClientFrame = new MainClientFrame();
        } else {
        	System.out.println("??");
        }

    }

}