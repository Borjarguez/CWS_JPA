package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

public class AssignWorkOrder implements Command<Void> {
	private MechanicRepository repoMechanic = Factory.repository.forMechanic();
	private WorkOrderRepository repoWorkOrder = Factory.repository.forWorkOrder();

	private String woId;
	private String mechanicId;

	public AssignWorkOrder(String woId, String mechanicId) {
		this.woId = woId;
		this.mechanicId = mechanicId;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<Mechanic> om = repoMechanic.findById(mechanicId);
		BusinessCheck.isTrue(om.isPresent(), "Mechanic does not exist");

		Optional<WorkOrder> ow = repoWorkOrder.findById(woId);
		BusinessCheck.isTrue(ow.isPresent(), "Work order does not exist");

		Mechanic m = om.get();
		WorkOrder w = ow.get();
		VehicleType v = w.getVehicle().getVehicleType();
		BusinessCheck.isTrue(m.isCertifiedFor(v), "Mechanic must be certified");
		BusinessCheck.isTrue(w.getStatus().equals(WorkOrderStatus.OPEN), "Work order must be openned");

		w.assignTo(m);
		return null;
	}


}
