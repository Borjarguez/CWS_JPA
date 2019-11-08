package uo.ri.cws.ui;

import alb.util.menu.BaseMenu;
import uo.ri.cws.ui.manager.mechanic.MechanicsMenu;
import uo.ri.cws.ui.manager.sparepart.SparepartsMenu;
import uo.ri.cws.ui.manager.training.TrainingMenu;
import uo.ri.cws.ui.manager.vehicletype.VehicleTypesMenu;

public class MainMenu extends BaseMenu {{
		menuOptions = new Object[][] { 
			{ "Manager", null },
			
			{ "Mechanics management", 		MechanicsMenu.class },
			{ "Spareparts management", 		SparepartsMenu.class },
			{ "Vehicle types management", 	VehicleTypesMenu.class }, 
			{ "Training management", 		TrainingMenu.class }, 
		};
}}