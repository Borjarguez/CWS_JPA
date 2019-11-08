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

	Enrollment() {
	}

	public Enrollment(Mechanic mechanic, Course course) {
		Associations.Enroll.link(mechanic, this, course);
	}

	public Enrollment(Mechanic mechanic, Course course, int attendance, boolean passed) {
		this(mechanic, course);
		
		if(passed && attendance < 85)
			throw new IllegalArgumentException("To pass attendance must be over 85 percent");
		
		this.attendance = attendance;
		this.passed = passed;
	}
	
	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	void _setCourse(Course course) {
		this.course = course;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public Course getCourse() {
		return course;
	}

	public int getAttendance() {
		return attendance;
	}

	public boolean isPassed() {
		return passed;
	}

	/**
	 * Method which calculates 
	 * @param car
	 * @return
	 */
	public int getAttendedHoursFor(VehicleType car) {
		int hours = 0;
		
		for(Dedication d : course.getDedications()) {
			if(d.getVehicleType().equals(car))
				hours += d.getPercentage()*course.getHours()*0.01*attendance*0.01;
		}
		
		return hours;
	}

}
