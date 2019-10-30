package uo.ri.cws.domain;

public class Associations {

	public static class Own {

		public static void link(Client client, Vehicle vehicle) {
			vehicle._setClient(client);
			client._getVehicles().add(vehicle);
		}

		public static void unlink(Client client, Vehicle vehicle) {
			client._getVehicles().remove(vehicle);
			vehicle._setClient(null);
		}

	}

	public static class Classify {

		public static void link(VehicleType vehicleType, Vehicle vehicle) {
			vehicle._setType(vehicleType);
			vehicleType._getVehicles().add(vehicle);
		}

		public static void unlink(VehicleType vehicleType, Vehicle vehicle) {
			vehicleType._getVehicles().remove(vehicle);
			vehicle._setType(null);
		}

	}

	public static class Pay {

		public static void link(PaymentMean paymentMean, Client client) {
			paymentMean._setClient(client);
			client._getPaymentMeans().add(paymentMean);
		}
		
		public static void unlink(PaymentMean paymentMean, Client client) {
			client._getPaymentMeans().remove(paymentMean);
			paymentMean._setClient(null);
		}

	}

	public static class Order {

		public static void link(Vehicle vehicle, WorkOrder workOrder) {
			workOrder._setVehicle(vehicle);
			vehicle._getWorkOrders().add(workOrder);
		}

		public static void unlink(Vehicle vehicle, WorkOrder workOrder) {
			vehicle._getWorkOrders().remove(workOrder);
			workOrder._setVehicle(null);
		}

	}

	public static class ToInvoice {

		public static void link(Invoice invoice, WorkOrder workOrder) {
			workOrder._setInvoice(invoice);
			invoice._getWorkOrders().add(workOrder);
		}

		public static void unlink(Invoice invoice, WorkOrder workOrder) {
			invoice._getWorkOrders().remove(workOrder);
			workOrder._setInvoice(null);

		}
	}

	public static class Charges {
		
		public static void link(Invoice invoice, Charge charge, PaymentMean paymentMean) {
			charge._setInvoice(invoice);
			charge._setPaymentMean(paymentMean);
			
			paymentMean._getCharges().add(charge);
			invoice._getCharges().add(charge);
		}

		public static void unlink(Charge charge) {
			Invoice invoice = charge.getInvoice();
			PaymentMean paymentMean = charge.getPaymentMean();
			
			paymentMean._getCharges().remove(charge);
			invoice._getCharges().remove(charge);
			
			charge._setInvoice(null);
			charge._setPaymentMean(null);
			
		}

	}

	public static class Assign {

		public static void link(Mechanic mechanic, WorkOrder workOrder) {
			workOrder._setMechanic(mechanic);
			mechanic._getAssigned().add(workOrder);
		}

		public static void unlink(Mechanic mechanic, WorkOrder workOrder) {
			mechanic._getAssigned().remove(workOrder);
			workOrder._setMechanic(null);

		}
	}

	public static class Intervene {

		public static void link(WorkOrder workOrder, Intervention intervention, Mechanic mechanic) {
			intervention._setWorkOrder(workOrder);
			intervention._setMechanic(mechanic);

			mechanic._getInterventions().add(intervention);
			workOrder._getInterventions().add(intervention);
		}

		public static void unlink(Intervention intervention) {
			WorkOrder workOrder = intervention.getWorkOrder();
			Mechanic mechanic = intervention.getMechanic();
			
			workOrder._getInterventions().remove(intervention);
			mechanic._getInterventions().remove(intervention);

			intervention._setWorkOrder(null);
			intervention._setMechanic(null);
		}

	}

	public static class Sustitute {

		public static void link(SparePart sparePart, Substitution substitution, Intervention intervention) {
			substitution._setIntervention(intervention);
			substitution._setSparePart(sparePart);

			intervention._getSubstitutions().add(substitution);
			sparePart._getSubstitutions().add(substitution);
		}
		
		public static void unlink(Substitution substitution) {
			SparePart sparePart = substitution.getSparePart();
			Intervention intervention = substitution.getIntervention();

			intervention._getSubstitutions().remove(substitution);
			sparePart._getSubstitutions().remove(substitution);
			
			substitution._setIntervention(null);
			substitution._setSparePart(null);
		}

	}

}
