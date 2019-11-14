package uo.ri.cws.domain;

public class Associations {

	/**
	 * Class which links and unlinks vehicles and clients
	 * 
	 * @author borja
	 *
	 */
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

	/**
	 * Class which links and unlinks vehicle types and vehicles
	 * 
	 * @author borja
	 *
	 */
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

	/**
	 * Class which links and unlinks payment means and clients
	 * 
	 * @author borja
	 *
	 */
	public static class Pay {

		public static void link(PaymentMean paymentMean, Client client) {
			paymentMean._setClient(client);
			client._getPaymentMeans().add(paymentMean);
		}

		public static void unlink(PaymentMean paymentMean, Client client) {
			client._getPaymentMeans().remove(paymentMean);
			paymentMean._setClient(null);
		}

		public static void unlink(Client client, Cash cash) {
			unlink(cash, client);
		}

	}

	/**
	 * Class which links and unlinks vehicles and work orders
	 * 
	 * @author borja
	 *
	 */
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

	/**
	 * Class which links and unlinks invoices and work orders
	 * 
	 * @author borja
	 *
	 */
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

	/**
	 * Class which links and unlinks invoices, charges and payment means
	 * 
	 * @author borja
	 *
	 */
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

	/**
	 * Class which links and unlinks mechanics and work orders
	 * 
	 * @author borja
	 *
	 */
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

	/**
	 * Class which links and unlinks work orders, interventions and mechanics
	 * 
	 * @author borja
	 *
	 */
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

	/**
	 * Class which links and unlinks spare parts, substitutions and interventions
	 * 
	 * @author borja
	 *
	 */
	public static class Sustitute {

		public static void link(SparePart sparePart, Substitution substitution, Intervention intervention) {
			substitution._setIntervention(intervention);
			substitution._setSparePart(sparePart);

			intervention._getSustitutions().add(substitution);
			sparePart._getSustituciones().add(substitution);
		}

		public static void unlink(Substitution substitution) {
			SparePart sparePart = substitution.getSparePart();
			Intervention intervention = substitution.getIntervention();

			intervention._getSustitutions().remove(substitution);
			sparePart._getSustituciones().remove(substitution);

			substitution._setIntervention(null);
			substitution._setSparePart(null);
		}

	}

	////////////// EXTENSION /////////////////

	/**
	 * Class which links and unlinks mechanics, certificates and vehicle types
	 * 
	 * @author borja
	 *
	 */
	public static class Certify {

		public static void link(Mechanic mechanic, Certificate certificate, VehicleType vehicleType) {
			certificate._setMechanic(mechanic);
			certificate._setVehicleType(vehicleType);

			mechanic._getCertificates().add(certificate);
			vehicleType._getCertificates().add(certificate);
		}

		public static void unlink(Certificate certificate) {
			Mechanic m = certificate.getMechanic();
			VehicleType v = certificate.getVehicleType();

			m._getCertificates().remove(certificate);
			v._getCertificates().remove(certificate);

			certificate._setMechanic(null);
			certificate._setVehicleType(null);
		}

	}

	/**
	 * Class which links and unlinks mechanics, enrollments and courses
	 * 
	 * @author borja
	 *
	 */
	public static class Enroll {

		public static void link(Mechanic mechanic, Enrollment enrollment, Course course) {
			enrollment._setMechanic(mechanic);
			enrollment._setCourse(course);

			mechanic._getEnrollments().add(enrollment);
			course._getEnrollments().add(enrollment);
		}

		public static void unlink(Enrollment enrollment) {
			Mechanic m = enrollment.getMechanic();
			Course c = enrollment.getCourse();

			m._getEnrollments().remove(enrollment);
			c._getEnrollments().remove(enrollment);

			enrollment._setMechanic(null);
			enrollment._setCourse(null);
		}

	}

	/**
	 * Class which links and unlinks courses, dedications and vehicle types
	 * 
	 * @author borja
	 *
	 */
	public static class Dedicate {

		public static void link(Course course, Dedication dedication, VehicleType vehicleType) {
			dedication._setCourse(course);
			dedication._setVehicleType(vehicleType);

			course._getDedications().add(dedication);
			vehicleType._getDedications().add(dedication);
		}

		public static void unlink(Dedication dedication) {
			Course c = dedication.getCourse();
			VehicleType v = dedication.getVehicleType();

			c._getDedications().remove(dedication);
			v._getDedications().remove(dedication);

			dedication._setCourse(null);
			dedication._setVehicleType(null);
		}

	}
}
