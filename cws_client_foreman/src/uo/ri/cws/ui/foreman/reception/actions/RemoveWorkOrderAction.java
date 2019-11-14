package uo.ri.cws.ui.foreman.reception.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;

public class RemoveWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {
	
		String woId = Console.readString("Work order id");
		
		WorkOrderCrudService as = Factory.service.forWorkOrderService();
		as.deleteWorkOrder( woId );
		
		Console.println("\nThe work order has been deleted");  
	}
}
