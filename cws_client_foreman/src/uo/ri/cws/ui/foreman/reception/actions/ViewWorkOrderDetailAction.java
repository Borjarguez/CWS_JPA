package uo.ri.cws.ui.foreman.reception.actions;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.ui.util.Printer;

public class ViewWorkOrderDetailAction implements Action {

	@Override
	public void execute() throws BusinessException {
	
		String woId = Console.readString("Work order id");
		
		WorkOrderCrudService as = Factory.service.forWorkOrderService();
		Optional<WorkOrderDto> oWo = as.findWorkOrderById(woId);
		
		if ( oWo.isPresent() ) {
			Printer.printWorkOrderDetail( oWo.get() );
		} else {
			Console.println("There is no work order with that id");
		}

	}
}
