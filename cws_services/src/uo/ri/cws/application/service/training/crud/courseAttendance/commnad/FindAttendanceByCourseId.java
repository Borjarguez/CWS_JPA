package uo.ri.cws.application.service.training.crud.courseAttendance.commnad;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;

public class FindAttendanceByCourseId implements Command<List<EnrollmentDto>>{
	private EnrollmentRepository repo = Factory.repository.forEnrollment();
	private String id;
	
	public FindAttendanceByCourseId(String id) {
		this.id = id;
	}
	
	@Override
	public List<EnrollmentDto> execute() throws BusinessException {
		List<EnrollmentDto> list = DtoAssembler.toEnrollmentDtoList(repo.findByCourseId(id));
		return list;
	}

}
