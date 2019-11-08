package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="TVEHICLETYPES")
public class VehicleType extends BaseEntity{
	
	@Column(unique=true) private String name;
	private double pricePerHour;
	private int minTrainingHours;
	
	@OneToMany(mappedBy="vehicleType")
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();
	
	@OneToMany(mappedBy="vehicleType")
	private Set<Certificate> certificates = new HashSet<Certificate>();
	
	@OneToMany(mappedBy = "vehicleType")
	private Set<Dedication> dedications = new HashSet<Dedication>();
	
	public VehicleType() {}

	public VehicleType(String name) {
		super();
		this.name = name;
	}

	public VehicleType(String name, double pricePerHour) {
		this(name);
		this.pricePerHour = pricePerHour;
	}

	public Set<Vehicle> getVehicles() {
		return new HashSet<Vehicle>(vehicles);
	}
	
	Set<Vehicle> _getVehicles() {
		return vehicles;
	}
	
	Set<Certificate> _getCertificates() {
		return certificates;
	}
	
	public Set<Certificate> getCertificates() {
		return new HashSet<Certificate>(certificates);
	}

	public String getName() {
		return name;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		VehicleType other = (VehicleType) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VehicleType [name=" + name + ", pricePerHour=" + pricePerHour + "]";
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// EXTENSION /////////////////////////////////////////////
	
	Set<Dedication> _getDedications(){
		return dedications;
	}
	
	public Set<Dedication> getDedications(){
		return new HashSet<Dedication>(dedications);
	}

	public int getMinTrainingHours() {
		return minTrainingHours;
	}

}
