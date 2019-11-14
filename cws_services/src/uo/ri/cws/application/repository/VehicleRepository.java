package uo.ri.cws.application.repository;

import java.util.Optional;

import uo.ri.cws.domain.Vehicle;

public interface VehicleRepository extends Repository<Vehicle> {

	/**
	 * Method which finds the vehicle by its plate
	 * 
	 * @param plate, the vehicle plate
	 * @return the vehicle
	 */
	Optional<Vehicle> findByPlate(String plate);

}
