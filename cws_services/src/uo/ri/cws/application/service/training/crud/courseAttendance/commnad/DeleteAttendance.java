package uo.ri.cws.application.service.training.crud.courseAttendance.commnad;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Enrollment;

public class DeleteAttendance implements Command<Void> {
	private EnrollmentRepository repo = Factory.repository.forEnrollment();
	private String attendanceId;

	public DeleteAttendance(String id) {
		this.attendanceId = id;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<Enrollment> oc = repo.findById(attendanceId);
		
		BusinessCheck.isTrue(oc.isPresent(), "Attendance does not exist");
		Enrollment e = oc.get();
		
		repo.remove(e);
		return null;
	}

}
