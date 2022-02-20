import javax.swing.*;
import java.io.IOException;
import java.io.IOException;
class Main {
    public static void main(String[] a) {
        loginform frame = null;
        try {
            frame = new loginform();        //initializes the JFrame object  
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
}