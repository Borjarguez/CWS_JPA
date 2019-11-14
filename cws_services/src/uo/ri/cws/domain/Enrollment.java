package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TEnrollments", uniqueConstraints = { @UniqueConstraint(columnNames = { "MECHANIC_ID", "COURSE_ID" }) })
public class Enrollment extends BaseEntity {

	@ManyToOne
	private Mechanic mechanic;

	@ManyToOne
	private Course course;

	private int attendance;
	private boolean passed;

	/**
	 * Mapper's constructor
	 */
	Enrollment() {
	}

	/**
	 * Public constructor
	 * 
	 * @param mechanic, the mechanic
	 * @param course,   the course
	 */
	public Enrollment(Mechanic mechanic, Course course) {
		Associations.Enroll.link(mechanic, this, course);
	}

	/**
	 * Public constructor
	 * 
	 * @param mechanic,   the mechanic
	 * @param course,     the course
	 * @param attendance, the attendance
	 * @param passed,     the passed value
	 */
	public Enrollment(Mechanic mechanic, Course course, int attendance, boolean passed) {
		this(mechanic, course);

		if (passed && attendance < 85)
			throw new IllegalArgumentException("To pass attendance must be over 85 percent");

		this.attendance = attendance;
		this.passed = passed;
	}

	/**
	 * Method which sets the new mechanic
	 * 
	 * @param mechanic the new mechanic
	 */
	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	/**
	 * Method which sets the course value
	 * 
	 * @param course, the new course
	 */
	void _setCourse(Course course) {
		this.course = course;
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
	 * Method which returns the course
	 * 
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Method which returns the attendance
	 * 
	 * @return the attendance
	 */
	public int getAttendance() {
		return attendance;
	}

	/**
	 * Method which returns the passed value
	 * 
	 * @return the passed value
	 */
	public boolean isPassed() {
		return passed;
	}

	/**
	 * Method which calculates the attended hours
	 * 
	 * @param car, the vehicle type
	 * @return the attended hours
	 */
	public int getAttendedHoursFor(VehicleType car) {
		int hours = 0;

		for (Dedication d : course.getDedications()) {
			if (d.getVehicleType().equals(car))
				hours += d.getPercentage() * course.getHours() * 0.01 * attendance * 0.01;
		}

		return hours;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((mechanic == null) ? 0 : mechanic.hashCode());
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
		Enrollment other = (Enrollment) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (mechanic == null) {
			if (other.mechanic != null)
				return false;
		} else if (!mechanic.equals(other.mechanic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Enrollment [mechanic=" + mechanic + ", course=" + course + ", attendance=" + attendance + ", passed="
				+ passed + "]";
	}

}
