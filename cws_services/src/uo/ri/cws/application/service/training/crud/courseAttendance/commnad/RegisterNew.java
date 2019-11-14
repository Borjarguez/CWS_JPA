package uo.ri.cws.application.service.training.crud.courseAttendance.commnad;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.EnrollmentRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.EnrollmentDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;

public class RegisterNew implements Command<EnrollmentDto> {
	private EnrollmentRepository repo = Factory.repository.forEnrollment();
	private MechanicRepository repoMechanic = Factory.repository.forMechanic();
	private CourseRepository repoCourse = Factory.repository.forCourse();
	private EnrollmentDto dto;

	public RegisterNew(EnrollmentDto dto) {
		this.dto = dto;
	}

	@Override
	public EnrollmentDto execute() throws BusinessException {
		BusinessCheck.isNotNull(dto, "Dto is missing");

		Optional<Mechanic> m = repoMechanic.findById(dto.mechanicId);
		Optional<Course> c = repoCourse.findById(dto.courseId);
		Optional<Enrollment> en = repo.findByMechanicIdCourseId(dto.mechanicId, dto.courseId);

		//////// Security checks ///////////////
		BusinessCheck.isFalse(en.isPresent(), "Attendance already exists");
		BusinessCheck.isTrue(m.isPresent(), "Mechanic does not exist");
		BusinessCheck.isTrue(c.isPresent(), "Course does not exist");
		BusinessCheck.isFalse(dto.attendance < 85 && dto.passed, "Attendance to pass must be over 85%");
		BusinessCheck.isFalse(dto.attendance < 0, "Attendance must be over 0");
		BusinessCheck.isFalse(dto.attendance > 100, "Attendance must be under 100");

		Enrollment e = new Enrollment(m.get(), c.get(), dto.attendance, dto.passed);
		repo.add(e);

		dto.id = e.getId();
		return dto;
	}

}
