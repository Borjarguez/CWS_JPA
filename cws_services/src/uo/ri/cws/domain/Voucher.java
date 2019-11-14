package uo.ri.cws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TVOUCHERS")
public class Voucher extends PaymentMean {
	@Column(unique = true)
	private String code;
	private double available;
	private String description;

	/**
	 * Augments the accumulated and decrements the available
	 * 
	 * @throws IllegalStateException if not enough available to pay
	 */
	@Override
	public void pay(double amount) {
		this.available -= amount;
	}

	/**
	 * Mapper's constructor
	 */
	Voucher() {
	}

	/**
	 * Public constructor
	 * 
	 * @param code, the code
	 */
	public Voucher(String code) {
		this.code = code;
	}

	/**
	 * Public constructor
	 * 
	 * @param code,      the code
	 * @param available, the available
	 */
	public Voucher(String code, double available) {
		this(code);
		this.available = available;
	}

	/**
	 * Public constructor
	 * 
	 * @param code,        the code
	 * @param available,   the available
	 * @param description, the description
	 */
	public Voucher(String code, double available, String description) {
		this(code, available);
		this.description = description;
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
	 * Method which returns the available
	 * 
	 * @return the available
	 */
	public double getDisponible() {
		return available;
	}

	/**
	 * Method which returns the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
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
		Voucher other = (Voucher) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Voucher [code=" + code + ", available=" + available + ", description=" + description + "]";
	}

	/**
	 * Method which sets the available
	 * 
	 * @param available, the new available
	 */
	public void setAvailable(double available) {
		this.available = available;
	}

	/**
	 * Method which sets the description
	 * 
	 * @param description, the new description
	 */
	public void setDescripcion(String description) {
		this.description = description;
	}

	/**
	 * Method which changes the available
	 */
	public void changeAvailable() {
		this.available = available - getAccumulated();
	}

}
