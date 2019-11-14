package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class DeleteWorkOrder implements Command<Void> {
	private WorkOrderRepository repo = Factory.repository.forWorkOrder();
	private String woId;

	public DeleteWorkOrder(String woId) {
		this.woId = woId;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<WorkOrder> ow = repo.findById(woId);
		BusinessCheck.isTrue(ow.isPresent(), "WorkOrder does not exist");
		
		WorkOrder w = ow.get();
		BusinessCheck.isTrue(w.getInterventions().size() == 0, "WorkOrder can not be deleted");
		
		repo.remove(w);
		return null;
	}

}
