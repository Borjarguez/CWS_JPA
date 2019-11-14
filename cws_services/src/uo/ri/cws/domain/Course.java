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

	@OneToMany(mappedBy = "course", cascade=CascadeType.ALL)
	private Set<Dedication> dedications = new HashSet<Dedication>();

	Course() {
	}

	public Course(String code) {
		this.code = code;
	}

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

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartDate() {
		return new Date(startDate.getTime());
	}

	public Date getEndDate() {
		return new Date(endDate.getTime());
	}

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

	Set<Enrollment> _getEnrollments() {
		return enrollments;
	}

	public Set<Enrollment> getEnrollments() {
		return new HashSet<Enrollment>(enrollments);
	}

	Set<Dedication> _getDedications() {
		return dedications;
	}

	public Set<Dedication> getDedications() {
		return new HashSet<Dedication>(dedications);
	}

	/**
	 * Method which adds the different dedications to the list
	 * 
	 * @param percentages
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

	public void clearDedications() {
		for (Dedication d : getDedications()) {
			Associations.Dedicate.unlink(d);
		}
		dedications.clear();
	}

	////////////////////////////////////////////////////
	///// Security checks //////////////////////////////

	private void checkInt(int num) {
		if (num == 0)
			throw new IllegalArgumentException("Duration can not be zero");

		if (num < 0)
			throw new IllegalArgumentException("Duration can not be negative");
	}

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

	private void checkString(String value, String name) {
		if (value.equals(""))
			throw new IllegalArgumentException(name + " must not be empty");
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStartDate(Date startDate) {
		this.startDate = new Date(startDate.getTime());
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = new Date(endDate.getTime());
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
}
