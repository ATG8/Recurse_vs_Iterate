package Recurse_vs_Iterate;

// import packages
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Date: 7/24/16
 * Class:
 * Author: ATG8
 * Purpose: This is the main method that will run Project 3.
 */
public class Recurse_vs_Iterate extends JFrame{
    
    // set main window size
    private static final int WIN_WIDTH = 320, WIN_HEIGHT = 200;
    
    // create user interfaces
    private final JLabel blank = new JLabel("");
    private final JRadioButton i = new JRadioButton("Iterative", true);
    private final JRadioButton r = new JRadioButton("Recursive");
    private final JLabel enterLabel = new JLabel("Enter n:");
    private final JTextField enterN = new JTextField("");
    private final JButton compute = new JButton("Compute");
    private final JLabel resultLabel = new JLabel("Result:");
    private final JTextField result = new JTextField("");
    private final JLabel efficiencyLabel = new JLabel("Efficiency:");
    private final JTextField efficiency = new JTextField("");
    private final ButtonGroup selectMethod = new ButtonGroup();
    private final JOptionPane popup = new JOptionPane();
    
    // create out file
    private static FileWriter fw;
    private File out = new File("out.csv");
    private ArrayList<String> outList = new ArrayList<>();
       
    // create constructor
    public Recurse_vs_Iterate (){
        
        // main panel setup
        super("Project 3");
        setFrame(WIN_WIDTH, WIN_HEIGHT);
        setResizable(false);
        JPanel nestedPanel = new JPanel();
        add(nestedPanel);
        nestedPanel.setLayout(new GridLayout(6,2,0,10));
        
        // add radio buttons to selectMethod group
        selectMethod.add(i); //add Iterative radio button
        selectMethod.add(r); //add Recursive radio button
        
        // add buttons and input boxes to panel
        nestedPanel.add(blank); //should add blank panel but doesn't
        nestedPanel.add(i); //add Iterative button
        nestedPanel.add(blank); //should add blank panel but doesn't
        nestedPanel.add(r); //add Recursive button
        nestedPanel.add(enterLabel); //adds "Enter n:" label
        nestedPanel.add(enterN); //adds input box
        nestedPanel.add(blank); //adds blank label to create space
        nestedPanel.add(compute); //adds compute button
        nestedPanel.add(resultLabel); //adds "Result:" label
        nestedPanel.add(result); //adds result output box
        result.setEditable(false); //sets result output box as uneditable
        nestedPanel.add(efficiencyLabel); //adds "Efficiency:" label
        nestedPanel.add(efficiency); //adds efficiency output box
        efficiency.setEditable(false); //sets efficiency output box as uneditable
        
        
        // add listeners for buttons to perform actions
        compute.addActionListener(new CAction()); //Compute button, CAction class
        CreateOutFile onClose = new CreateOutFile();
        addWindowListener(onClose);
    } //end constructor
    
    
    // class to use Compute button
    class CAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            
            // Input from user must be an integer greater than or equal to 3
            if(isInt() >= 0 && isInt() < 26) {
                // If Iterative radio button is selected then...
                if(i.isSelected()){
                    // Iterative method from Sequence class is performed
                    // while using integer input by the user
                    result.setText(Integer.toString(Sequence.computeIterative(isInt())));
                    efficiency.setText(Integer.toString(Sequence.trackEfficiency));
                }else{
                    result.setText(Integer.toString(Sequence.computeRecursive(isInt())));
                    efficiency.setText(Integer.toString(Sequence.trackEfficiency));
                } // end radio selection if statement
            }else{
                JOptionPane.showMessageDialog(popup, "Please enter an " +
                    "integer greater than zero and less than 26.", "Value Error", 
                    JOptionPane.INFORMATION_MESSAGE);
                resetInput();
            } //end if/else to check integer size
        } //end actionPerformed
    } //end CAction
    
    // class to create file on close
    class CreateOutFile extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e){
            try{
                int iResult, iEff, rResult, rEff;
                for (int i = 0; i <= 10; i++){
                    iResult = Sequence.computeIterative(i);
                    iEff = Sequence.trackEfficiency;
                    rResult = Sequence.computeRecursive(i);
                    rEff = Sequence.trackEfficiency;
                    outList.add(i + "," + iEff + "," + rEff);
                } //end gathering result of i 0-10
                if(!outList.isEmpty()){
                    createFile();
                    fw.close();
                }
            }catch (IOException ex){
                System.exit(0);
            }catch (NullPointerException exc){
                System.exit(0);
            }
            System.exit(0);
        }
    }
    
    // create file
    public void createFile(){
        try{
            fw = new FileWriter(out);
            for (String i:outList){
                fw.write(i + System.getProperty("line.separator"));
            } //end for
        }catch (IOException e){
            e.getMessage();
        }
    }
    
    // check input is integer
    public int isInt(){
        try{
            return Integer.parseInt(enterN.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(popup, "Please enter an " +
                    "integer greater than zero and within range " +
                    "of Java integers.", "Value Error", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
        resetInput();
        return 0;
    } //end userInput error checking
    
    
    // clear user input and reset focus
    public void resetInput(){
        enterN.setText("");
        enterN.requestFocus();
    } //end inputBox clear and refocus
    
    
    // set main window size
    private void setFrame(int width, int height){
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } //end main window sizing
    
    
    // display main window and focus on inputBox
    public void display(){
        setVisible(true);
        enterN.requestFocus();
    }
    
    

    // Main
    public static void main(String[] args) {
        Recurse_vs_Iterate start = new Recurse_vs_Iterate();
        start.display();
    } //end Main
} //end program
