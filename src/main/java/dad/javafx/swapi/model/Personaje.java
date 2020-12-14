package dad.javafx.swapi.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Personaje {

	private StringProperty nombre = new SimpleStringProperty();
	private IntegerProperty peso = new SimpleIntegerProperty();
	private IntegerProperty altura = new SimpleIntegerProperty();
	private ListProperty<Vehiculo> vehiculos = new SimpleListProperty<>(FXCollections.observableArrayList());

	public final StringProperty nombreProperty() {
		return this.nombre;
	}

	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final IntegerProperty pesoProperty() {
		return this.peso;
	}

	public final int getPeso() {
		return this.pesoProperty().get();
	}

	public final void setPeso(final int peso) {
		this.pesoProperty().set(peso);
	}

	public final IntegerProperty alturaProperty() {
		return this.altura;
	}

	public final int getAltura() {
		return this.alturaProperty().get();
	}

	public final void setAltura(final int altura) {
		this.alturaProperty().set(altura);
	}

	public final ListProperty<Vehiculo> vehiculosProperty() {
		return this.vehiculos;
	}

	public final ObservableList<Vehiculo> getVehiculos() {
		return this.vehiculosProperty().get();
	}

	public final void setVehiculos(final ObservableList<Vehiculo> vehiculos) {
		this.vehiculosProperty().set(vehiculos);
	}

}
