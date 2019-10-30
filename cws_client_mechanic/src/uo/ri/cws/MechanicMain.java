package uo.ri.cws;

import alb.util.console.Printer;
import uo.ri.cws.ui.MainMenu;

public class MechanicMain {

	public static void main(String[] args) {
		new MechanicMain()
			.config()
			.run()
			.close();
	}

	private MechanicMain config() {
		return this;
	}

	public MechanicMain run() {
		try {
			new MainMenu().execute();

		} catch (RuntimeException rte) {
			Printer.printRuntimeException(rte);
		}
		return this;
	}

	private void close() {
	}

}
