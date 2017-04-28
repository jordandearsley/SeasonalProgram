/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SeasonalProgram;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author jordandearsley
 */
public class TradingDay {
    public Date d;
    public Map<Security, Double[]> holdings;
    public double growth;
    public TradingDay(Date d, Map<Security,Double[]> holdings,double growth){
        this.d = d;
        this.holdings = holdings;
        this.growth = growth;
    }
}
