package uo.ri.cws.ui.foreman.reception.actions;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.AssignWorkOrderService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.ui.util.Printer;

public class ListUnfinishedWorkOrdersAction implements Action {

	@Override
	public void execute() throws BusinessException {

		AssignWorkOrderService as = Factory.service.forAssignWorkOrderService();
		List<WorkOrderDto> wos = as.findUnfinishedWorkOrders();

		Console.println("In process work orders");
		for(WorkOrderDto wo: wos) {
			Printer.printWorkOrderDetail( wo );
		}

	}
}
