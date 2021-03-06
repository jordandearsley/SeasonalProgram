/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeasonalProgram;

import SeasonalProgram.RSModel;
import SeasonalProgram.SeasonalProgram;
import static SeasonalProgram.Data.FILEPATH;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author jordandearsley
 */
public class RSModelUI extends JPanel {
        
        public JTextField startDateText;
        public JTextField endDateText;
        public ButtonGroup bg;
        
        public JPanel one;
        public JPanel two;
        public JPanel three;
        public JPanel four;
        
        public ArrayList<JComponent[]> coresInput = new ArrayList<JComponent[]>();
        public ArrayList<JComponent[]> sectorsInput = new ArrayList<JComponent[]>();
        
        public JButton addCore;
        public JButton addSector;
        
        public String[] sizeNames = {"Relative Strength","Moving Averages","RSI","PnF"};
        public String[] options = {"Exponential","Simple"};

    
        public RSModelUI(){
            
            
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            
            one = sectionOne();
            two = sectionTwo();
            three = sectionThree();
            four = sectionFour();
            add(one);
            add(two);
            add(three);
            add(four);
            
  
        }
        
        public JPanel sectionOne(){
        //Creates Grid to [][] array
            int i = 1;
            int j = 2;
            JPanel secOne = new JPanel(new GridLayout(i, j));
            
            
            
            JPanel[][] panelHolder = new JPanel[i][j];    
            secOne.setLayout(new GridLayout(i,j,0,0));

            for(int m = 0; m < i; m++) {
               for(int n = 0; n < j; n++) {
                  panelHolder[m][n] = new JPanel(new FlowLayout(FlowLayout.LEFT));
                  secOne.add(panelHolder[m][n]);
               }
            }
            
            //Row One 
            panelHolder[0][0].add(new JLabel("Start Date (yyyy/mm/dd)"));
            panelHolder[0][1].add(new JLabel("End Date (yyyy/mm/dd)"));
            startDateText = new JTextField(20);
            panelHolder[0][0].add(startDateText);
            endDateText = new JTextField(20);
            panelHolder[0][1].add(endDateText);
            
            //Row Two
            /*
            model = new JRadioButton("Model");
            individual = new JRadioButton("Individual");
            bg = new ButtonGroup();
            bg.add(model);
            bg.add(individual);
            
            panelHolder[1][0].add(model);
            panelHolder[1][0].add(individual);
            model.setSelected(true);*/
            secOne.setPreferredSize(secOne.getPreferredSize());
            secOne.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
            return secOne;
        }
        
