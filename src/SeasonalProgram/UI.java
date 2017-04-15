/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeasonalProgram;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author jordandearsley
 */
public class UI extends JFrame {
        
        public JTextField startDateText;
        public JTextField endDateText;
        public JRadioButton model;
        public JRadioButton individual;
        
        public ArrayList<JComponent[]> cores = new ArrayList<JComponent[]>();
        public ArrayList<JComponent[]> sectorsInput = new ArrayList<JComponent[]>();

        public JTextField[][] fieldHolder;
        
        public JCheckBox RSBox;
        public JComboBox RSCombo1;
        public JTextField RSText1;
        public JComboBox RSCombo2;
        public JTextField RSText2;
        public JCheckBox MABox;
        public JComboBox MACombo1;
        public JTextField MAText1;
        public JComboBox MACombo2;
        public JTextField MAText2;
        public JCheckBox RSIBox;
        public JComboBox RSICombo1;
        public JTextField RSIText1;
        public JComboBox RSICombo2;
        public JTextField RSIText2;
        public JCheckBox BTBox;
        public JComboBox BTCombo1;
        public JTextField BTText1;
        public JComboBox BTCombo2;
        public JTextField BTText2;
        
    
        public UI(){
            super("Program Name");

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setDefaultLookAndFeelDecorated(true);
            getContentPane().setPreferredSize(new Dimension(400,400));
            
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            
            add(sectionOne());
            add(sectionTwo());
            add(sectionThree(3));
            add(sectionFour());
            add(sectionFive());
            
            pack();
        }
        
