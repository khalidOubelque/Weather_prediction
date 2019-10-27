package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class DataHandler {

	private String path;
	private List<Weather_spec> weather_data = new ArrayList<Weather_spec>();

	public DataHandler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataHandler(String path) {
		super();
		this.path = path;
	}



	public List<Weather_spec> getData(){
		if(this.weather_data.size() == 0){//to upload the list one time
			String line = "";
			Weather_spec nr = new Weather_spec();
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while ((line = br.readLine()) != null){
				    String[] cols = line.split(",");
				    if(cols[0].equals("temperature")){
				    	continue;
				    }
					nr = new Weather_spec();
					nr.setTemperature(Double.parseDouble(cols[0]));
					nr.setHumidity(Double.parseDouble(cols[1]));
					nr.setWind_speed(Double.parseDouble(cols[2]));
					nr.setWind_gust(Double.parseDouble(cols[3]));
					nr.setDaily_rain(Double.parseDouble(cols[4]));
					nr.setMonthly_rain(Double.parseDouble(cols[5]));
					nr.setYearly_rain(Double.parseDouble(cols[6]));
					nr.setUv(Double.parseDouble(cols[7]));
					
					weather_data.add(nr);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return weather_data;
		
	}
	
	
	
}
