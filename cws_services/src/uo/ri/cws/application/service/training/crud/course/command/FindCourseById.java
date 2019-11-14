package uo.ri.cws.application.service.training.crud.course.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class FindCourseById implements Command<Optional<CourseDto>> {
	private CourseRepository repo = Factory.repository.forCourse();
	private String id;

	public FindCourseById(String id) {
		this.id = id;
	}

	@Override
	public Optional<CourseDto> execute() throws BusinessException {
		Optional<Course> oc = repo.findById(id);
		BusinessCheck.isTrue(oc.isPresent(), "Course does not exist");

		return Optional.of(DtoAssembler.toDto(oc.get()));
	}

}