        public JPanel sectionOne(){
        //Creates Grid to [][] array
            int i = 2;
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
            model = new JRadioButton("Model");
            individual = new JRadioButton("Individual");
            ButtonGroup bG = new ButtonGroup();
            bG.add(model);
            bG.add(individual);
            
            panelHolder[1][0].add(model);
            panelHolder[1][0].add(individual);
            model.setSelected(true);
            secOne.setPreferredSize(secOne.getPreferredSize());
            secOne.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));
            return secOne;
        }
        
        public JPanel sectionTwo(){
        //Creates Grid to [][] array
            
            
            JPanel secTwo = new JPanel(new GridLayout());
            
            
            GridLayout gl = new GridLayout(0,3);
            secTwo.setLayout(gl);
            //Row One 
            secTwo.add(new JLabel("Core"));
            secTwo.add(new JLabel("Buy Date"));
            secTwo.add(new JLabel("Sell Date"));
            
            //Row Two
            //JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JComponent[] components = new JComponent[3];
            JComboBox jcb = new JComboBox(SeasonalProgram.data.getDatasetNames());
       
            components[0] = new JComboBox(SeasonalProgram.data.getDatasetNames());
            components[1] = new JTextField(20);
            components[2] = new JTextField(20);
            cores.add(components);
            for(JComponent jc:components){
                secTwo.add(jc);
            }
            
            
            JButton addCore = new JButton("Add Core");
            secTwo.add(addCore);

            addCore.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComponent[] components = new JComponent[3];
                    components[0] = new JComboBox(SeasonalProgram.data.getDatasetNames());
                    components[1] = new JTextField(20);
                    components[2] = new JTextField(20);
                    cores.add(components);
                    secTwo.remove(addCore);
                    for(JComponent jc:components){
                        secTwo.add(jc);
                    }
                    secTwo.add(addCore);
                    secTwo.revalidate();
                }
            });
            
            secTwo.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));

            return secTwo;
        }
        
        public JPanel sectionThree(int sectors){
        //Creates Grid to [][] array
            
            
            int columns = 4;
            GridLayout gl = new GridLayout(0,4);
            
            JPanel secThree = new JPanel(gl);
            
            //Row One 
            secThree.add(new JLabel("Sector"));
            secThree.add(new JLabel("Start"));
            secThree.add(new JLabel("Stop"));
            secThree.add(new JLabel("Lev %"));
            
            JComponent[] components = new JComponent[4];
            JComboBox jcb = new JComboBox(SeasonalProgram.data.getDatasetNames());
       
            components[0] = new JComboBox(SeasonalProgram.data.getDatasetNames());
            components[1] = new JTextField(20);
            components[2] = new JTextField(20);
            components[3] = new JTextField(20);
            
            sectorsInput.add(components);
            for(JComponent jc:components){
                secThree.add(jc);
            }
            
            JButton addSector = new JButton("Add Sector");
            secThree.add(addSector);
            
            addSector.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComponent[] components = new JComponent[4];
                    JComboBox jcb = new JComboBox(SeasonalProgram.data.getDatasetNames());

                    components[0] = new JComboBox(SeasonalProgram.data.getDatasetNames());
                    components[1] = new JTextField(20);
                    components[2] = new JTextField(20);
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
        //Creates Grid to [][] array
            
            int rows = 5;
            int columns = 3;
            JPanel secFour = new JPanel(new GridLayout(rows, columns));
            
            
            JPanel[][] panelHolder = new JPanel[rows][columns];    
            secFour.setLayout(new GridLayout(rows,columns,0,0));

            for(int m = 0; m < rows; m++) {
               for(int n = 0; n < columns; n++) {
                  panelHolder[m][n] = new JPanel(new FlowLayout(FlowLayout.LEFT));
                  secFour.add(panelHolder[m][n]);
               }
            }
            
            //Row One 
            panelHolder[0][0].add(new JLabel("Triggers"));
            
            
            String[] options = {"Exponential","Simple"};

            //Row Two
            RSBox = new JCheckBox("Relative Strength");
            panelHolder[1][0].add(RSBox);
            RSCombo1 = new JComboBox(options);
            panelHolder[1][1].add(RSCombo1);
            RSText1 = new JTextField(4);
            panelHolder[1][1].add(RSText1);
            RSCombo2 = new JComboBox(options);
            panelHolder[1][2].add(RSCombo2);
            RSText2 = new JTextField(4);
            panelHolder[1][2].add(RSText2);
            
            //Row Three
            MABox = new JCheckBox("Moving Averages");
            panelHolder[2][0].add(MABox);
            MACombo1 = new JComboBox(options);
            panelHolder[2][1].add(MACombo1);
            MAText1 = new JTextField(4);
            panelHolder[2][1].add(MAText1);
            MACombo2 = new JComboBox(options);
            panelHolder[2][2].add(MACombo2);
            MAText2 = new JTextField(4);
            panelHolder[2][2].add(MAText2);
            
            //Row Four
            RSIBox = new JCheckBox("RSI");
            panelHolder[3][0].add(RSIBox);
            RSICombo1 = new JComboBox(options);
            panelHolder[3][1].add(RSICombo1);
            RSIText1 = new JTextField(4);
            panelHolder[3][1].add(RSIText1);
            RSICombo2 = new JComboBox(options);
            panelHolder[3][2].add(RSICombo2);
            RSIText2 = new JTextField(4);
            panelHolder[3][2].add(RSIText2);
            
            //Row Four
            BTBox = new JCheckBox("Brooke Thackray RS");
            panelHolder[4][0].add(BTBox);
            BTCombo1 = new JComboBox(options);
            panelHolder[4][1].add(BTCombo1);
            BTText1 = new JTextField(4);
            panelHolder[4][1].add(BTText1);
            BTCombo2 = new JComboBox(options);
            panelHolder[4][2].add(BTCombo2);
            BTText2 = new JTextField(4);
            panelHolder[4][2].add(BTText2);
           
            secFour.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(1.0f)));

            return secFour;
        }
        
        public JPanel sectionFive(){   
            JPanel secFive = new JPanel();
            //
            JButton submitButton = new JButton("Submit");
            secFive.add(submitButton);
            
            JLabel status = new JLabel("Click Submit to Run");
            secFive.add(status);
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        SeasonalProgram.seasonalModel = new Model(
                                startDateText,
                                endDateText,
                                model,
                                individual,
                                
                                cores,
                                
                                sectorsInput,
                                
                                RSBox,
                                RSCombo1,
                                RSText1,
                                RSCombo2,
                                RSText2,
                                MABox,
                                MACombo1,
                                MAText1,
                                MACombo2,
                                MAText2,
                                RSIBox,
                                RSICombo1,
                                RSIText1,
                                RSICombo2,
                                RSIText2,
                                BTBox,
                                BTCombo1,
                                BTText1,
                                BTCombo2,
                                BTText2
                        );
                        status.setForeground(Color.green);
                        status.setText("Model ran successfully");
                    } catch (Exception ex) {
                        status.setForeground(Color.red);
                        status.setText("Error: Check date format or empty field");
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }              
            });
            return secFive;
        }

}