        public JPanel sectionTwo(){
        //Creates Grid to [][] array
            
            
            JPanel secTwo = new JPanel(new GridLayout());
            
            
            GridLayout gl = new GridLayout(0,7);
            secTwo.setLayout(gl);
            //Row One 
            secTwo.add(new JLabel("Core"));
            secTwo.add(new JLabel("Buy Date (mm/dd)"));
            secTwo.add(new JLabel("Sell Date (mm/dd)"));
            secTwo.add(new JLabel("Allocation %"));
            secTwo.add(new JLabel("Reversal Boxes"));
            secTwo.add(new JLabel("Signal Boxes"));
            secTwo.add(new JLabel("Box Size (%)"));
            
            //Row Two
            //JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JComponent[] components = new JComponent[7];
       
            components[0] = new JComboBox(SeasonalProgram.data.getDatasetNames());
            components[1] = new JTextField(5);
            components[2] = new JTextField(5);
            components[3] = new JTextField(5);
            components[4] = new JTextField(4);
            components[5] = new JTextField(4);
            components[6] = new JTextField(4);
 
            coresInput.add(components);
            for(JComponent jc:components){
                secTwo.add(jc);
            }
            
            
            
            
            secTwo.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));

            return secTwo;
        }
        
        public JPanel sectionThree(){
        //Creates Grid to [][] array
            
            
            int columns = 4;
            GridLayout gl = new GridLayout(0,columns);
            
            JPanel secThree = new JPanel(gl);
            
            //Row One 
            secThree.add(new JLabel("Sector"));
            secThree.add(new JLabel("Type"));
            secThree.add(new JLabel("Sell Type"));
            secThree.add(new JLabel("Allocation (5%/10%)"));
            
            JComponent[] components = new JComponent[4];
       
            components[0] = new JComboBox(SeasonalProgram.data.getDatasetNames());
            components[1] = new JComboBox(new String[]{"Major","Minor"});
            components[2] = new JComboBox(new String[]{"Regular","Hard"});
            components[3] = new JTextField(20);
            
            sectorsInput.add(components);
            for(JComponent jc:components){
                secThree.add(jc);
            }
            
            addSector = new JButton("Add Sector");
            secThree.add(addSector);
            
            addSector.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComponent[] components = new JComponent[4];
       
                    components[0] = new JComboBox(SeasonalProgram.data.getDatasetNames());
                    components[1] = new JComboBox(new String[]{"Major","Minor"});
                    components[2] = new JComboBox(new String[]{"Regular","Hard"});
                    components[3] = new JTextField(20);

                    sectorsInput.add(components);
                    for(JComponent jc:components){
                        secThree.add(jc);
                    }
                    
                    secThree.remove(addSector);
                    for(JComponent jc:components){
                        secThree.add(jc);
                    }
                    secThree.add(addSector);
                    secThree.revalidate();
                }
            });
            
            secThree.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));

            return secThree;
        }
        
        
        
        public JPanel sectionFour(){   
            JPanel secFive = new JPanel();
            //
            JButton submitButton = new JButton("Submit");
            secFive.add(submitButton);
            
            JLabel status = new JLabel("Click Submit to Run");
            secFive.add(status);
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //checks if allocation if over 100%
                    
                    int sectorAllocationSum = 0;
                    try{
                        for(JComponent[] core:sectorsInput){
                            JTextField jtf = (JTextField)core[4];
                            sectorAllocationSum+=Double.parseDouble(jtf.getText());
                        }
                        
                    }catch (Exception ex) {
                        status.setForeground(Color.red);
                        status.setText("Error: %Allocation Empty");
                    }
                    if(sectorAllocationSum>100){
                        status.setForeground(Color.red);
                        status.setText("Error: %Allocation over 100%");
                        return;
                    }
                    try {
                        SeasonalProgram.RSModel = new RSModel(
                            startDateText,
                            endDateText,
                            /*model,
                            individual,*/
                            coresInput,
                            sectorsInput
                                  
                        );
                        logInputs(startDateText,
                            endDateText,
                            coresInput,
                            sectorsInput
                            );
                        //IMPORTANTTT*******
                        SeasonalProgram.runRSModel();
                        
                        status.setForeground(Color.green);
                        status.setText("Model ran successfully");
                    } catch (Exception ex) {
                        status.setForeground(Color.red);
                        status.setText("Error: Check date format or empty field");
                        Logger.getLogger(RSModelUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }              
            });
            
            JButton loadPresets = new JButton("Load Presets");
            secFive.add(loadPresets);
            loadPresets.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        try {
                            loadPreset(loadPresets());
                        } catch (IOException ex) {
                            Logger.getLogger(RSModelUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(RSModelUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }              
            });
            
            JButton savePresets = new JButton("Save Presets");
            secFive.add(savePresets);
            savePresets.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        savePresets();
                    } catch (IOException ex) {
                        Logger.getLogger(RSModelUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }              
            });
            
            JButton deletePreset = new JButton("Delete Preset");
            secFive.add(deletePreset);
            deletePreset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        try {
                            deletePreset(loadPresets());
                        } catch (IOException ex) {
                            Logger.getLogger(RSModelUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(RSModelUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }              
            });
            
            
            return secFive;
        }
        public void deletePreset(String path) throws URISyntaxException, IOException{
            (new File(path)).delete();
        }
        
        public String loadPresets() throws URISyntaxException, IOException{
            URL location = SeasonalProgram.class.getProtectionDomain().getCodeSource().getLocation();
            File folder = new File(location.toURI());
            File[] listOfFiles = folder.listFiles();
            
            int numPresets = 0;
            for(int i = 0; i < listOfFiles.length; i++){
                if(listOfFiles[i].getName().contains("RSPRESET")){
                    numPresets++;
                }
            }
            String[] fileNames = new String[numPresets];
            //current position in fileNames
            int currentPos = 0;
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()&&listOfFiles[i].getName().contains("RSPRESET")) {
                  String fileName = listOfFiles[i].getName();
                  fileName = fileName.replace("RSPRESET", "");
                  fileName = fileName.replace(".csv", "");
                  fileNames[currentPos] = fileName;
                  currentPos++;
                }
            }
           
            String input = (String) JOptionPane.showInputDialog(null, "Select Preset",
            "Presets", JOptionPane.QUESTION_MESSAGE, null, fileNames,fileNames[0]);
            
            String path = location.getFile()+"RSPRESET"+input+".csv";
            return path;
            
            
        }
        
        public void logInputs(JTextField startDateText,
            JTextField endDateText,
            ArrayList<JComponent[]> coresInput,
            ArrayList<JComponent[]> sectorsInput){
            System.out.println("--------------------------------");     

            System.out.println("Start Date: "+startDateText.getText());
            System.out.println("End Date: "+endDateText.getText());
            
            System.out.println("");
            System.out.println("Core Inputs");
            
            for(JComponent[] component:coresInput){
                JComboBox jcb = (JComboBox)component[0];
                JTextField jtb1 = (JTextField)component[1];
                JTextField jtb2 = (JTextField)component[2];
                JTextField jtb3 = (JTextField)component[3];
                JTextField jtb4 = (JTextField)component[4];
                JTextField jtb5 = (JTextField)component[5];
                JTextField jtb6 = (JTextField)component[6];
                System.out.println(jcb.getSelectedItem().toString()+", "+jtb1.getText()+", "+jtb2.getText()+", "+jtb3.getText()+", "+jtb4.getText()+", "+jtb5.getText()+", "+jtb6.getText());
            }
            
            System.out.println("");
            System.out.println("Sector Inputs");
            
            for(JComponent[] component:sectorsInput){
                JComboBox jcb = (JComboBox)component[0];
                JComboBox jcb2 = (JComboBox)component[1];
                JComboBox jcb3 = (JComboBox)component[2];
                JTextField jtb1 = (JTextField)component[3];
               
                System.out.println(jcb.getSelectedItem().toString()+", "+jcb2.getSelectedItem().toString()+", "+jcb3.getSelectedItem().toString()+", "+jtb1.getText());
            }
           
            System.out.println("--------------------------------");     
        }
        
        public void loadPreset(String path) throws IOException{
            
            ArrayList<String[]> preset = readFile(path);
            startDateText.setText(preset.get(0)[0]);
            endDateText.setText(preset.get(0)[1]);
            /*if(!Boolean.parseBoolean(preset.get(0)[3])){
                bg.setSelected(individual.getModel(),true);
            }*/
            
            int lengthOfCore = coresInput.get(0).length;
            int numCores = (int)preset.get(1).length/lengthOfCore;
            
            for(int i = 0;i<preset.get(1).length;i+=lengthOfCore){
                if(coresInput.size()<i/lengthOfCore){
                    JComponent[] components = new JComponent[7];
                    components[0] = new JComboBox(SeasonalProgram.data.getDatasetNames());
                    components[1] = new JTextField(20);
                    components[2] = new JTextField(20);
                    components[3] = new JTextField(20);
                    components[4] = new JTextField(20);
                    components[5] = new JTextField(20);
                    components[6] = new JTextField(20);

                    coresInput.add(components);
                    two.remove(addCore);
                    for(JComponent jc:components){
                        two.add(jc);
                    }
                    two.add(addCore);
                    two.revalidate();
                }
            }
            for(int i = 0;i<coresInput.size();i++){
                //FIX
                JComboBox jcb = (JComboBox)coresInput.get(i)[0];
                JTextField jtb1 = (JTextField)coresInput.get(i)[1];
                JTextField jtb2 = (JTextField)coresInput.get(i)[2];
                JTextField jtb3 = (JTextField)coresInput.get(i)[3];
                JTextField jtb4 = (JTextField)coresInput.get(i)[4];
                JTextField jtb5 = (JTextField)coresInput.get(i)[5];
                JTextField jtb6 = (JTextField)coresInput.get(i)[6];
                jcb.setSelectedItem(preset.get(1)[0+i*lengthOfCore]);
                jtb1.setText(preset.get(1)[1+i*lengthOfCore]);
                jtb2.setText(preset.get(1)[2+i*lengthOfCore]);
                jtb3.setText(preset.get(1)[3+i*lengthOfCore]);
                jtb4.setText(preset.get(1)[4+i*lengthOfCore]);
                jtb5.setText(preset.get(1)[5+i*lengthOfCore]);
                jtb6.setText(preset.get(1)[6+i*lengthOfCore]);

            }
              
           
            
            int lengthOfSector = sectorsInput.get(0).length;
            int numSectors = (int)preset.get(2).length/lengthOfSector;
            
            for(int i = 0;i<preset.get(2).length;i+=lengthOfSector){
                if(sectorsInput.size()<i/lengthOfSector){
                    JComponent[] components = new JComponent[4];

                    components[0] = new JComboBox(SeasonalProgram.data.getDatasetNames());
                    components[1] = new JComboBox(new String[]{"Major","Minor"});
                    components[2] = new JComboBox(new String[]{"Regular","Hard"});
                    components[3] = new JTextField(20);
  

                    sectorsInput.add(components);
                    for(JComponent jc:components){
                        three.add(jc);
                    }
                    
                    three.remove(addSector);
                    for(JComponent jc:components){
                        three.add(jc);
                    }
                    three.add(addSector);
                    three.revalidate();
                }
            }
            
            for(int i = 0;i<sectorsInput.size();i++){
                JComboBox jcb = (JComboBox)sectorsInput.get(i)[0];
                JComboBox jcb2 = (JComboBox)sectorsInput.get(i)[1];
                JComboBox jcb3 = (JComboBox)sectorsInput.get(i)[2];
                JTextField jtb1 = (JTextField)sectorsInput.get(i)[3];

                jcb.setSelectedItem(preset.get(2)[0+i*lengthOfSector]);
                jcb2.setSelectedItem(preset.get(2)[1+i*lengthOfSector]);
                jcb3.setSelectedItem(preset.get(2)[2+i*lengthOfSector]);
                jtb1.setText(preset.get(2)[3+i*lengthOfSector]);

            }
            
            
        }
        
        public static ArrayList<String[]> readFile(String path) throws IOException{
        
            String line = "";
            String cvsSplitBy = ",";
            ArrayList<String[]> historicalData = new ArrayList<String[]>();

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {

                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] data = line.split(cvsSplitBy, -1);
                    historicalData.add(data);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return historicalData;
        }
        
        public void savePresets() throws IOException{
            String presetName = JOptionPane.showInputDialog("Enter Preset Name:");
            
            URL location = SeasonalProgram.class.getProtectionDomain().getCodeSource().getLocation();

            String path = location.getFile()+"RSPRESET"+presetName+".csv";
            int result = 0;
            if(!(new File(path).canRead())){
                (new File(path)).createNewFile();
            }else{
                //cancel ==2, ok ==0
                result = JOptionPane.showConfirmDialog((Component) null, "Preset exists, overwrite?","alert",JOptionPane.OK_CANCEL_OPTION);
                
            }
            //if ok
            if(result == 0){
                try(
                FileWriter fw = new FileWriter(path, false);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
                {       
                    out.print(startDateText.getText()+",");
                    out.print(endDateText.getText()+",");
                    /*out.print(model.isSelected()+",");
                    out.print(individual.isSelected()+",");*/
                    out.println("");
                    for(JComponent[] c:coresInput){
                        JComboBox jcb = (JComboBox)c[0];
                        JTextField jtb1 = (JTextField)c[1];
                        JTextField jtb2 = (JTextField)c[2];
                        JTextField jtb3 = (JTextField)c[3];
                        JTextField jtb4 = (JTextField)c[4];
                        JTextField jtb5 = (JTextField)c[5];
                        JTextField jtb6 = (JTextField)c[6];

                        out.print(jcb.getSelectedItem().toString()+",");
                        out.print(jtb1.getText()+",");
                        out.print(jtb2.getText()+",");
                        out.print(jtb3.getText()+",");
                        out.print(jtb4.getText()+",");
                        out.print(jtb5.getText()+",");
                        out.print(jtb6.getText()+",");

                    }  
                    out.println("");
                    for(JComponent[] c:sectorsInput){
                        JComboBox jcb = (JComboBox)c[0];
                        JComboBox jcb2 = (JComboBox)c[1];
                        JComboBox jcb3 = (JComboBox)c[2];
                        JTextField jtb1 = (JTextField)c[3];
  
                        out.print(jcb.getSelectedItem().toString()+",");
                        out.print(jcb2.getSelectedItem().toString()+",");
                        out.print(jcb3.getSelectedItem().toString()+",");
                        out.print(jtb1.getText()+",");

                    }
                    

                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            
            
        }
}
