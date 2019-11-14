package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TSUBSTITUTIONS", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "SPAREPART_ID", "INTERVENTION_ID" }) })
public class Substitution extends BaseEntity {
	@ManyToOne
	private SparePart sparePart;
	@ManyToOne
	private Intervention intervention;
	private int quantity;

	/**
	 * Mapper's constructor
	 */
	Substitution() {
	}

	/**
	 * Public constructor
	 * 
	 * @param sparePart,    the spare part
	 * @param intervention, the intervention
	 * @param quantity,     the quantity
	 */
	public Substitution(SparePart sparePart, Intervention intervention, int quantity) {
		checkQuantity(quantity);
		Associations.Sustitute.link(sparePart, this, intervention);
		this.quantity = quantity;
	}

	/**
	 * Method which checks the quantity value
	 * 
	 * @param quantity, the quantity
	 */
	private void checkQuantity(int quantity) {
		if (quantity < 0)
			throw new IllegalArgumentException("Quantity must be positive");

		if (quantity == 0)
			throw new IllegalArgumentException("Quantity must be over 0");
	}

	/**
	 * Method which returns the spare part
	 * 
	 * @return the spare part
	 */
	public SparePart getSparePart() {
		return sparePart;
	}

	/**
	 * Method which returns the intervention
	 * 
	 * @return the intervention
	 */
	public Intervention getIntervention() {
		return intervention;
	}

	/**
	 * Method which returns the quantity
	 * 
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Method which sets the spare part
	 * 
	 * @param sparePart, the new spare part
	 */
	void _setSparePart(SparePart sparePart) {
		this.sparePart = sparePart;
	}

	/**
	 * Method which sets the intervention
	 * 
	 * @param intervention, the new intervention
	 */
	void _setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((intervention == null) ? 0 : intervention.hashCode());
		result = prime * result + ((sparePart == null) ? 0 : sparePart.hashCode());
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
		Substitution other = (Substitution) obj;
		if (intervention == null) {
			if (other.intervention != null)
				return false;
		} else if (!intervention.equals(other.intervention))
			return false;
		if (sparePart == null) {
			if (other.sparePart != null)
				return false;
		} else if (!sparePart.equals(other.sparePart))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Substitution [sparePart=" + sparePart + ", intervention=" + intervention + ", quantity=" + quantity
				+ "]";
	}

	/**
	 * Method which calculates the import
	 * 
	 * @return the import
	 */
	public double getImporte() {
		return quantity * sparePart.getPrice();
	}

}
