package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.date.Dates;
import alb.util.math.Round;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

@Entity
@Table(name = "TINVOICES")
public class Invoice extends BaseEntity {
	public enum InvoiceStatus {
		NOT_YET_PAID, PAID
	}

	@Column(unique = true)
	private Long number;

	@Temporal(TemporalType.DATE)
	private Date date;
	private double amount;
	private double vat;
	@Enumerated(EnumType.STRING)
	private InvoiceStatus status = InvoiceStatus.NOT_YET_PAID;

	@OneToMany(mappedBy = "invoice")
	private Set<WorkOrder> workOrders = new HashSet<WorkOrder>();

	@OneToMany(mappedBy = "invoice")
	private Set<Charge> charges = new HashSet<>();

	/**
	 * Mapper's constructor
	 */
	Invoice() {
	}

	/**
	 * Public constructor
	 * 
	 * @param number, the number
	 */
	public Invoice(Long number) {
		setNumber(number);
		setDate(new Date());
	}

	/**
	 * Public constructor
	 * 
	 * @param number, the number
	 * @param date,   the date
	 */
	public Invoice(Long number, Date date) {
		// check arguments (always), through IllegalArgumentException
		// store the number
		// store a copy of the date
		this(number);
		setDate(date);
	}

	/**
	 * Public constructor
	 * 
	 * @param number,     the number
	 * @param workOrders, the work orders
	 */
	public Invoice(Long number, List<WorkOrder> workOrders) {
		this(number);
		for (WorkOrder workOrder : workOrders)
			this.addWorkOrder(workOrder);
	}

	/**
	 * Public constructor
	 * 
	 * @param number,     the number
	 * @param date,       the date
	 * @param workOrders, the work orders
	 */
	public Invoice(Long number, Date date, List<WorkOrder> workOrders) {
		this(number, workOrders);
		this.setDate(date);
	}

	/**
	 * Computed amount and vat (vat depends on the date)
	 */
	private void computeAmount() {
		this.vat = Dates.fromString("1/7/2012").before(date) ? 21.0 : 18.0;
		this.amount = 0L;

		for (WorkOrder workOrder : workOrders)
			this.amount += workOrder.getAmount();

		this.amount *= 1L + vat / 100L;
		this.amount = Round.twoCents(amount);
	}

	/**
	 * Adds (double links) the workOrder to the invoice and updates the amount and
	 * vat
	 * 
	 * @param workOrder, the work order
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
	 */
	public void addWorkOrder(WorkOrder workOrder) {
		// Checks the invoice is not already PAID
		if (getStatus().equals(InvoiceStatus.PAID))
			throw new IllegalStateException("Invoice must not be paid");
		// Checks the workOrder is not FINISHED
		if (!workOrder.getStatus().equals(WorkOrderStatus.FINISHED))
			throw new IllegalStateException("WorkOrder must not be finished");

		// Links invoice and workOrder
		Associations.ToInvoice.link(this, workOrder);
		// Makes the workOrder as INVOICED
		workOrder.markAsInvoiced();
		// Computes the price
		computeAmount();
	}

	/**
	 * Removes a work order from the invoice and recomputes amount and vat
	 * 
	 * @param workOrder, the work order
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
	 */
	public void removeWorkOrder(WorkOrder workOrder) {
		if (getStatus().equals(InvoiceStatus.NOT_YET_PAID)) {
			Associations.ToInvoice.unlink(this, workOrder);
			workOrder.markBackToFinished();
			computeAmount();
		} else {
			throw new IllegalStateException("Invoice is already paid");
		}
	}

	/**
	 * Marks the invoice as PAID, but
	 * 
	 * @throws IllegalStateException if - Is already settled - Or the amounts paid
	 *                               with charges to payment means do not cover the
	 *                               total of the invoice
	 */
	public void settle() {
		if (InvoiceStatus.PAID.equals(status)) {
			throw new IllegalStateException("Invoiced is already paid");
		}

		this.status = InvoiceStatus.PAID;
	}

	/**
	 * Method which returns the number
	 * 
	 * @return the number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * Method which returns the date
	 * 
	 * @return the date
	 */
	public Date getDate() {
		return new Date(date.getTime());
	}

	/**
	 * Method which returns the amount
	 * 
	 * @return the amount
	 */
	public double getAmount() {
		calculateAmount();
		return amount;
	}

	/**
	 * Method which calculates the amount
	 */
	private void calculateAmount() {
		this.vat = Dates.fromString("1/7/2012").before(date) ? 21.0 : 18.0;
		this.amount = 0L;

		for (WorkOrder averia : workOrders)
			this.amount += averia.getAmount();

		this.amount *= 1L + vat / 100L;
		this.amount = Round.twoCents(amount);
	}

	/**
	 * Method which returns the vat
	 * 
	 * @return the vat
	 */
	public double getVat() {
		return vat;
	}

	/**
	 * Method which returns the status
	 * 
	 * @return the status
	 */
	public InvoiceStatus getStatus() {
		return status;
	}

	/**
	 * Method which returns the work orders
	 * 
	 * @return the work orders
	 */
	Set<WorkOrder> _getWorkOrders() {
		return workOrders;
	}

	/**
	 * Method which returns the work orders
	 * 
	 * @return safe return of the work orders
	 */
	public Set<WorkOrder> getWorkOrders() {
		return new HashSet<WorkOrder>(workOrders);
	}

	/**
	 * Method which returns the charges
	 * 
	 * @return the charges
	 */
	Set<Charge> _getCharges() {
		return charges;
	}

	/**
	 * Method which returns the charges
	 * 
	 * @return safe return of the charges
	 */
	public Set<Charge> getCharges() {
		return new HashSet<Charge>(charges);
	}

	/**
	 * Method which sets the date
	 * 
	 * @param date, the new date
	 */
	public void setDate(Date date) {
		this.date = new Date(date.getTime());
	}

	/**
	 * Method which sets the number
	 * 
	 * @param num, the new number
	 */
	private void setNumber(long num) {
		if (num < 0)
			throw new IllegalArgumentException("Number must be positive");
		this.number = num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Invoice other = (Invoice) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Invoice [number=" + number + ", date=" + date + ", amount=" + amount + ", vat=" + vat + ", status="
				+ status + "]";
	}

}
