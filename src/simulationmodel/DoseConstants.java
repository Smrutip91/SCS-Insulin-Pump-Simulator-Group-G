package simulationmodel;

public class DoseConstants {

	// 500 Rules
	public static final double CHO_500 = 500;
	// Insulin Sensitivity Factor
	public static final double ISF_1700 = 1700;
	// Correction Factor
	public static final double COR_FACTOR = 100;
    // 15/15 Rules
	public static final double HYPO_DOSE = 15;
    // 40% of TDD
	public static final double BASAL_FACTOR = 0.4;
    // 60% of TDD
	public static final double BOLUS_FACTOR = 0.6;
	// Loop index using number of meals per day
	public static final int DURATION_LOOP_INDEX = 3;
    
	public static final double TARGET_BSL_BEFORE_DINNER = 5.5;
	public static final double TARGET_BSL_AFTER_DINNER = 8.0;
	public static final double TARGET_BSL_DURING_DINNER = 0;
	public static final double TARGET_BSL_BEFORE_BREAKFAST = 4.5;
	public static final double TARGET_BSL_AFTER_BREAKFAST = 7.5;
	public static final double TARGET_BSL_DURING_BREAKFAST = 0;
	public static final double TARGET_BSL_BEFORE_LAUNCH = 6.0;
	public static final double TARGET_BSL_AFTER_LAUNCH = 8.0;
	public static final double TARGET_BSL_DURING_LAUNCH = 0;
    
}