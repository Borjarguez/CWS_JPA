package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.cws.domain.Invoice.InvoiceStatus;

@Entity
@Table(name = "TCHARGES", uniqueConstraints = { @UniqueConstraint(columnNames = { "INVOICE_ID", "PAYMENTMEAN_ID" }) })
public class Charge extends BaseEntity {

	@ManyToOne
	private Invoice invoice;
	@ManyToOne
	private PaymentMean paymentMean;

	private double amount;

	Charge() {
	}

	public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
		// store the amount
		// increment the paymentMean accumulated ( paymentMean.pay( -amount) )
		// link invoice, this and paymentMean

		if (paymentMean instanceof Voucher)
			if (((Voucher) paymentMean).getDisponible() < amount)
				throw new IllegalStateException("El medio de pago indicado no tiene suficiente dinero");
		paymentMean.accumulated += amount;

		if (paymentMean instanceof Voucher)
			((Voucher) paymentMean).changeAvailable();
		this.amount = amount;
		Associations.Charges.link(invoice, this, paymentMean);
	}

	/**
	 * Method which sets the invoice
	 * 
	 * @param invoice, the new invoice
	 */
	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	/**
	 * Method which returns the invoice
	 * 
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}

	/**
	 * Method which returns the payment mean
	 * 
	 * @return the payment mean
	 */
	public PaymentMean getPaymentMean() {
		return paymentMean;
	}

	/**
	 * Method which sets the payment mean
	 * 
	 * @param paymentMean, the payment mean
	 */
	void _setPaymentMean(PaymentMean paymentMean) {
		this.paymentMean = paymentMean;
	}

	/**
	 * Unlinks this charge and restores the value to the payment mean
	 * 
	 * @throws IllegalStateException if the invoice is already settled
	 */
	public void rewind() {
		// assert the invoice is not in PAID status
		// decrement the payment mean accumulated ( paymentMean.pay( -amount) )
		// unlink invoice, this and paymentMean
		if (invoice.getStatus().equals(InvoiceStatus.NOT_YET_PAID)) {
			paymentMean.setAccumulated(paymentMean.getAccumulated() - this.amount);
			Associations.Charges.unlink(this);
		}
	}

}
