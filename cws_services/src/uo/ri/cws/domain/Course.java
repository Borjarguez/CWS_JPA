package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TCOURSES")
public class Course extends BaseEntity {

	@Column(unique = true)
	private String code;

	private String name;
	private String description;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	private int hours;

	@OneToMany(mappedBy = "course")
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private Set<Dedication> dedications = new HashSet<Dedication>();

	/**
	 * Mapper's constructor
	 */
	Course() {
	}

	/**
	 * Public constructor
	 * 
	 * @param code, the course's code
	 */
	public Course(String code) {
		this.code = code;
	}

	/**
	 * Public constructor
	 * 
	 * @param code,        the course's code
	 * @param name,        the course's name
	 * @param description, the course's description
	 * @param startDate,   the course's start date
	 * @param endDate,     the course's end date
	 * @param hours,       the course's hours
	 */
	public Course(String code, String name, String description, Date startDate, Date endDate, int hours) {
		this(code);
		checkString(code, "code");
		checkString(name, "name");
		checkString(description, "description");
		checkDate(startDate, endDate);
		checkInt(hours);

		this.name = name;
		this.description = description;
		this.startDate = new Date(startDate.getTime());
		this.endDate = new Date(endDate.getTime());
		this.hours = hours;
	}

	/**
	 * Method which returns the code
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
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
	 * Method which returns the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method which returns the start date
	 * 
	 * @return safe return of the start date
	 */
	public Date getStartDate() {
		return new Date(startDate.getTime());
	}

	/**
	 * Method which return the end date
	 * 
	 * @return safe return of the end date
	 */
	public Date getEndDate() {
		return new Date(endDate.getTime());
	}

	/**
	 * Method which returns the hours
	 * 
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Course other = (Course) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [code=" + code + ", name=" + name + ", description=" + description + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", hours=" + hours + "]";
	}

	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// EXTENSION
	/////////////////////////////////////////////////////////////////////////////////////// /////////////////////////////////////////////

	/**
	 * Method which returns the enrollments
	 * 
	 * @return the enrollments
	 */
	Set<Enrollment> _getEnrollments() {
		return enrollments;
	}

	/**
	 * Method which returns the enrollments
	 * 
	 * @return safe return of the enrollments
	 */
	public Set<Enrollment> getEnrollments() {
		return new HashSet<Enrollment>(enrollments);
	}

	/**
	 * Method which returns the dedications
	 * 
	 * @return the dedications
	 */
	Set<Dedication> _getDedications() {
		return dedications;
	}

	/**
	 * Method which returns the dedications
	 * 
	 * @return safe return of the dedications
	 */
	public Set<Dedication> getDedications() {
		return new HashSet<Dedication>(dedications);
	}

	/**
	 * Method which adds the different dedications to the list
	 * 
	 * @param percentages, the percentages
	 */
	public void addDedications(Map<VehicleType, Integer> percentages) {
		int percentage = 0;

		if (getDedications().size() > 0)
			throw new IllegalStateException("Dedications already existed");

		for (Map.Entry<VehicleType, Integer> dedication : percentages.entrySet()) {
			percentage += dedication.getValue();
		}

		if (percentage != 100)
			throw new IllegalArgumentException("Percentage must be between 0 and 100");

		for (Map.Entry<VehicleType, Integer> dedication : percentages.entrySet()) {
			dedications.add(new Dedication(dedication.getKey(), this, dedication.getValue()));
		}

	}

	/**
	 * Method which clears the course's dedications
	 */
	public void clearDedications() {
		for (Dedication d : getDedications()) {
			Associations.Dedicate.unlink(d);
		}
		dedications.clear();
	}

	////////////////////////////////////////////////////
	///// Security checks //////////////////////////////

	/**
	 * Method which checks if the integer values are correct
	 * 
	 * @param num
	 */
	private void checkInt(int num) {
		if (num == 0)
			throw new IllegalArgumentException("Duration can not be zero");

		if (num < 0)
			throw new IllegalArgumentException("Duration can not be negative");
	}

	/**
	 * Method which checks if dates are correct
	 * 
	 * @param startDate, the start date
	 * @param endDate,   the end date
	 */
	private void checkDate(Date startDate, Date endDate) {
		if (startDate == null || endDate == null)
			throw new IllegalArgumentException("Date can not be null");

		if (startDate.after(endDate))
			throw new IllegalArgumentException("Start date can not be after end date");

		if (endDate.before(startDate))
			throw new IllegalArgumentException("End date can not be before start date");

		if (startDate.getTime() == endDate.getTime())
			if (startDate.after(endDate))
				throw new IllegalArgumentException("Dates can not be equal to each other");
	}

	/**
	 * Method which checks if a string is correct
	 * 
	 * @param value, the string value
	 * @param name,  the name of the parameter
	 */
	private void checkString(String value, String name) {
		if (value.equals(""))
			throw new IllegalArgumentException(name + " must not be empty");
	}

	/**
	 * Method which sets the value of the property name
	 * 
	 * @param name, the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method which sets the value of the property description
	 * 
	 * @param description, the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Method which sets the value of the property start date
	 * 
	 * @param startDate, the start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = new Date(startDate.getTime());
	}

	/**
	 * Method which sets the value of the property end date
	 * 
	 * @param endDate, the end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = new Date(endDate.getTime());
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
}
