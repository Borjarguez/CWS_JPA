package uo.ri.cws.application.service.training.crud.courseReport.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.DedicationRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.TrainingForMechanicRow;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class FindTrainingByMechanicId implements Command<List<TrainingForMechanicRow>>{
	private MechanicRepository repo = Factory.repository.forMechanic();
	private VehicleTypeRepository repoType = Factory.repository.forVehicleType();
	private DedicationRepository repoDedicate = Factory.repository.forDedication();
	private String mechanicId;
	
	public FindTrainingByMechanicId(String id) {
		this.mechanicId = id;
	}

	@Override
	public List<TrainingForMechanicRow> execute() throws BusinessException {
		Optional<Mechanic> om = repo.findById(mechanicId);
		
		BusinessCheck.isTrue(om.isPresent(), "Mechanic does not exist");
		Mechanic m = om.get();
		
		List<VehicleType> vehicleTypes = repoType.findAll();
		List<TrainingForMechanicRow> rows = new ArrayList<TrainingForMechanicRow>();
		
		for(VehicleType v: vehicleTypes) {
			TrainingForMechanicRow row = new TrainingForMechanicRow();
			row.vehicleTypeName = v.getName();
			row.attendedHours = repoDedicate.findAttendanceHours(m,v).get().intValue();
			row.enrolledHours = repoDedicate.findEnrolledHours(m,v).get().intValue();
			rows.add(row);
		}
		
		return rows;
	}
}
