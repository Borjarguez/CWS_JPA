package uo.ri.cws.ui.foreman.reception;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.cws.ui.foreman.reception.actions.AssignWorkOrderAction;
import uo.ri.cws.ui.foreman.reception.actions.ListCertifiedMechanicsAction;
import uo.ri.cws.ui.foreman.reception.actions.RegisterWorkOrderAction;
import uo.ri.cws.ui.foreman.reception.actions.RemoveWorkOrderAction;
import uo.ri.cws.ui.foreman.reception.actions.UpdateWorkOrderAction;

public class ReceptionMenu extends BaseMenu {

	public ReceptionMenu() {
		menuOptions = new Object[][] { 
			{"Foreman > Vehicle reception", null},
			
			{"Register work order", 	RegisterWorkOrderAction.class }, 
			{"Update workorder", 		UpdateWorkOrderAction.class },
			{"Remove workorder", 		RemoveWorkOrderAction.class },
			{"", null},
			{"List work orders", 		NotYetImplementedAction.class }, 
			{"View work order detail", 	NotYetImplementedAction.class },
			{"", null},
			{"List certified mechanics",ListCertifiedMechanicsAction.class }, 
			{"Assign a work order",  	AssignWorkOrderAction.class },
		};
	}

}
