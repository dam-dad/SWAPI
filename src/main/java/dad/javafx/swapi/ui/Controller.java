package dad.javafx.swapi.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mashape.unirest.http.exceptions.UnirestException;

import dad.javafx.swapi.api.People;
import dad.javafx.swapi.api.SWAPIService;
import dad.javafx.swapi.api.Vehicle;
import dad.javafx.swapi.model.Personaje;
import dad.javafx.swapi.model.Vehiculo;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {
	
	// business logic
	
	private SWAPIService service = new SWAPIService();
	
	// model
	
	private ListProperty<Personaje> personajes = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Vehiculo> vehiculos = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<Personaje> personajeSeleccionado = new SimpleObjectProperty<>();
	
	// view
	
    @FXML
    private GridPane view;

    @FXML
    private TableView<Personaje> personajesTable;

    @FXML
    private TableColumn<Personaje, String> nombreColumn;

    @FXML
    private TableColumn<Personaje, Number> pesoColumn;

    @FXML
    private TableColumn<Personaje, Number> alturaColumn;

    @FXML
    private TableView<Vehiculo> vehiculosTable;

    @FXML
    private TableColumn<Vehiculo, String> vehiculoColumn;
    
    public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/View.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.personajesTable.itemsProperty().bind(personajes);

		this.vehiculosTable.itemsProperty().bind(vehiculos);

		this.personajeSeleccionado.bind(this.personajesTable.getSelectionModel().selectedItemProperty());
		
		this.nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		this.pesoColumn.setCellValueFactory(v -> v.getValue().pesoProperty());
		this.alturaColumn.setCellValueFactory(v -> v.getValue().alturaProperty());

		this.vehiculoColumn.setCellValueFactory(v -> v.getValue().nombreProperty());

		this.personajeSeleccionado.addListener((o, ov, nv) -> onPersonajeSeleccionadoChanged(o, ov, nv));
		
		this.retrieveData();
		
	}
	
	private void onPersonajeSeleccionadoChanged(ObservableValue<? extends Personaje> o, Personaje ov, Personaje nv) {
		
		if (ov != null ) {
			
			this.vehiculos.unbind();
			this.vehiculos.set(null);
			
		}

		if (nv != null) {
		
			this.vehiculos.bind(nv.vehiculosProperty());
			
		}
		
	}

	private void retrieveData() {

		
		try {
			
			for (int i = 1; i <= 30; i++) {
			
				People people = service.getPeople(i);

				if (people == null) continue;
				
				// mapeo de objetos
				Personaje personaje = new Personaje();
				personaje.setNombre(people.getName());
				try {
					personaje.setAltura(Integer.parseInt(people.getHeight()));
				} catch (NumberFormatException e) {} 
				try {
					personaje.setPeso(Integer.parseInt(people.getMass()));
				} catch (NumberFormatException e) {} 
				
				for (String vehicleUrl : people.getVehicles()) {
					Vehicle vehicle = service.getVehicle(vehicleUrl);
					Vehiculo vehiculo = new Vehiculo();
					vehiculo.setNombre(vehicle.getName());
					personaje.getVehiculos().add(vehiculo);
				}
				
				this.personajes.add(personaje);
				
			}
			
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		
		
		
	}

	public GridPane getView() {
		return view;
	}

}
