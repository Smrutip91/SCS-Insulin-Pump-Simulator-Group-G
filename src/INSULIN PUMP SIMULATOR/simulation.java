
package javaapplication11;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
//import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.UIManager;
import simulationmodel.DoseConstants;

public class simulation extends Thread {

    private String strMIN;
    private String strHOR;
    private long watchStart, watchEnd;
    int sleep;// =100;     //i hours is 30seconds
    public int insulin = 40;

    private double CurrentBsl;
    private double ICR;
    private double CorFactor;
    
    private double TDD;
    private double Basal;
    private double Bolus;
    
    public void setParameters(double TDD)
    {
        this.TDD = TDD;
        this.Basal = TDD * DoseConstants.BASAL_FACTOR;
        this.Bolus = TDD * DoseConstants.BOLUS_FACTOR;
        this.ICR = DoseConstants.CHO_500 / TDD;
        this.CorFactor = DoseConstants.COR_FACTOR / TDD;
    }
    public void setTdd(double TDD)
    {
        setParameters(TDD);
    }
    
    public void calculateDose(double weight)
    {
    	setTdd(weight * 0.5);
    }
    
    public void calculateDose(double weight, double Tdd)
    {
    	setTdd(Tdd);
    }
    
    public void setBasal(double Basal)
    {
        this.Basal = Basal;
    }
    
    public void setBolus(double Bolus)
    {
        this.Bolus = Bolus;
    }
    
    public int getInsulinLevel() {
        return insulin;
    }
    public void setInsulineLevel(int  val){
    insulin=val;
    }

    @Override
    public void run() {
        ReadComport();
    }

    public void ReadComport() {

        Vector<Double> expectedBsl = new Vector<Double>();
        Vector<Double> doseTimes = new Vector<Double>();

        expectedBsl.add(10.0);
        expectedBsl.add(4.5);
        expectedBsl.add(9.0);
        //expectedBsl.add(8.0);
        // expectedBsl.add(11.0);
        expectedBsl.add(5.5);
        expectedBsl.add(10.0);
        expectedBsl.add(6.0);

        doseTimes.add(0.0);
        doseTimes.add(11.0);
        doseTimes.add(13.0);
        //doseTimes.add(14.0);
        //doseTimes.add(15.0);
        doseTimes.add(16.0);
        doseTimes.add(18.0);
        doseTimes.add(24.0);

        //double []expectedBsl = {9, 4.5, 9, 5.5, 10, 6};
        //double []doseTimes = {0, 11, 13, 16, 18, 24};
        double bslDiff;
        double bslIncrRate;
        double timeSlicePerHour = 3;
        double nOfTimeSlice;
        double currentBsl;
        double time = 0;
        double timeSlice = 20;
        sleep = 200;     //i hours is 30seconds
        boolean injected = true;
        PrintWriter writer = null;
        boolean morning = true;
        String msg = "";
        
        try {
            writer = new PrintWriter("data.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            bslDiff = Math.abs(expectedBsl.get(i + 1) - expectedBsl.get(i));
            nOfTimeSlice = Math.abs((doseTimes.get(i + 1) - doseTimes.get(i)) * timeSlicePerHour);
            currentBsl = expectedBsl.get(i);
            bslIncrRate = bslDiff / nOfTimeSlice;

            while (nOfTimeSlice-- > 0) {
                writer.print(" Time " + time);
                writer.println("  BSL " + currentBsl);
                System.out.print(" Time " + time);
                System.out.println("  BSL " + currentBsl);
                setVales(currentBsl, time);

                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // BSL Decrement
                if (i % 2 == 0) {
                    currentBsl -= bslIncrRate;
                    injected = true;
                } else {

                    // BSL Increment
                    if (injected) {
                        injected = false;
                        if (getInsulinLevel() > 0) {
                            double dose;
                            if (morning)
                            {
                                dose = this.Bolus;
                                morning = false;
                                msg = " Morning ";
                            }
                            else
                            {
                                dose = this.Basal;
                                morning = true;
                                msg = " Night ";
                            }
                        //    System.out.println(" Insulin injected " + insulin);                            
                             insulin = insulin - 10;
                             NewJFrame.mainFrameOBJ.setJprogressValInsuline(dose);
                        
                               
                        }
                    }
                    currentBsl += bslIncrRate;
                }

                time += timeSlice;

                NewJFrame.mainFrameOBJ.chart.changechartLine(0, time, 0, currentBsl);

            }

            System.out.println(" ======== ");

            // Infinite for loop
            if (i == 4) {
                i = -1;
            }
        }
        writer.close();
    }

    public void setVales(double bsl, double time) {

        if (NewJFrame.mainFrameOBJ.getJprogressVal() > 0) {

            NewJFrame.mainFrameOBJ.setJprogressVal(time);
        }

        NewJFrame.mainFrameOBJ.setBSL(bsl);

    }
}
