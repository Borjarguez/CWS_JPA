package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TMECHANICS")
public class Mechanic extends BaseEntity {

	@Column(unique = true)
	private String dni;

	private String surname;
	private String name;

	@Transient
	private Set<WorkOrder> workOrders = new HashSet<WorkOrder>();

	@OneToMany(mappedBy = "mechanic")
	private Set<Intervention> interventions = new HashSet<Intervention>();

	@OneToMany(mappedBy = "mechanic")
	private Set<Certificate> certificates = new HashSet<Certificate>();

	@OneToMany(mappedBy = "mechanic")
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();

	/**
	 * Mapper's constructor
	 */
	Mechanic() {
	}

	/**
	 * Public constructor
	 * 
	 * @param dni, the dni
	 */
	public Mechanic(String dni) {
		super();
		this.dni = dni;
	}

	/**
	 * Public constructor
	 * 
	 * @param dni,     the dni
	 * @param name,    the name
	 * @param surname, the surname
	 */
	public Mechanic(String dni, String name, String surname) {
		this(dni);
		this.name = name;
		this.surname = surname;
	}

	/**
	 * Method which returns the dni
	 * 
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Method which returns the surname
	 * 
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Method which returns the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method which returns the work orders
	 * 
	 * @return the work orders
	 */
	Set<WorkOrder> _getAssigned() {
		return workOrders;
	}

	/**
	 * Method which returns the work orders
	 * 
	 * @return safe return of the work orders
	 */
	public Set<WorkOrder> getAssigned() {
		return new HashSet<WorkOrder>(workOrders);
	}

	/**
	 * MEthod which returns the work orders
	 * 
	 * @return the work orders
	 */
	Set<Intervention> _getInterventions() {
		return interventions;
	}

	/**
	 * Method which returns the interventions
	 * 
	 * @return safe return of the interventions
	 */
	public Set<Intervention> getInterventions() {
		return new HashSet<Intervention>(interventions);
	}

	/**
	 * Method which sets the surname value
	 * 
	 * @param surname, the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Method which sets the name
	 * 
	 * @param name, the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Mechanic other = (Mechanic) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mechanic [dni=" + dni + ", surname=" + surname + ", name=" + name + "]";
	}

	////////////////////////// EXTENSION //////////////////////////

	Set<Certificate> _getCertificates() {
		return certificates;
	}

	public Set<Certificate> getCertificates() {
		return new HashSet<Certificate>(certificates);
	}

	Set<Enrollment> _getEnrollments() {
		return enrollments;
	}

	public Set<Enrollment> getEnrollments() {
		return new HashSet<Enrollment>(enrollments);
	}

	public Set<Enrollment> getEnrollmentsFor(VehicleType truck) {
		Set<Enrollment> enrollments = new HashSet<Enrollment>();
		for (Enrollment enrollment : getEnrollments()) {
			Course enroll_course = enrollment.getCourse();

			for (Dedication dedication : enroll_course.getDedications()) {
				if (dedication.getVehicleType().equals(truck))
					enrollments.add(enrollment);
			}
		}

		return enrollments;
	}

	public boolean isCertifiedFor(VehicleType type) {
		for (Certificate c : getCertificates()) {
			if (c.getVehicleType().equals(type)) {
				return true;
			}
		}
		return false;
	}

}
