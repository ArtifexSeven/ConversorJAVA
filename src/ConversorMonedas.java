	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import org.json.JSONObject;


	public class ConversorMonedas {

	   private static final String API_KEY = "dcb56a135a717b2b60dbe56409b2827d";
	   private static final String API_URL = "http://api.exchangeratesapi.io/v1/latest?access_key=" + API_KEY;

	    public static double obtenerTasaCambio(String monedaDestino) throws IOException {
	        URL url = new URL(API_URL);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");

	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        StringBuilder response = new StringBuilder();
	        String line;

	        while ((line = reader.readLine()) != null) {
	            response.append(line);
	        }

	        reader.close();

	        JSONObject jsonResponse = new JSONObject(response.toString());
	        JSONObject rates = jsonResponse.getJSONObject("rates");
	        return rates.getDouble(monedaDestino);
			
	        
	    }
	}
 
	
