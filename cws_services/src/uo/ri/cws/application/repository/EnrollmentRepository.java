package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.Enrollment;

public interface EnrollmentRepository extends Repository<Enrollment>{

	List<Enrollment> findByCourseId(String id);

}
