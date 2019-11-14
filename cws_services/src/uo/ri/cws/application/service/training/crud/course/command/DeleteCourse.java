package uo.ri.cws.application.service.training.crud.course.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class DeleteCourse implements Command<Void> {
	private CourseRepository repo = Factory.repository.forCourse();
	private String courseId;

	public DeleteCourse(String courseId) {
		this.courseId = courseId;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<Course> oc = repo.findById(courseId);
		BusinessCheck.isTrue(oc.isPresent(), "Course does not exist");
		
		Course c = oc.get();
		BusinessCheck.isTrue(c.getEnrollments().size() == 0, "Course can not be deleted: enrollments assigned");
		
		c.clearDedications();
		
		repo.remove(c);
		return null;
	}

}
