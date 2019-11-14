package uo.ri.cws.application.repository;

import java.math.BigDecimal;
import java.util.Optional;

import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public interface DedicationRepository extends Repository<Dedication> {

	/**
	 * Method which calculates the attended hours of a mechanic and a vehicle type
	 * 
	 * @param m, the mechanic
	 * @param v, the vehicle type
	 * @return the amount of attended hours
	 */
	Optional<BigDecimal> findAttendanceHours(Mechanic m, VehicleType v);

	/**
	 * Method which calculates the enrolled hours of a mechanic and a vehicle type
	 * 
	 * @param m, the mechanic
	 * @param v, the vehicle type
	 * @return the amount of enrolled hours
	 */
	Optional<BigDecimal> findEnrolledHours(Mechanic m, VehicleType v);

}
