package uo.ri.cws.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TCERTIFICATES", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "MECHANIC_ID", "VEHICLETYPE_ID" }) })
public class Certificate extends BaseEntity {

	@ManyToOne
	private Mechanic mechanic;

	@ManyToOne
	private VehicleType vehicleType;

	@Temporal(TemporalType.DATE)
	private Date date;

	Certificate() {
	}

	public Certificate(Mechanic mechanic, VehicleType vehicleType) {
		Associations.Certify.link(mechanic, this, vehicleType);
		this.date = new Date();
	}

	/**
	 * Method which returns the mechanic
	 * 
	 * @return the mechanic
	 */
	public Mechanic getMechanic() {
		return mechanic;
	}

	/**
	 * Method which returns the the vehicle type
	 * 
	 * @return the vehicle type
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * Method which returns the certificate's date
	 * 
	 * @return safe return of the date
	 */
	public Date getDate() {
		return new Date(date.getTime());
	}

	/**
	 * Method which sets the mechanic
	 * 
	 * @param mechanic, the new mechanic
	 */
	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	/**
	 * Method which sets the vehicle type
	 * 
	 * @param vehicleType, the new vehicle type
	 */
	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mechanic == null) ? 0 : mechanic.hashCode());
		result = prime * result + ((vehicleType == null) ? 0 : vehicleType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Certificate other = (Certificate) obj;
		if (mechanic == null) {
			if (other.mechanic != null)
				return false;
		} else if (!mechanic.equals(other.mechanic))
			return false;
		if (vehicleType == null) {
			if (other.vehicleType != null)
				return false;
		} else if (!vehicleType.equals(other.vehicleType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Certificate [mechanic=" + mechanic + ", vehicleType=" + vehicleType + ", date=" + date + "]";
	}

}
