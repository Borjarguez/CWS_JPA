package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;

import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class EnrollmentJpaRepository extends BaseJpaRepository<Enrollment> implements EnrollmentRepository {

	@Override
	public List<Enrollment> findByCourseId(String id) {
		// TODO Query must be implemented
		return Jpa.getManager()
				.createNamedQuery("Enrollment.findByCourseId", Enrollment.class)
				.setParameter(1, id)
				.getResultList();
	}

}
