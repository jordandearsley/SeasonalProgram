/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeasonalProgram;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jordandearsley
 */
public class Portfolio {
    
    public Map<Security, Double[]> holdings = new HashMap<Security, Double[]>();
    public ArrayList<Security> securities;
    public Map<Date, Map<Security, Double[]>> historicalPortfolio = new HashMap<Date, Map<Security, Double[]>>();
    public ArrayList<Trade> trades = new ArrayList<Trade>();
    
    public Date startDate;
    public Date endDate;
    
    public Calendar calendar;
    
    public Security cash;
        
    
    public Portfolio(ArrayList<Security> securities, Date startDate, Date endDate){
        this.securities = securities;
        this.startDate = startDate;
        this.endDate = endDate;
        calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        
        
        //creates cash at 100% with value of 1 and adds to holdings
        cash = new Core("Cash",getCore(securities).sellDate,getCore(securities).buyDate,100);
        securities.add(0,cash);
        Double[] cashStats = new Double[2];
        cashStats[0] = 100.00;
        cashStats[1] = 1.0;
        holdings.put(cash, cashStats);
    }
    
    public void runPortfolio(){
        while(calendar.getTime().before(endDate)){
            setDates(calendar.getTime());
            Security base = new Security();
            //Determine the bank
            for(Security s:holdings.keySet()){
                if(s instanceof Core){
                    base = s;
                    break;
                }
            }
            
            for(Security s:securities){
                //Security not held
                if(s instanceof Sector){
                    if(!holdings.containsKey(s)){
                       
                       if(calendar.getTime().after(s.buyDate)||calendar.getTime().equals(s.buyDate)){
                           updatePortfolio(new Trade(calendar.getTime(),base,s,s.allocation));
                       }

                    //Security held
                    }else{
                       if(calendar.getTime().after(s.sellDate)||calendar.getTime().equals(s.sellDate)){
                           updatePortfolio(new Trade(calendar.getTime(),s,base,s.allocation));
                       }
                    }
                    
                }else if(s instanceof Core){
                    ///only buy, don't sell, they have swapped buy/sell dates so only buys take care of both
                    if(!holdings.containsKey(s)){
                        if(calendar.getTime().after(s.buyDate)||calendar.getTime().equals(s.buyDate)){
                            updatePortfolio(new Trade(calendar.getTime(),base,s,holdings.get(base)[0]));
                            //update the base
                            base = s;
                        }
                        
                    }
                }
                
            }
            updatePortfolioValues(calendar.getTime());
            savePortfolio(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }
    }
    
    public void setDates(Date dateNow){
        Calendar c = Calendar.getInstance();
        c.setTime(dateNow);
        //System.out.println(dateNow);
        for(Security s:securities){
            if(compareDates(c.getTime(),s.buyDate)){
                s.buyDate.setYear(dateNow.getYear()+1);
            }else{
                s.buyDate.setYear(dateNow.getYear());
            }
            if(compareDates(c.getTime(),s.sellDate)){
                s.sellDate.setYear(dateNow.getYear()+1);
            }else{
                s.sellDate.setYear(dateNow.getYear());
            }
            
            //System.out.println(s.name+" | "+s.buyDate+" | "+s.sellDate);
        }
        //System.out.println("");
    }
    
    //returns true if date 1 >= date 2
    public boolean compareDates(Date date1,Date date2){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        
        if(cal1.get(Calendar.MONTH)>cal2.get(Calendar.MONTH)){
            return true;
        }else if(cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH)){
            if(cal1.get(Calendar.DAY_OF_MONTH)>cal2.get(Calendar.DAY_OF_MONTH)){
                return true;
            }
        }
        return false;
        
    }
    
    public void savePortfolio(Date date){
        historicalPortfolio.put(date, holdings);
    }
    
    public Security getCore(ArrayList<Security> securities){
        for(Security s:securities){
            if(s instanceof Core){
                return s;
                
            }
            
        }
        return null;
    }
    
    public void updatePortfolio(Trade trade){
        //subtract from base
        Double[] holdingFromStats = {holdings.get(trade.from)[0]-trade.percentage,getValue(trade.date,trade.from)};
        holdings.put(trade.from,holdingFromStats);
        //remove base in the case of bank shift
        if(holdings.get(trade.from)[0]<=0){
            holdings.remove(trade.from);
        }
        
        if(!holdings.containsKey(trade.to)){
            Double[] holdingToStats = {trade.percentage,getValue(trade.date,trade.to)};
            holdings.put(trade.to,holdingToStats);
        }else{
            Double[] holdingToStats = {holdings.get(trade.to)[0]+trade.percentage,getValue(trade.date,trade.to)};
            holdings.put(trade.to,holdingToStats);
        }
        
        System.out.println("");
        System.out.println(trade.date);
        
        System.out.println("Sold "+trade.from.name+", bought "+trade.to.name+" - "+trade.percentage);
        printHoldings();
        
        if(allocationOver()){
            System.out.println("Allocation over 100%, Error");
        }
        
        trades.add(trade);
    }
    
    public void updateHoldingValues(Date timeNow){
        for(Security s:holdings.keySet()){
            Double[] newStats = {holdings.get(s)[0],getValue(timeNow,s)};
            holdings.put(s, newStats);
        }
    }
    
    public double getValue(Date d,Security s){
        
    }
    
    public void printHoldings(){
        for(Security s:holdings.keySet()){
            System.out.println(s.name+": "+holdings.get(s));
        }
    }
    
    
        
    public boolean allocationOver(){
        double sum = 0;
        for(Double[] d:holdings.values()){
            sum+=d[0];
        }
        if(sum>100){
            return true;
        }
        return false;
    }

}
    
    
    
    

    
