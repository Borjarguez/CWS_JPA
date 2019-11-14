package uo.ri.cws.application.service.training.crud.course.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import alb.util.date.Dates;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.VehicleType;

public class UpdateCourse implements Command<Void> {
	private VehicleTypeRepository repoType = Factory.repository.forVehicleType();
	private CourseRepository repo = Factory.repository.forCourse();
	private CourseDto dto;

	public UpdateCourse(CourseDto dto) {
		this.dto = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		checkEverything();

		Optional<Course> oc = repo.findById(dto.id);
		BusinessCheck.isTrue(oc.isPresent(), "Course does not exist");
		
		Course c = oc.get();
		BusinessCheck.hasVersion(c, dto.version);
		
		c.setName(dto.name);
		c.setDescription(dto.description);
		c.setStartDate(dto.startDate);
		c.setEndDate(dto.endDate);
		c.setHours(dto.hours);

		c.clearDedications();
		c.addDedications(generateDedications());
		
		return null;
	}

	/**
	 * Method which checks that everything is OK
	 * 
	 * @throws BusinessException
	 */
	private void checkEverything() throws BusinessException {
		// any field other than id and version is null or empty
		BusinessCheck.isNotNull(dto.name, "Course's name missing");
		BusinessCheck.isNotNull(dto.code, "Course's code missing");
		BusinessCheck.isNotNull(dto.description, "Course's description missing");
		BusinessCheck.isNotNull(dto.startDate, "Course's start date missing");
		BusinessCheck.isNotNull(dto.endDate, "Course's end date missing");
		BusinessCheck.isNotNull(dto.hours, "Course's hours missing");
		BusinessCheck.isNotNull(dto.percentages, "Course's percentages missing");

		// the course already exists
		Optional<Course> oc = repo.findByCode(dto.code);
		BusinessCheck.isFalse(oc.isPresent(), "Course already exists");

		// Check the dto has dedications
		BusinessCheck.isTrue(!dto.percentages.isEmpty(), "Course's dedications are empty");

		// the vehicle types exist
		List<String> vehicleTypeIds = new ArrayList<String>(dto.percentages.keySet());

		for (String id : vehicleTypeIds) {
			Optional<VehicleType> v = repoType.findById(id);
			BusinessCheck.isTrue(v.isPresent(), "Vehicle type does not exist");
		}

		// Dates checks
		BusinessCheck.isTrue(dto.startDate.before(dto.endDate), "Start date must be before end date");
		BusinessCheck.isTrue(dto.endDate.after(dto.startDate), "End date must be after start date");
		BusinessCheck.isFalse(dto.startDate.before(Dates.today()), "Start date must be after today");
		BusinessCheck.isFalse(dto.endDate.before(Dates.today()), "End date must be after today");

		// hours over 0
		BusinessCheck.isTrue(dto.hours > 0, "Hours must be over 0");

		// Percentages checks
		List<Integer> percentages = new ArrayList<Integer>(dto.percentages.values());
		int sum = percentages.stream().reduce(0, Integer::sum);

		BusinessCheck.isTrue(sum != 100, "Percentages must sum 100");

		for (Integer in : percentages) {
			BusinessCheck.isTrue(in > 0 && in <= 100, "Vehicle type does not exist");
		}
	}
	
	/**
	 * Method which converts the dto's map to another type of map 
	 * @return the new map
	 */
	private Map<VehicleType, Integer> generateDedications() {
		Map<VehicleType, Integer> map = new HashMap<>();

		for (Map.Entry<String, Integer> dedication : dto.percentages.entrySet()) {
			map.put(repoType.findById(dedication.getKey()).get(), dedication.getValue());
		}
		
		return map;

	}

}
