
package simulationmodel;


public class Pancreas {
    
    private double CurrentBsl;
    private double ICR;
    private double CorFactor;
    
    private double TDD;
    private double Basal;
    private double Bolus;
    
    public ProcessCycle processCycle;
    
    public void setParameters(double TDD)
    {
        this.TDD = TDD;
        this.Basal = TDD * DoseConstants.BASAL_FACTOR;
        this.Bolus = TDD * DoseConstants.BOLUS_FACTOR;
        this.ICR = DoseConstants.CHO_500 / TDD;
        this.CorFactor = DoseConstants.COR_FACTOR / TDD;
    }
    
    public void setDurations(double night, double day, double evening)
    {
    	/*DurationLoop = new double[DoseConstants.DURATION_LOOP_INDEX];
    	
    	DurationLoop[0] = DurationDinnerToBreakfast = night;
    	DurationLoop[1] = DurationBreakfastToLunch = day;
    	DurationLoop[2] = DurationLunchToDinner = evening;*/
    }
    
    public void setCorFactor(double CorFactor)
    {
    	this.CorFactor = CorFactor;
    }
    
    public double getCorFactor()
    {
    	return this.CorFactor;
    }
    
    public double getCurrentChoDose(double CHO)
    {
    	return CHO / this.ICR;
    }
    
    public void setTdd(double TDD)
    {
        setParameters(TDD);
    }
    
    public void setBasal(double Basal)
    {
        this.Basal = Basal;
    }
    
    public void setBolus(double Bolus)
    {
        this.Bolus = Bolus;
    }
    
    public double getTdd()
    {
        return TDD;
    }
    
    public void setCurrentBsl(double BSL)
    {
        this.CurrentBsl = BSL;
    }
    
    public double getCurrentBsl()
    {
        return CurrentBsl;
    }
     
    public void setDurationDinnerToBreakfast(double duration)
    {
    	//this.DurationDinnerToBreakfast = duration;
    }
    
    public void setDurationBreakfastToLunch(double duration)
    {
    	//this.DurationBreakfastToLunch = duration;
    }
    
    public void setDurationLunchToDinner(double duration)
    {
    	//this.DurationLunchToDinner = duration;
    }
    
    public double getDurationDinnerToBreakfast()
    {
    	//return DurationDinnerToBreakfast;
    	return 0.0;
    }
    
    public double getDurationBreakfastToLunch()
    {
    	//return DurationBreakfastToLunch;
    	return 0.0;
    }
    
    public double getDurationLunchToDinner()
    {
    	//return DurationLunchToDinner;
    	return 0.0;
    }
    
    public void addCho()
    {
    	
    }
    
    public void balanceBsl()
    {
    	
    }
    
    public double injectInsulin(double insulinDoses)
    {
    	return 0;
    }
    
    public double injectGlucagon()
    {
    	return DoseConstants.HYPO_DOSE;
    }
    
    public double getBasal()
    {
        return Basal;
    }
    
    public double getBolus()
    {
        return Bolus;
    }
    
    public void calculateDose(double weight)
    {
    	setTdd(weight * 0.5);
        
    }
    
    public void calculateDose(double weight, double Tdd)
    {
    	setTdd(Tdd);
    }
}
