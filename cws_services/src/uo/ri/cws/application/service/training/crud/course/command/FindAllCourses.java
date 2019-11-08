package uo.ri.cws.application.service.training.crud.course.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

public class FindAllCourses implements Command<List<CourseDto>>{
	private CourseRepository repo = Factory.repository.forCourse();

	@Override
	public List<CourseDto> execute() throws BusinessException {
		List<CourseDto> list = DtoAssembler.toCourseDtoList(repo.findAll());
		return list;
	}

}
