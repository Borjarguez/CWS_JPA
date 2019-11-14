package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

public class UpdateWorkOrder implements Command<Void> {

	private WorkOrderRepository repo = Factory.repository.forWorkOrder();
	private WorkOrderDto dto;

	public UpdateWorkOrder(WorkOrderDto dto) {
		this.dto = dto;
	}

	@Override
	public Void execute() throws BusinessException {
		Optional<WorkOrder> ow = repo.findById(dto.id);

		BusinessCheck.isTrue(ow.isPresent(), "WorkOrder does not exist");
		WorkOrder w = ow.get();
		BusinessCheck.isTrue(
				w.getStatus().equals(WorkOrderStatus.OPEN) || w.getStatus().equals(WorkOrderStatus.ASSIGNED),
				"Work order must be openned or assigned");
		BusinessCheck.hasVersion(w, dto.version);

		w.setDescription(dto.description);
		return null;
	}

}
