package uo.ri.cws.application.service.training.crud.course;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseCrudService;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.service.training.crud.course.command.FindAllCourses;
import uo.ri.cws.application.service.vehicletype.VehicleTypeDto;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CourseCrudServiceImpl implements CourseCrudService {
	
	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public CourseDto registerNew(CourseDto dto) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCourse(CourseDto dto) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCourse(String id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CourseDto> findAllCourses() throws BusinessException {
		return executor.execute(new FindAllCourses());
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CourseDto> findCourseById(String cId) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
