package uo.ri.cws.ui.manager.training.reports.actions;

import java.util.List;

import alb.util.console.Console;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.mechanic.MechanicDto;
import uo.ri.cws.application.service.training.CourseAttendanceService;
import uo.ri.cws.application.service.training.CourseCrudService;
import uo.ri.cws.application.service.vehicletype.VehicleTypeDto;
import uo.ri.cws.ui.util.Printer;

public class ReportsUserInteractor {

	public String askForMechanicId() throws BusinessException {
		showMechanics();
		return Console.readString("Mechanic id");
	}

	public String askForVehicleTypeId() throws BusinessException {
		showVehicleTypes();
		return Console.readString("Vehicle type id");
	}

	private void showVehicleTypes() throws BusinessException {
		CourseCrudService cs = Factory.service.forCourseCrudService();
		List<VehicleTypeDto> mechanics = cs.findAllVehicleTypes();
		Console.println("List of vehicle types");
		mechanics.forEach((vt) -> Printer.printVehicleType( vt ) );
	}

	private void showMechanics() throws BusinessException {
		CourseAttendanceService cs = Factory.service.forCourseAttendanceService();
		List<MechanicDto> mechanics = cs.findAllActiveMechanics();
		Console.println("List of mechanics");
		mechanics.forEach((m) -> Printer.printMechanic(m) );
	}

}
