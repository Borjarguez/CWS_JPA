package uo.ri.cws.application.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Enrollment;

public interface EnrollmentRepository extends Repository<Enrollment> {

	/**
	 * Method which finds the list of enrollments for a course id
	 * 
	 * @param id, the course id
	 * @return the list of enrollments
	 */
	List<Enrollment> findByCourseId(String id);

	/**
	 * Method which finds the enrollment for the specified mechanic and course ids
	 * 
	 * @param mechanicId, the mechanic id
	 * @param courseId,   the course id
	 * @return the enrollment
	 */
	Optional<Enrollment> findByMechanicIdCourseId(String mechanicId, String courseId);

}
