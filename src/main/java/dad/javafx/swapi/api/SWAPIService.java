package dad.javafx.swapi.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SWAPIService {

	public SWAPIService() {
		Unirest.setObjectMapper(new JacksonObjectMapper());
	}

	public People getPeople(int peopleId) throws UnirestException {
		System.out.println("recuperando people " + peopleId);
		
		HttpResponse<People> response = 
				Unirest
					.get("https://swapi.dev/api/people/{id}")
					.routeParam("id", "" + peopleId)
					.asObject(People.class);
		
		if (response.getStatus() != 200) return null;
		
		return response.getBody();
		
	}

	public Vehicle getVehicle(String vehicleUrl) throws UnirestException {
		System.out.println("recuperando vehicle " + vehicleUrl);		
		return Unirest
			.get(vehicleUrl)
			.asObject(Vehicle.class)
			.getBody();
	}

	public static void main(String[] args) throws UnirestException {
		SWAPIService service = new SWAPIService();
		
		People luke = service.getPeople(1);
		System.out.println(luke.getName());
		
		People darthVader = service.getPeople(4);
		System.out.println(darthVader.getName());
		
		Vehicle v14 = service.getVehicle("https://swapi.dev/api/vehicles/33");
		System.out.println(v14.getName());
	}

}
