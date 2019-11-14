package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.math.BigDecimal;
import java.util.Optional;

import uo.ri.cws.application.repository.DedicationRepository;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class DedicationJpaRepository extends BaseJpaRepository<Dedication> implements DedicationRepository {

	@Override
	public Optional<BigDecimal> findAttendanceHours(Mechanic m, VehicleType v) {
		return Jpa.getManager()
				.createNamedQuery("Dedication.findAttendance", BigDecimal.class)
				.setParameter(1, m)
				.setParameter(2, v)
				.getResultStream()
				.findFirst();
	}

	@Override
	public Optional<BigDecimal> findEnrolledHours(Mechanic m, VehicleType v) {
		return Jpa.getManager()
				.createNamedQuery("Dedication.findEnrolledHours", BigDecimal.class)
				.setParameter(1, m)
				.setParameter(2, v)
				.getResultStream()
				.findFirst();
	}

}
