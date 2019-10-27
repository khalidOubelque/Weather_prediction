package Test;

import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Data.ConstantVar;
import Data.DataHandler;
import Data.Weather_spec;
import MLP.Classification;
import MLP.GraphPanel;
import MLP.Learning;
import MLP.Neurone;

public class Test {

	public static void main(String[] args) {
		Learning l = new Learning();
		l.learning();
//		for(Double d : l.getErrorCycle()){
//			System.out.println("Error Cycle is "+d);
//		}
		
//		GraphPanel g = new GraphPanel(l.getErrorCycle());
//
//		JFrame testFrame = new JFrame();
//		testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		testFrame.add(g);
//		testFrame.setBounds(10, 127, 755, 387);
//        testFrame.setVisible(true);
        
        Classification c = new Classification();
		Weather_spec nr = new Weather_spec();
		nr.setHumidity(53.0);
		nr.setWind_speed(0.2);
		nr.setWind_gust(4.9);
		nr.setDaily_rain(0.0);
		nr.setMonthly_rain(1.05);
		nr.setYearly_rain(16.71);
		nr.setUv(5.0);
        double output = c.classification_func(nr);
        System.out.println(output);
	}

}
