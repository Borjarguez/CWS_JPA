package uo.ri.cws.ui;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.cws.ui.foreman.cliente.ClientsMenu;
import uo.ri.cws.ui.foreman.reception.ReceptionMenu;
import uo.ri.cws.ui.foreman.vehicle.VehiclesMenu;

public class MainMenu extends BaseMenu {{
	menuOptions = new Object[][] { 
		{ "Foreman", null },
		{ "Vehicle reception", 		ReceptionMenu.class }, 
		{ "Client management", 		ClientsMenu.class },
		{ "Vehicle management", 	VehiclesMenu.class },
		{ "Review client history", 	NotYetImplementedAction.class }, 
	};
}}