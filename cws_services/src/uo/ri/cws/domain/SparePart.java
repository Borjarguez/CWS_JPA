package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TSPAREPARTS")
public class SparePart extends BaseEntity {
	@Column(unique = true)
	private String code;
	private String description;
	private double price;

	@OneToMany(mappedBy = "sparePart")
	protected Set<Substitution> substitutions = new HashSet<Substitution>();

	/**
	 * Mapper's constructor
	 */
	SparePart() {
	}

	/**
	 * Public constructor
	 * 
	 * @param code, the code
	 */
	public SparePart(String code) {
		super();
		this.code = code;
	}

	/**
	 * Public constructor
	 * 
	 * @param code,        the code
	 * @param description, the description
	 * @param price,       the price
	 */
	public SparePart(String code, String description, double price) {
		this(code);
		this.description = description;
		this.price = price;
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
	 * Method which returns the description
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method which returns the price
	 * 
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Method which returns the substitutions
	 * 
	 * @return the substitutions
	 */
	Set<Substitution> _getSustituciones() {
		return substitutions;
	}

	/**
	 * Method which returns the substitutions
	 * 
	 * @return safe return of the substitutions
	 */
	public Set<Substitution> getSustituciones() {
		return new HashSet<Substitution>(substitutions);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		SparePart other = (SparePart) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SparePart [code=" + code + ", description=" + description + ", price=" + price + "]";
	}

}
