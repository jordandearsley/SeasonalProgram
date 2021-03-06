/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeasonalProgram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jordandearsley
 */
public class Data {
    public static String FILEPATH;
    public static String[][] rawData;
    public NewDataset[] datasets;
    
    public Data(String name) throws IOException, ParseException{
        URL location = SeasonalProgram.class.getProtectionDomain().getCodeSource().getLocation();

        FILEPATH = location.getFile().substring(0,location.getFile().lastIndexOf("/"))+"/"+name;
        
        rawData = convertToArray(readFile(FILEPATH));
        
        datasets = createDataSets();
        
    }
    
    public static NewDataset[] createDataSets() throws ParseException{
        //Row of "S&P 500", etc.
        int nameListPos = 1;
        //Columns for one dataset
        int dataColumnCount = 2;
        
        //gets the names of the dataset headers
        String[] nameHeader = rawData[nameListPos];
        
        ArrayList<String> names = new ArrayList<String>();
        for(String s:nameHeader){
            if(!s.equals("")){
                names.add(s);
            }
        }
        
        //creates the datasets
        NewDataset[] datasets = new NewDataset[names.size()];
        for(int i = 0;i<names.size();i++){
            datasets[i] = new NewDataset(rawData,i,nameListPos,dataColumnCount);
        }  
        
        return datasets;
        
    }
    
    public static String[][] convertToArray(ArrayList<String[]> input){
        String[][] data = new String[input.size()][input.get(0).length];
        for(int i = 0;i<input.size();i++){
            data[i] = input.get(i);
        }
        return data;
    }
    
    public double getValue(Date d,Security s){
        if(s.name.equals("Cash")){
            return 1;
        }
        Date[] dates = getDataset(s.name).dates;
        for(int i = 0;i<dates.length;i++){
            if(dates[i].after(d)||dates[i].equals(d)){
                return getDataset(s.name).values[i];
            }
        }
        return 0;
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
    
    public NewDataset getDataset(String setName){
        for(int i = 0;i<datasets.length;i++){
            if(datasets[i].name.equals(setName)){
                return datasets[i];
            }
        }
        return null;
    }
    
    public String[] getDatasetNames(){
        String[] setNames = new String[datasets.length];
        for(int i = 0;i<datasets.length;i++){
            setNames[i]= datasets[i].name;
        }
        return setNames;
    }
    
    
}
