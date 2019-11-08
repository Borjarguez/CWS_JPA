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

	Dedication() {
	}

	Dedication(Course course, VehicleType vehicleType) {
		Associations.Dedicate.link(course, this, vehicleType);
	}

	Dedication(VehicleType vehicleType, Course course, Integer percentage) {
		this(course, vehicleType);
		this.percentage = percentage;
	}

	void _setCourse(Course course) {
		this.course = course;
	}

	void _setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Course getCourse() {
		return course;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

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
