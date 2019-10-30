package uo.ri.cws.ui;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.cws.ui.cashier.action.FindNotInvoicedWorkOrders;
import uo.ri.cws.ui.cashier.action.InvoiceWorkorderAction;

public class MainMenu extends BaseMenu {{
	menuOptions = new Object[][] { 
		{ "Cash", null },
		{ "Find not invoiced work orders", 	FindNotInvoicedWorkOrders.class }, 
		{ "Find work order by plate", 		NotYetImplementedAction.class }, 
		{ "Inovice work orders", 			InvoiceWorkorderAction.class },
		{ "Liquidate invoice", 				NotYetImplementedAction.class },
	};
}}