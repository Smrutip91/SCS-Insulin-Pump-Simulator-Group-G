/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationmodel;
import java.util.*;

public class SimulationModel {

    public void init()
    {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        Date date = new Date();
        Date newDate = new Date(date.getTime() + 5 * 60 * 1000L);
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 5);
        newDate = cal.getTime();

        System.out.println(newDate);
        
    }    
}