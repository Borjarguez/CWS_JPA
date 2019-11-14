package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TVEHICLES")
public class Vehicle extends BaseEntity {

	@Column(unique = true)
	private String plateNumber;

	private String make;
	private String model;

	@ManyToOne
	private Client client;

	@ManyToOne
	private VehicleType vehicleType;

	@OneToMany(mappedBy = "vehicle")
	private Set<WorkOrder> workOrders = new HashSet<WorkOrder>();

	/**
	 * Mapper's constructor
	 */
	Vehicle() {
	}

	/**
	 * Public constructor
	 * 
	 * @param plateNumber, the plate number
	 */
	public Vehicle(String plateNumber) {
		super();
		this.plateNumber = plateNumber;
	}

	/**
	 * Public constructor
	 * 
	 * @param plate, the plate
	 * @param make,  the make
	 * @param model, the model
	 */
	public Vehicle(String plate, String make, String model) {
		this(plate);
		this.make = make;
		this.model = model;
	}

	/**
	 * Method which returns the client
	 * 
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Method which sets the client
	 * 
	 * @param client, the new client
	 */
	void _setClient(Client client) {
		this.client = client;
	}

	/**
	 * Method which returns the vehicle type
	 * 
	 * @return the vehicle type
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * Method which set the type of vehicle
	 * 
	 * @param type, the vehicle type
	 */
	void _setType(VehicleType type) {
		this.vehicleType = type;
	}

	/**
	 * Method which sets the plate number
	 * 
	 * @param plateNumber, the new plate number
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	/**
	 * Method which sets the make
	 * 
	 * @param make, the new make
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * Method which sets the model
	 * 
	 * @param model, the new model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Method which returns the plate number
	 * 
	 * @return the plate number
	 */
	public String getPlateNumber() {
		return plateNumber;
	}

	/**
	 * Method which returns the make
	 * 
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * Method which returns the model
	 * 
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Method which returns the work orders
	 * 
	 * @return safe return of the work orders
	 */
	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<WorkOrder>(workOrders);
	}

	/**
	 * Method which returns the work orders
	 * 
	 * @return the work orders
	 */
	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plateNumber == null) ? 0 : plateNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (plateNumber == null) {
			if (other.plateNumber != null)
				return false;
		} else if (!plateNumber.equals(other.plateNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [plateNumber=" + plateNumber + ", make=" + make + ", model=" + model + "]";
	}

}
