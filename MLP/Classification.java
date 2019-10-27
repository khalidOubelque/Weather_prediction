package MLP;

import Data.ConstantVar;
import Data.Weather_spec;

public class Classification {
	private Neurone neurone = new Neurone();
	private double[] inputLayer; // Input Layer
	private double[] hiddenLayer; // hidden Layer
	private double[] outputLayer; // Output Layer
	
	public double classification_func(Weather_spec w){
		
		inputLayer = new double[ConstantVar.inputNeuronsSize]; // Input Layer
		hiddenLayer = new double[ConstantVar.hiddenNeuronsSize]; // hidden Layer
		outputLayer = new double[ConstantVar.outputNeuronsSize]; // Output Layer
		double max = 0.0;
		inputLayer = neurone.getInputData(w);//Mapping input 
		
		inputLayer = neurone.inputNormalization(inputLayer);//To be tested passage par valeur !!!!
		
		/*
		System.out.println();
		int res;
		int i =2;
		System.out.println(i);
		res = testpassageValeur(i);
		System.out.println(i);
		*/

		double sum =0.0;
		for(int i=0; i<ConstantVar.hiddenNeuronsSize; i++){
			sum =0;
			for(int j=0; j<ConstantVar.inputNeuronsSize ; j++){
				sum += (inputLayer[j] * neurone.getListInputWeight()[j][i]);
			}
			
			sum += neurone.getHidBias()[i];
			hiddenLayer[i] = 1.0 / (1.0 + Math.exp(-sum)); // to be reviewed, works fine 
			
		}
		double[] errOut = new double[ConstantVar.outputNeuronsSize];
		for(int i=0; i<ConstantVar.outputNeuronsSize; i++){
			sum=0;
			for(int j=0; j<ConstantVar.hiddenNeuronsSize; j++){
				sum+= hiddenLayer[j]*neurone.getListHiddenWeight()[j][i];
			}
			sum+=neurone.getOutBias()[i];

			outputLayer[i] = 1.0 / (1.0 + Math.exp(-sum)); // to be reviewed, works fine
		}
		
		
		//Denormalization
		outputLayer = neurone.outputDeNormalization(outputLayer); 
		for(int i=0; i<ConstantVar.outputNeuronsSize; i++){
			if(outputLayer[i]>max){
				max = outputLayer[i];
			}
		}
		return max;
	}
	
	public int testpassageValeur(int i){
		i = 12;
		return i;
	}

}
