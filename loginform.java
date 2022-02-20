import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
public class loginform extends JFrame implements ActionListener {              //listens to the user event actions performed on UI                  

    public static File encrypted;
    public static String secretKey = "This is the secret key";


    public static String secondUser;
    public static String firstUser;

//setting UI objects 
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel myText = new JLabel("MY TEXT");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JTextField userDescription = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton saveButton = new JButton("SAVE");               //for saving inside user functions
    JButton resetButton = new JButton("RESET");
    JButton logout = new JButton("LOGOUT");
    JCheckBox showPassword = new JCheckBox("Show Password");
    JLabel greetings = new JLabel("Hello");

    JButton editChanges = new JButton("SAVE");
    JButton delete = new JButton("DELETE");


    JButton addUser = new JButton("ADD");

    JButton addSecond = new JButton("ADD");

    JTextField alternate = new JTextField();












    JLabel searchUserNameMaster = new JLabel("Enter username");

    JButton search = new JButton("SEARCH");








    JLabel setMasterUsername = new JLabel("set Master Username");
    JLabel setMasterPassword = new JLabel("set Master Password");

    JTextField masterText = new JTextField();
    JPasswordField masterPassword = new JPasswordField();
    JTextField masterDescryption = new JTextField();


    JButton editUserDetails = new JButton("EDIT");
    JTextField enterUsername = new JTextField("Enter user name");

    JButton removeUser = new JButton("REMOVE USER");
    JButton discardChanges = new JButton("DISCARD");






    JToggleButton masterToggle = new JToggleButton("master");


    loginform() throws IOException {

        encrypted = new File("D:/encrypted.txt");               //destination being set for storing the file


        if(!encrypted.exists()) {
            encrypted.createNewFile();
        }


        try {
            setLayoutManager();


            ArrayList<ArrayList<String>>  register = AES.convertToDictionary(encrypted, secretKey);
            if(register.size()==0){
                setMaster();
            }
            else{
                setLocationAndSizeStart();
            }
            addActionEvent();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }



    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSizeStart() {
        container.removeAll();



        masterToggle.setBounds(0, 0, 100, 30);                      //use of setBound to set their location and size on the screen
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        alternate.setBounds(50, 400, 300, 30);


        resetButton.setBounds(200, 300, 100, 30);



        logout.setBounds(200, 300, 100, 30);
        greetings.setBounds(50, 20, 100, 30);
        myText.setBounds(50, 150, 100, 30);
        userDescription.setBounds(50, 180, 300, 30);
        saveButton.setBounds(50, 300, 100, 30);



        container.add(masterToggle);                          //.add and .remove are used to add or remove UI elements
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);

        container.remove(userDescription);
        container.remove(greetings);
        container.remove(myText);
        container.remove(logout);
        container.remove(saveButton);

        this.repaint();                                      //used to redraw the Jframe if changes are made
    }


    public void reset(){
        userTextField.setText("");
        passwordField.setText("");
    }

    public void setLocationMaster(){
        container.removeAll();


        search.setBounds(50, 300, 100, 30);
        delete.setBounds(0, 0, 100, 30);
        addUser.setBounds(150, 0, 100, 30);
        addSecond.setBounds(150, 0, 100, 30);
        editChanges.setBounds(300, 0, 100, 30);


        container.add(editChanges);
        container.add(userLabel);
        container.add(userTextField);
        container.add(logout);
        container.add(search);
        container.add(delete);
        container.add(addUser);

        userTextField.setText("");

        this.repaint();
    }


