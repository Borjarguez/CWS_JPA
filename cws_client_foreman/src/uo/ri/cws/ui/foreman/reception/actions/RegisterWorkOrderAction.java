package uo.ri.cws.ui.foreman.reception.actions;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.vehicle.VehicleDto;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.ui.util.Printer;

public class RegisterWorkOrderAction implements Action {

	private WorkOrderUserInteractor user = new WorkOrderUserInteractor();

	@Override
	public void execute() throws BusinessException {

		VehicleDto v = user.askForVehicle(); 
		Printer.printVehicleDetail( v );
		
		WorkOrderDto wo = user.askForWorkOrder(v);
		
		WorkOrderCrudService as = Factory.service.forWorkOrderService();
		as.registerNew( wo );
		
		Console.println("\nWork order registered: " + wo.id);  
	}

}
