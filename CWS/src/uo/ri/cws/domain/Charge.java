package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.cws.domain.Invoice.InvoiceStatus;
@Entity
@Table(name="TCHARGES", uniqueConstraints = {
		@UniqueConstraint(columnNames={
			"INVOICE_ID","PAYMENTMEAN_ID"
		})
})
public class Charge extends BaseEntity{
	
	@ManyToOne private Invoice invoice;
	@ManyToOne private PaymentMean paymentMean;
	
	private double amount;
	
	Charge(){}
	
	public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
		// store the amount
		// increment the paymentMean accumulated ( paymentMean.pay( -amount) )
		// link invoice, this and paymentMean
		
		if (paymentMean instanceof Voucher)
			if (((Voucher) paymentMean).getAvailable() < amount)
				throw new IllegalStateException("El medio de pago indicado no tiene suficiente dinero");
		// incrementar el importe en el acumulado del medio de pago
		// guardar el importe
		// enlazar (link) factura, este cargo y medioDePago
		paymentMean.accumulated += amount;
		
		if (paymentMean instanceof Voucher)
			((Voucher) paymentMean).changeAvailable();
		this.amount = amount;
		Associations.Charges.link(invoice, this, paymentMean);
	}
	

	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}
	
	public PaymentMean getPaymentMean() {
		return paymentMean;
	}

	void _setPaymentMean(PaymentMean paymentMean) {
		this.paymentMean = paymentMean;
	}



	/**
	 * Unlinks this charge and restores the value to the payment mean
	 * @throws IllegalStateException if the invoice is already settled
	 */
	public void rewind() {
		// assert the invoice is not in PAID status
		// decrement the payment mean accumulated ( paymentMean.pay( -amount) )
		// unlink invoice, this and paymentMean
		if(invoice.getStatus().equals(InvoiceStatus.NOT_YET_PAID)) {
			paymentMean.setAccumulated(paymentMean.getAccumulated() - this.amount);
			Associations.Charges.unlink(this);
		}
	}
	
}
