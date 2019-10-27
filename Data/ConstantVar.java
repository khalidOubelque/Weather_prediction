package Data;

public class ConstantVar {
	public static final int inputNeuronsSize = 7; 
	public static final int hiddenNeuronsSize = 20; 
	public static final int outputNeuronsSize = 1; 

	public static final double learningRate = 0.8; 	
	public static final double min = -0.5; //  Set To Your Desired Min Value
	public static final double max = 0.5; //    Set To Your Desired Max Value
	public static final double minBias = -0.1; //  value between -0.1 & 0.1
	public static final double maxBias = 0.1; //   value between -0.1 & 0.1
	
	public static final double minNorm = 0.0; //  Min value in dataSet
	public static final double maxNorm = 99.0; //  Max value in dataSet
	
	public static final double Emax = 0.0001; //  Max value in dataSet  0.00002


}
