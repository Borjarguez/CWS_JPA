package uo.ri.cws.application.repository;

import java.util.Optional;

import uo.ri.cws.domain.Course;

public interface CourseRepository extends Repository<Course> {

	/**
	 * Method which finds the course by its code
	 * 
	 * @param code, the course code
	 * @return the course if it exists, empty in other case
	 */
	Optional<Course> findByCode(String code);
}
