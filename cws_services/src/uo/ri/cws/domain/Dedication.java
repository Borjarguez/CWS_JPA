package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TDEDICATIONS", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "COURSE_ID", "VEHICLETYPE_ID" }) })
public class Dedication extends BaseEntity {

	@ManyToOne
	private Course course;

	@ManyToOne
	private VehicleType vehicleType;

	private int percentage;

	/**
	 * Mapper's constructor
	 */
	Dedication() {
	}

	/**
	 * Protected constructor
	 * 
	 * @param course,      the course
	 * @param vehicleType, the vehicle type
	 */
	Dedication(Course course, VehicleType vehicleType) {
		Associations.Dedicate.link(course, this, vehicleType);
	}

	/**
	 * Protected constructor
	 * 
	 * @param vehicleType, the vehicle type
	 * @param course,      the course
	 * @param percentage,  the percentage
	 */
	Dedication(VehicleType vehicleType, Course course, Integer percentage) {
		this(course, vehicleType);
		this.percentage = percentage;
	}

	/**
	 * Method which sets the course
	 * 
	 * @param course, the new course
	 */
	void _setCourse(Course course) {
		this.course = course;
	}

	/**
	 * Method which sets the vehicle type
	 * 
	 * @param vehicleType, the vehicle type
	 */
	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * Method which returns the course
	 * 
	 * @return the course
	 */
	public Course getCourse() {
		return course;
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
	 * Method which returns the percentage
	 * 
	 * @return the percentage
	 */
	public int getPercentage() {
		return percentage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((course == null) ? 0 : course.hashCode());
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
		Dedication other = (Dedication) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
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
		return "Dedication [course=" + course + ", vehicleType=" + vehicleType + ", percetage=" + percentage + "]";
	}

}
