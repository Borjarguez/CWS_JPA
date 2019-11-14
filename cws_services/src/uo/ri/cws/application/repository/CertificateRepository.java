package uo.ri.cws.application.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public interface CertificateRepository extends Repository<Certificate> {

	/**
	 * Method which calculates the total hours of the certificate
	 * 
	 * @param m, the mechanic
	 * @param v, the vehicle type
	 * @return the amount of the certificate's hours
	 */
	Optional<BigDecimal> findTotalHoursCertificate(Mechanic m, VehicleType v);

	/**
	 * Method which finds the certificate for an specified mechanic and vehicle type
	 * 
	 * @param m, the mechanic
	 * @param v, the vehicle type
	 * @return the certificate if exists
	 */
	Optional<Certificate> findCertificateByMechanicVehicleTypeIDs(Mechanic m, VehicleType v);

	/**
	 * Method which finds the certificates for a vehicle type
	 * 
	 * @param vehicleTypeId, the vehicle type's id
	 * @return a list with the certificates for the id
	 */
	List<Certificate> findCertificatesByVehicleTypeId(String vehicleTypeId);
}
