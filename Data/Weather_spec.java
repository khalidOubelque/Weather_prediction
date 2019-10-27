package Data;

import java.util.ArrayList;

public class Weather_spec {
	
	private double temperature;
	private double humidity;
	private double wind_speed;
	private double wind_gust;
	private double daily_rain;
	private double monthly_rain;
	private double yearly_rain;
	private double uv;
	private ArrayList<Double> listOfWeightIn;
	private ArrayList<Double> listOfWeightOut;
	
	public Weather_spec() {
		super();
	}
	
	public void initNeuronWeight(){
		
		
	}
	
	
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public ArrayList<Double> getListOfWeightIn() {
		return listOfWeightIn;
	}

	public void setListOfWeightIn(ArrayList<Double> listOfWeightIn) {
		this.listOfWeightIn = listOfWeightIn;
	}

	public ArrayList<Double> getListOfWeightOut() {
		return listOfWeightOut;
	}

	public void setListOfWeightOut(ArrayList<Double> listOfWeightOut) {
		this.listOfWeightOut = listOfWeightOut;
	}

	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getWind_speed() {
		return wind_speed;
	}
	public void setWind_speed(double wind_speed) {
		this.wind_speed = wind_speed;
	}
	public double getWind_gust() {
		return wind_gust;
	}
	public void setWind_gust(double wind_gust) {
		this.wind_gust = wind_gust;
	}
	public double getDaily_rain() {
		return daily_rain;
	}
	public void setDaily_rain(double daily_rain) {
		this.daily_rain = daily_rain;
	}
	public double getMonthly_rain() {
		return monthly_rain;
	}
	public void setMonthly_rain(double monthly_rain) {
		this.monthly_rain = monthly_rain;
	}
	public double getYearly_rain() {
		return yearly_rain;
	}
	public void setYearly_rain(double yearly_rain) {
		this.yearly_rain = yearly_rain;
	}
	public double getUv() {
		return uv;
	}
	public void setUv(double uv) {
		this.uv = uv;
	}
	
	
}
