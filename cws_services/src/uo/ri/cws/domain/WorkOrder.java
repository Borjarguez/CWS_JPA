package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import alb.util.assertion.StateCheck;

@Entity
@Table(name = "TWORKORDERS", uniqueConstraints = { @UniqueConstraint(columnNames = { "DATE", "VEHICLE_ID" }) })
public class WorkOrder extends BaseEntity {

	public enum WorkOrderStatus {
		OPEN, ASSIGNED, FINISHED, INVOICED
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String description;
	private double amount = 0.0;
	@Enumerated(EnumType.STRING)
	private WorkOrderStatus status = WorkOrderStatus.OPEN;

	@ManyToOne
	private Vehicle vehicle;
	@ManyToOne
	private Mechanic mechanic;
	@ManyToOne
	private Invoice invoice;

	@OneToMany(mappedBy = "workOrder")
	private Set<Intervention> interventions = new HashSet<Intervention>();

	/**
	 * Mapper's constructor
	 */
	WorkOrder() {
	}

	/**
	 * Public constructor
	 * 
	 * @param vehicle, the vehicle to link
	 */
	public WorkOrder(Vehicle vehicle) {
		setDate(new Date());
		setStatus(WorkOrderStatus.OPEN);
		Associations.Order.link(vehicle, this);
	}

	/**
	 * Public constructor
	 * 
	 * @param vehicle,     the vehicle to link
	 * @param description, the work order description
	 */
	public WorkOrder(Vehicle vehicle, String description) {
		this(vehicle);
		setDescription(description);
	}

	/**
	 * Method which returns the work order's date
	 * 
	 * @return safe return of the date
	 */
	public Date getDate() {
		return new Date(date.getTime());
	}

	/**
	 * Method which sets the work order's date
	 * 
	 * @param date, the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Method which returns the work order's associated vehicle
	 * 
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * Method which modifies the vehicle property
	 * 
	 * @param vehicle, the new vehicle
	 */
	void _setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * Method which returns the work order's associated mechanic
	 * 
	 * @return the mechanic
	 */
	public Mechanic getMechanic() {
		return mechanic;
	}

	/**
	 * Method which modifies the mechanic property
	 * 
	 * @param mechanic, the new mechanic
	 */
	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	/**
	 * Method which returns the work order's description
	 * 
	 * @return the description's value
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method which returns the amount of the work order
	 * 
	 * @return the amount's value
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Method which returns the work order's status
	 * 
	 * @return the status' value
	 */
	public WorkOrderStatus getStatus() {
		return status;
	}

	/**
	 * Method which modifies the work order's status
	 * 
	 * @param status, the new status
	 */
	private void setStatus(WorkOrderStatus status) {
		this.status = status;
	}

	/**
	 * Method which returns the associated invoice
	 * 
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * Method which modifies the invoice property
	 * 
	 * @param invoice, the new invoice
	 */
	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * Method which returns the work order's interventions
	 * 
	 * @return the interventions
	 */
	Set<Intervention> _getInterventions() {
		return interventions;
	}

	/**
	 * Method which returns the work order's interventions
	 * 
	 * @return safe return of the interventions
	 */
	public Set<Intervention> getInterventions() {
		return new HashSet<Intervention>(interventions);
	}

	/**
	 * Method which modifies the description
	 * 
	 * @param description, the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((vehicle == null) ? 0 : vehicle.hashCode());
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
		WorkOrder other = (WorkOrder) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (vehicle == null) {
			if (other.vehicle != null)
				return false;
		} else if (!vehicle.equals(other.vehicle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WorkOrder [date=" + date + ", description=" + description + ", amount=" + amount + ", status=" + status
				+ ", vehicle=" + vehicle + "]";
	}

	/**
	 * Changes it to INVOICED state given the right conditions This method is called
	 * from Invoice.addWorkOrder(...)
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not FINISHED, or - The
	 *                               work order is not linked with the invoice
	 */
	public void markAsInvoiced() {
		StateCheck.isTrue(getStatus().equals(WorkOrderStatus.FINISHED), "Work order must not be finished");
		StateCheck.isNotNull(getInvoice());
		setStatus(WorkOrderStatus.INVOICED);
	}

	/**
	 * Changes it to FINISHED state given the right conditions and computes the
	 * amount
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in ASSIGNED state,
	 *                               or - The work order is not linked with a
	 *                               mechanic
	 */
	public void markAsFinished() {
		StateCheck.isTrue(getStatus().equals(WorkOrderStatus.ASSIGNED));
		StateCheck.isNotNull(mechanic);
		this.amount = calculatePrice();
		Associations.Assign.unlink(mechanic, this);
		setStatus(WorkOrderStatus.FINISHED);
	}

	/**
	 * Method which calculates the price of the work order
	 * 
	 * @return the work order's amount
	 */
	private double calculatePrice() {
		double price = 0L;
		for (Intervention intervention : interventions)
			price += intervention.getAmount();
		return price;
	}

	/**
	 * Changes it back to FINISHED state given the right conditions This method is
	 * called from Invoice.removeWorkOrder(...)
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not INVOICED, or - The
	 *                               work order is still linked with the invoice
	 */
	public void markBackToFinished() {
		StateCheck.isNull(this.getInvoice());
		StateCheck.isTrue(getStatus().equals(WorkOrderStatus.INVOICED));
		setStatus(WorkOrderStatus.FINISHED);
	}

	/**
	 * Links (assigns) the work order to a mechanic and then changes its status to
	 * ASSIGNED
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in OPEN status, or -
	 *                               The work order is already linked with another
	 *                               mechanic
	 */
	public void assignTo(Mechanic mechanic) {
		StateCheck.isTrue(WorkOrderStatus.OPEN.equals(status));
		Associations.Assign.link(mechanic, this);
		setStatus(WorkOrderStatus.ASSIGNED);
	}

	/**
	 * Unlinks (deassigns) the work order and the mechanic and then changes its
	 * status back to OPEN
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in ASSIGNED status
	 */
	public void desassign() {
		StateCheck.isTrue(WorkOrderStatus.ASSIGNED.equals(status));
		Associations.Assign.unlink(mechanic, this);
		setStatus(WorkOrderStatus.OPEN);
	}

	/**
	 * In order to assign a work order to another mechanic is first have to be moved
	 * back to OPEN state and unlinked from the previous mechanic.
	 * 
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if - The work order is not in FINISHED status
	 */
	public void reopen() {
		StateCheck.isTrue(WorkOrderStatus.FINISHED.equals(status));
		setStatus(WorkOrderStatus.OPEN);
	}

}
