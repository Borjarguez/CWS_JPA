package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class FindWorkOrderById implements Command<Optional<WorkOrderDto>>{
	private WorkOrderRepository repo = Factory.repository.forWorkOrder();
	private String woId;
	
	public FindWorkOrderById(String woId) {
		this.woId = woId;
	}

	@Override
	public Optional<WorkOrderDto> execute() throws BusinessException {
		Optional<WorkOrder> ow = repo.findById(woId);
		BusinessCheck.isTrue(ow.isPresent(), "Work order does not exist");
		return ow.isPresent() ? Optional.of(DtoAssembler.toDto(ow.get())) : Optional.empty();
	}
	
	

}
