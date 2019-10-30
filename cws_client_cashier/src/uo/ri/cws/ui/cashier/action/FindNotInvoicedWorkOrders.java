package uo.ri.cws.ui.cashier.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.invoice.CreateInvoiceService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.ui.util.Printer;

public class FindNotInvoicedWorkOrders implements Action {

	@Override
	public void execute() throws BusinessException {
		CreateInvoiceService cs = Factory.service.forCreateInvoiceService();

		String dni = Console.readString("Client dni:");
		
		Console.println("\nInvoice-pending work orders\n");  
		
		List<WorkOrderDto> reps = cs.findWorkOrdersByClientDni( dni ); 
		
		if (reps.size() == 0) {
			Console.printf("There is no pending work orders\n");
			return;
		}
		
		for(WorkOrderDto rep : reps) {
			Printer.printWorkOrder( rep );
		}
	}

}
