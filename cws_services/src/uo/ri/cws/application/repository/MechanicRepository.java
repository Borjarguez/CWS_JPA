package uo.ri.cws.application.repository;

import java.util.Optional;

import uo.ri.cws.domain.Mechanic;

public interface MechanicRepository extends Repository<Mechanic> {

	/**
	 * Method which finds the mechanic for the specified dni
	 * @param dni
	 * @return the mechanic identified by the dni or null if none
	 */
	Optional<Mechanic> findByDni(String dni);

}
