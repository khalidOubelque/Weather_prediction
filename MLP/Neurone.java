package MLP;

import java.util.ArrayList;

import Data.ConstantVar;
import Data.Weather_spec;

public class Neurone {
	/* Remarks
	 * Each neuron in the hidden layer has is own bias constant.
	 * 
	 * 
	 * 
	 */
	private double[] inputLayer= new double[ConstantVar.inputNeuronsSize]; // Input Layer
	private double[] hiddenLayer = new double[ConstantVar.hiddenNeuronsSize]; // hidden Layer
	private double[] outputLayer = new double[ConstantVar.outputNeuronsSize]; // Output Layer
	private double[] localGradientOutput = new double[ConstantVar.outputNeuronsSize]; // Output Layer
	private double[] localGradientHidden = new double[ConstantVar.hiddenNeuronsSize]; // Output Layer

	private double[][] listInputWeight = new double[ConstantVar.inputNeuronsSize][ConstantVar.hiddenNeuronsSize]; // weight input layer
	private double[][] listHiddenWeight = new double[ConstantVar.hiddenNeuronsSize][ConstantVar.outputNeuronsSize]; //hidden input layer
	private double[] outBias = new double[ConstantVar.outputNeuronsSize]; //Bias weight output layer 
	private double[] hidBias = new double[ConstantVar.hiddenNeuronsSize];; //Bias weight hidden layer, value between -0.1 & 0.1
	
	private enum Neurone_input_Type // NOT necessary 
	{ 
		Humidity, Wind_speed, Wind_gust, Daily_rain, 
		Monthly_rain, Yearly_rain, Uv; 
	}

	public Neurone() {
		super();
		initWeights();
	}

	public void initWeights(){
	
		//init Input Layer 
		for(int i=0; i<ConstantVar.inputNeuronsSize; i++){
			for(int j=0; j<ConstantVar.hiddenNeuronsSize; j++){
				listInputWeight[i][j] = (Math.random() * ((ConstantVar.max - ConstantVar.min))) + ConstantVar.min; 
				//System.out.println("----------START INIT INPUT WEIGHT ----------");
				//System.out.println(listInputWeight[i][j]);
			}
		}
		//System.out.println("----------END INIT INPUT WEIGHT ----------");
		//init Hidden Layer 
		for(int i=0; i<ConstantVar.hiddenNeuronsSize; i++){
			for(int j=0; j<ConstantVar.outputNeuronsSize; j++){
				listHiddenWeight[i][j] = (Math.random() * ((ConstantVar.max - ConstantVar.min))) + ConstantVar.min; 
				//System.out.println("----------START INIT HIDDEN WEIGHT ----------");
				//System.out.println(listHiddenWeight[i][j]);
			}
		}
		//System.out.println("----------END INIT HIDDEN WEIGHT ----------");
		//init Bias Hidden Layer
		for(int i=0; i<ConstantVar.hiddenNeuronsSize; i++){
			hidBias[i] = (Math.random() * ((ConstantVar.maxBias - ConstantVar.minBias))) + ConstantVar.minBias; 
			//System.out.println("----------START INIT HIDDEN BIAS WEIGHT ----------");
			//System.out.println(hidBias[i]);
		}
		
		//init Bias Output Layer
		for(int i=0; i<ConstantVar.outputNeuronsSize; i++){
			outBias[i] = (Math.random() * ((ConstantVar.maxBias - ConstantVar.minBias))) + ConstantVar.minBias;  
			//System.out.println("----------START INIT OUTPUT BIAS WEIGHT ----------");
			//System.out.println(outBias[i]);
		}
		//System.out.println("----------END INIT BIAS WEIGHT ----------");
		
	}
	
	
	public double[] getOutputLayer() {
		return outputLayer;
	}

	public void setOutputLayer(double[] outputLayer) {
		this.outputLayer = outputLayer;
	}

	public double[] getLocalGradientOutput() {
		return localGradientOutput;
	}

	public void setLocalGradientOutput(double[] localGradientOutput) {
		this.localGradientOutput = localGradientOutput;
	}

	public double[] getLocalGradientHidden() {
		return localGradientHidden;
	}

	public void setLocalGradientHidden(double[] localGradientHidden) {
		this.localGradientHidden = localGradientHidden;
	}

	
	public double[] getHiddenLayer() {
		return hiddenLayer;
	}

	public void setHiddenLayer(double[] hiddenLayer) {
		this.hiddenLayer = hiddenLayer;
	}

	public double[] getInputData(Weather_spec w){
		inputLayer = new double[ConstantVar.inputNeuronsSize];
		inputLayer[0] = w.getHumidity();
		inputLayer[1] = w.getWind_speed();
		inputLayer[2] = w.getWind_gust();
		inputLayer[3] = w.getDaily_rain();
		inputLayer[4] = w.getMonthly_rain();
		inputLayer[5] = w.getYearly_rain();
		inputLayer[6] = w.getUv();
		return inputLayer;
	}
	
	public double getDesiredOutput(Weather_spec w){
		double desiredOutput = w.getTemperature();
		return desiredOutput;
	}
	
	
	/************* DATA NORMALIZATION ************/ 
	//y = (x - min) / (max - min)
			
	public double[] inputNormalization(double[] input){
		
		for(int i=0; i<input.length; i++){
			input[i] = (input[i] - ConstantVar.minNorm)/(ConstantVar.maxNorm-ConstantVar.minNorm);
			//System.out.println("Normalization : "+input[i]);
		}
		return input;
	}
	
	public double outputDesiredNormalization(double output){
			double desired;
			desired = (output - ConstantVar.minNorm)/(ConstantVar.maxNorm-ConstantVar.minNorm);
			//System.out.println("Normalization : "+output);
		return desired;
	}
	
	/************* DATA DENORMALIZATION ************/ 
	public double[] inputDeNormalization(double[] input){
		
		for(int i=0; i<ConstantVar.inputNeuronsSize; i++){
			input[i] = ((ConstantVar.maxNorm-ConstantVar.minNorm)*input[i]) + ConstantVar.minNorm;
			//System.out.println("DeNormalization : "+input[i]);

		}
		return input;
	}
	
	public double[] outputDeNormalization(double[] output){
		
		for(int i=0; i<ConstantVar.outputNeuronsSize; i++){
			output[i] = ((ConstantVar.maxNorm-ConstantVar.minNorm)*output[i]) + ConstantVar.minNorm;
		}
		return output;
	}

	public double[] getInputLayer() {
		return inputLayer;
	}

	public void setInputLayer(double[] inputLayer) {
		this.inputLayer = inputLayer;
	}

	public double[][] getListInputWeight() {
		return listInputWeight;
	}

	public void setListInputWeight(double[][] listInputWeight) {
		this.listInputWeight = listInputWeight;
	}

	public double[][] getListHiddenWeight() {
		return listHiddenWeight;
	}

	public void setListHiddenWeight(double[][] listHiddenWeight) {
		this.listHiddenWeight = listHiddenWeight;
	}

	public double[] getOutBias() {
		return outBias;
	}

	public void setOutBias(double[] outBias) {
		this.outBias = outBias;
	}

	public double[] getHidBias() {
		return hidBias;
	}

	public void setHidBias(double[] hidBias) {
		this.hidBias = hidBias;
	}
	
	
	
	
	
	
}
