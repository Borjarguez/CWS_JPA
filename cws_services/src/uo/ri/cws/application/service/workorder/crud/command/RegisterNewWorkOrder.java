package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;

public class RegisterNewWorkOrder implements Command<WorkOrderDto> {
	private VehicleRepository repoVehicle = Factory.repository.forVehicle();
	private WorkOrderRepository repoWorkOrder = Factory.repository.forWorkOrder();
	private WorkOrderDto workOrder;
	
	public RegisterNewWorkOrder(WorkOrderDto workOrder) {
		this.workOrder = workOrder;
	}
	
	@Override
	public WorkOrderDto execute() throws BusinessException {
		Optional<Vehicle> ov = repoVehicle.findById(workOrder.vehicleId);
		BusinessCheck.isTrue(ov.isPresent(), "Vehicle does not exist");
		
		WorkOrder w = new WorkOrder(ov.get(), workOrder.description);
		repoWorkOrder.add(w);
		workOrder.id = w.getId();
		return workOrder;
	}

}
