package uo.ri.cws.ui.foreman.reception.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.AssignWorkOrderService;

public class AssignWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		String woId = Console.readString("Work order id");
		String mId = Console.readString("Mechanic id");

		AssignWorkOrderService as = Factory.service.forAssignWorkOrderService();
		as.assignWorkOrderToMechanic(woId, mId);

		Console.println("\nAssignation done");
	}
}
