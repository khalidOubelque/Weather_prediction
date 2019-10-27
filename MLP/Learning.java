package MLP;

import java.util.ArrayList;
import java.util.List;

import Data.ConstantVar;
import Data.DataHandler;
import Data.Weather_spec;

public class Learning {

	List<Double> errorCycle ;
	public void learning(){
		
		/********************* TEST INITIALIZE WEIGHT *************************/
		double E = 0; // total error
		errorCycle = new ArrayList<Double>();  //intiliaze list of quadratic errors
		String path = "C:/Users/hp amd/Desktop/test_csv.csv";
		DataHandler data = new DataHandler(path);
		List<Weather_spec> listWeather = data.getData();
		/* Get Data from excel file
		for (Weather_spec w : listWeather) {
			System.out.println("Output : "+w.getTemperature()+"----"+"Input : "+w.getHumidity()+ " "+w.getYearly_rain());
		}*/
		
		Neurone neurone = new Neurone(); //initialize weights
		double sum = 0 ;
		int count_while = 0;
		do
		{
			count_while++;
			E=0;
			for (Weather_spec weather_spec : listWeather) {
				neurone.getInputData(weather_spec);// array of input line by line
				double desiredOutput = neurone.getDesiredOutput(weather_spec);//desiredOutput
				//Normalization
				neurone.inputNormalization(neurone.getInputLayer());
				desiredOutput = neurone.outputDesiredNormalization( desiredOutput );
	
				//neurone.inputDeNormalization(neurone.getInputLayer()); //This is for Denormalization to get the original values it s has been tested works fine
	
				for(int i=0; i<ConstantVar.hiddenNeuronsSize; i++){
					sum =0;
					for(int j=0; j<ConstantVar.inputNeuronsSize ; j++){
						sum += (neurone.getInputLayer()[j] * neurone.getListInputWeight()[j][i]);
					}
					
					sum += neurone.getHidBias()[i];
					neurone.getHiddenLayer()[i] = 1.0 / (1.0 + Math.exp(-sum)); // to be reviewed, works fine 
					
				}	
				
				//Calculate Output
				double[] errOut = new double[ConstantVar.outputNeuronsSize];
				for(int i=0; i<ConstantVar.outputNeuronsSize; i++){
					sum=0;
					for(int j=0; j<ConstantVar.hiddenNeuronsSize; j++){
						sum+= neurone.getHiddenLayer()[j]*neurone.getListHiddenWeight()[j][i];
					}
					sum+=neurone.getOutBias()[i];
					//System.out.println("Sum output : "+sum);
	
					neurone.getOutputLayer()[i] = 1.0 / (1.0 + Math.exp(-sum)); // to be reviewed, works fine
					//System.out.println("output : "+neurone.getOutputLayer()[i]);
					//System.out.println("desiredOutput : "+desiredOutput);
	
					errOut[i] = (Math.pow((desiredOutput-neurone.getOutputLayer()[i]), 2))*1/2;
					//System.out.println("Error out : "+errOut[i]);
					
					//Quadratiq error
					E+=errOut[i]; 
					
					/***************************************Backpropagation*********************************************/
					//Local gradient for Output
					// delta_{o1} = -(target_{o1} - out_{o1}) * out_{o1}(1 - out_{o1})
					neurone.getLocalGradientOutput()[i] = (desiredOutput-neurone.getOutputLayer()[i])*neurone.getOutputLayer()[i]*(1-neurone.getOutputLayer()[i]);
					
					
					//Adjust hidden layer weights
					for(int j=0; j<ConstantVar.hiddenNeuronsSize; j++){
						neurone.getListHiddenWeight()[j][i] += ConstantVar.learningRate * neurone.getLocalGradientOutput()[i] * neurone.getHiddenLayer()[j];
					}
					//Adjust Bias
					neurone.getOutBias()[i]+= (ConstantVar.learningRate * neurone.getLocalGradientOutput()[i]);
				}
				
				//Adjust input weights
			
				double[] errHid = new double[ConstantVar.hiddenNeuronsSize];
				double contribution=0;

				for(int i=0; i<ConstantVar.hiddenNeuronsSize; i++){
					contribution=0;
					for(int j=0; j<ConstantVar.outputNeuronsSize; j++){
						contribution+=neurone.getLocalGradientOutput()[j]*neurone.getListHiddenWeight()[i][j];
					}
					errHid[i]+=neurone.getHiddenLayer()[i]* (1-neurone.getHiddenLayer()[i])*contribution;
					
					//Mise à jour des poids de la couche cachée
					
					for (int k = 0; k < ConstantVar.inputNeuronsSize; k++){
						neurone.getListInputWeight()[k][i]+=ConstantVar.learningRate*errHid[i]*neurone.getInputLayer()[k];
					}
					neurone.getHidBias()[i]+= (ConstantVar.learningRate * errHid[i]);

				}
			}
			errorCycle.add(E);
			System.out.println("Error Calculated is "+E);
		
		}while(E>ConstantVar.Emax);
		System.out.println("Count loop "+count_while);
		
	}
	public List<Double> getErrorCycle() {
		return errorCycle;
	}
	public void setErrorCycle(ArrayList<Double> errorCycle) {
		this.errorCycle = errorCycle;
	}
	
	
}
