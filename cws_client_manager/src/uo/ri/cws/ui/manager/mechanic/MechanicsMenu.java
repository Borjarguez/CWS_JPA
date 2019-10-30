package uo.ri.cws.ui.manager.mechanic;

import alb.util.menu.BaseMenu;
import uo.ri.cws.ui.manager.mechanic.action.AddMechanicAction;
import uo.ri.cws.ui.manager.mechanic.action.DeleteMechanicAction;
import uo.ri.cws.ui.manager.mechanic.action.ListMechanicsAction;
import uo.ri.cws.ui.manager.mechanic.action.UpdateMechanicAction;

public class MechanicsMenu extends BaseMenu {

	public MechanicsMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Mechanics management", null},
			
			{ "Add mechanic", 		AddMechanicAction.class }, 
			{ "Update mechanic", 	UpdateMechanicAction.class }, 
			{ "Disable mechanic", 	DeleteMechanicAction.class }, 
			{ "List mechanics", 	ListMechanicsAction.class },
		};
	}

}