    public void setLocationUser(){
        ArrayList<ArrayList<String>> temp = null;
        try {
            temp = AES.convertToDictionary(encrypted, secretKey);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        userDescription.setText(temp.get(getIFromUserName(temp, secondUser)).get(3));
        container.removeAll();

        container.add(userDescription);
        container.add(greetings);
        container.add(myText);
        container.add(logout);
        container.add(saveButton);


        this.repaint();
    }

    public void setMaster(){
        container.removeAll();

        setMasterUsername.setBounds(50, 150, 100, 30);
        setMasterPassword.setBounds(50, 220, 100, 30);
        masterText.setBounds(150, 150, 150, 30);
        masterPassword.setBounds(150, 220, 150, 30);
        masterDescryption.setBounds(50, 300, 300, 30);
        saveButton.setBounds(50, 400, 100, 30);

        container.add(setMasterUsername);
        container.add(setMasterPassword);
        container.add(masterText);
        container.add(masterPassword);
        container.add(masterDescryption);
        container.add(saveButton);

        this.repaint();
    }


    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        saveButton.addActionListener(this);
        logout.addActionListener(this);
        search.addActionListener(this);
        editChanges.addActionListener(this);
        delete.addActionListener(this);
        addUser.addActionListener(this);
        addSecond.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {                            //this method is to execute the functions when a specific button is pressed

        if (e.getSource() == loginButton) {

            if(userTextField.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter the username");
            }
            else if(passwordField.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter the password");
            }
            else if(!validatePassword(userTextField.getText(), passwordField.getText())){
                JOptionPane.showMessageDialog(this, "Incorrect username of password");
                return;
            }
            else {
                secondUser = userTextField.getText();
                System.out.println("Login successful");
                if(masterToggle.isSelected()){
                    try {
                        ArrayList<ArrayList<String>> input = AES.convertToDictionary(encrypted, secretKey);
                        if(input.get(0).get(0).equals(userTextField.getText())){
                            setLocationMaster();
                            firstUser = userTextField.getText();
                        }
                        else{
                            setLocationUser();
                            firstUser = null;
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                else{
                    setLocationUser();
                    firstUser = null;
                }
            }
            reset();
        }

        if(e.getSource() == delete){
            secondUser = userTextField.getText();
            ArrayList<ArrayList<String>> mid = null;
            try {

                mid = AES.convertToDictionary(encrypted, secretKey);
                int temp =getIFromUserName(mid, secondUser);
                if(temp!=0 && temp!=-1){
                    mid.remove(temp);
                    writeToFile(mid);
                    JOptionPane.showMessageDialog(this, "Deleted successfully");
                }
                else {
                    JOptionPane.showMessageDialog(this, "An error occurred");
                }

            } catch (Exception fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            reset();
        }
        if(e.getSource() == logout){
            setLocationAndSizeStart();
        }
        if(e.getSource() == editChanges){
            try {
                ArrayList<ArrayList<String>> mid = AES.convertToDictionary(encrypted, secretKey);
                int temp =getIFromUserName(mid, secondUser);
                if(temp!=0 && temp!=-1){
                    mid.get(getIFromUserName(mid, secondUser)).set(1, passwordField.getText());
                    mid.get(getIFromUserName(mid, secondUser)).set(3, alternate.getText());

                    writeToFile(mid);
                    JOptionPane.showMessageDialog(this, "Edited successfully");
                    reset();
                    alternate.setText("");
                }
                else{
                    JOptionPane.showMessageDialog(this, "Saved successfully!");
                }
            } catch (Exception fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        if (e.getSource() == addUser) {
            try {
                secondUser = userTextField.getText();
                ArrayList<ArrayList<String>> mid = AES.convertToDictionary(encrypted, secretKey);
                int temp =getIFromUserName(mid, secondUser);

                if(temp!=-1){
                    JOptionPane.showMessageDialog(this, "User already present");
                }
                else{
                    container.add(passwordLabel);
                    container.add(passwordField);
                    container.add(showPassword);
                    container.remove(addUser);
                    container.add(addSecond);
                    passwordField.setText("");
                    container.remove(alternate);
                    this.repaint();
                }

            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
        if (e.getSource() == addSecond) {
            if(userTextField.getText().equals(secondUser)){
                if(passwordField.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Password cannot by empty");
                }
                else {
                    try {
                        ArrayList<ArrayList<String>> mid = AES.convertToDictionary(encrypted, secretKey);
                        String salt = new Random().ints(48, 122 + 1)
                                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                                .limit(10)
                                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                .toString();
                        mid.add(new ArrayList<>(Arrays.asList(secondUser, passwordField.getText(), salt, "")));
                        System.out.println(secondUser +"" + passwordField.getText() + " " + salt + "");
                        writeToFile(mid);
                        JOptionPane.showMessageDialog(this, "User added successfully");
                        userTextField.setText("");
                        container.add(addUser);
                        container.remove(addSecond);
                        container.remove(passwordField);
                        container.remove(passwordLabel);
                        container.remove(showPassword);
                        container.remove(alternate);
                        this.repaint();
                    } catch (Exception fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }

                }
            }
            else {
                container.add(addUser);
                container.remove(addSecond);
                container.remove(passwordField);
                container.remove(passwordLabel);
                container.remove(showPassword);
                container.remove(alternate);
                passwordField.setText("");
                this.repaint();
                JOptionPane.showMessageDialog(this, "Username has been changed");
            }
            reset();
        }

        if (e.getSource() == resetButton) {
            reset();
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);              //if show password is selected the password will be visible
            } else {
                passwordField.setEchoChar('*');                   //if show password is not selected the password will be shown with asterisk
            }
        }

        if (e.getSource() == search) {
            try {
                ArrayList<ArrayList<String>> mid = AES.convertToDictionary(encrypted, secretKey);
                if(getIFromUserName(mid, userTextField.getText())==-1){
                    JOptionPane.showMessageDialog(this, "The username is not available");
                }
                else if(getIFromUserName(mid, userTextField.getText())==0){
                    JOptionPane.showMessageDialog(this, "Cannot edit admin data");
                }
                else {
                    JOptionPane.showMessageDialog(this, "User found");
                    secondUser = userTextField.getText();
                    passwordField.setText(mid.get(getIFromUserName(mid, secondUser)).get(1));
                    container.add(passwordLabel);
                    container.add(passwordField);
                    container.add(showPassword);
                    container.add(alternate);
                    alternate.setText(mid.get(getIFromUserName(mid, secondUser)).get(3));
                    this.repaint();
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }


        if(e.getSource() == saveButton){
            try {
                if(AES.convertToDictionary(encrypted, secretKey).size() == 0){

                    if(!encrypted.exists()) {
                        encrypted.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(encrypted.getAbsoluteFile(),true);
                    BufferedWriter bw = new BufferedWriter(fileWriter);
                    String salt = new Random().ints(48, 122 + 1)
                            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                            .limit(10)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();
                    bw.write(masterText.getText() + ":" + AES.encrypt(masterPassword.getText()+salt, secretKey)+ ":" + salt+ ":"+masterDescryption.getText());
                    bw.close();
                    setLocationAndSizeStart();
                }
                else {

                    ArrayList<ArrayList<String>> temp = AES.convertToDictionary(encrypted, secretKey);

                    temp.get(getIFromUserName(temp, secondUser)).set(3, userDescription.getText());

                    writeToFile(temp);
                }
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
    }


    static boolean validatePassword(String username, String password){                        //the method will check whether or not the username and password match(if the username and password match = true)
        try {
            ArrayList<ArrayList<String>> input = AES.convertToDictionary(encrypted, secretKey);
            for(ArrayList i : input){
                if(i.get(0).equals(username) && i.get(1).equals(password)){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }




    public static void writeToFile(ArrayList<ArrayList<String>> input) throws IOException {              //this function takes the array list and writes it into the file after the password is encrypted
        PrintWriter writer = new PrintWriter(encrypted.getAbsoluteFile());
        writer.print("");
        writer.close();



        FileWriter fileWriter = new FileWriter(encrypted.getAbsoluteFile(),true);
        BufferedWriter bw = new BufferedWriter(fileWriter);

        for(ArrayList<String> arr : input){
            String salt = new Random().ints(48, 122 + 1)                                //we use random ints to create a random number which is converted to string then combined together
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))         //that is why we get a random string that consists of number of characters
                    .limit(10)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            bw.write(arr.get(0) + ":" + AES.encrypt(arr.get(1)+arr.get(2), secretKey)+ ":" +arr.get(2)+ ":"+arr.get(3));        //stores the file information 
            bw.newLine();
        }

        bw.close();
    }

    public static int getIFromUserName(ArrayList<ArrayList<String>> input, String username){             //this function searches usernames in the array list and returns the location of its data back to the array list 
        for(int i = 0 ; i<input.size() ; i++){
            if(input.get(i).get(0).equals(username)){
                return i;
            }
        }
        return -1;
    }
}
