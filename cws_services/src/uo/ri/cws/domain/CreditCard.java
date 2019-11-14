package uo.ri.cws.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import alb.util.date.Dates;

@Entity
@Table(name = "TCREDITCARDS")
public class CreditCard extends PaymentMean {
	@Column(unique = true)
	private String number;
	private String type;
	private Date validThru;

	/**
	 * Mapper's constructor
	 */
	CreditCard() {
	}

	/**
	 * Public constructor
	 * 
	 * @param number, the credit card number
	 */
	public CreditCard(String number) {
		super();
		this.number = number;
	}

	/**
	 * Public constructor
	 * 
	 * @param number,   the number
	 * @param type,     the type
	 * @param tomorrow, the date
	 */
	public CreditCard(String number, String type, Date tomorrow) {
		this(number);
		checkValidThru(tomorrow);
		this.type = type;
		this.validThru = new Date(tomorrow.getTime());
	}

	/**
	 * Method which checks if the validity date is correct
	 * 
	 * @param validity, the date
	 */
	private void checkValidThru(Date validity) {
		if (validity.getTime() < Dates.today().getTime()) {
			throw new IllegalStateException();
		}
	}

	/**
	 * Method which returns the number
	 * 
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Method which returns the type
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Method which returns the validity date
	 * 
	 * @return the validity date
	 */
	public Date getValidThru() {
		return validThru;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		CreditCard other = (CreditCard) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CreditCard [number=" + number + ", type=" + type + ", validThru=" + validThru + "]";
	}

}
