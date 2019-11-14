package uo.ri.cws.application.service.training.crud.courseReport.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.DedicationRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

public class FindTrainingByVehicleTypeAndMechanic implements Command<List<TrainingHoursRow>> {
	private MechanicRepository repo = Factory.repository.forMechanic();
	private VehicleTypeRepository repoType = Factory.repository.forVehicleType();
	private DedicationRepository repoDedicate = Factory.repository.forDedication();

	@Override
	public List<TrainingHoursRow> execute() throws BusinessException {
		List<VehicleType> vehicleTypes = repoType.findAll();
		List<Mechanic> mechanics = repo.findAll();
		
		List<TrainingHoursRow> rows = new ArrayList<TrainingHoursRow>();
		int hours = 0;
		
		for(VehicleType v : vehicleTypes) {
			for(Mechanic m: mechanics) {
				hours = repoDedicate.findAttendanceHours(m, v).get().intValue();
				if(hours > 0) {
					TrainingHoursRow row = new TrainingHoursRow();
					row.mechanicFullName = m.getName()+" "+m.getSurname();
					row.vehicleTypeName = v.getName();
					row.enrolledHours = hours;
					rows.add(row);
				}		
			}
		}
		return rows;
	}

}
