package de.whs.holiday;

import java.util.HashMap;

import de.whs.holiday.console.Console;
import de.whs.holiday.data.Application;
import de.whs.holiday.gui.ApplicationActionListener;
import de.whs.holiday.gui.SwingUserInterfaceImpl;
import de.whs.holiday.gui.SwingNotifierImpl;

public class Main {

	public static void main(String args[]) {

		final UserInterface userInterface = new SwingUserInterfaceImpl();
		final Notifier notifier = new SwingNotifierImpl();

//		final DataProvider userInterface = new DataProviderConsoleImpl();
//		final Notifier notifier = new NotifierConsoleImpl();
		
		final HumanResourceService hrService = new HumanResourceServiceImpl();

		Console.writeLine("Start with holiday application");
		userInterface.getApplication(new ApplicationActionListener() {

			@Override
			public void actionPerformed(Application app) {			
				app.setSuperior(hrService.getSuperior(app.getApplicant()));
				app.setCosuperior(hrService.getCoSuperior(app.getApplicant()));
				app.setAdvisor(hrService.getHrAdvisor(app.getApplicant()));

				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("application", app);

				HolidayProcess process = new HolidayProcess(userInterface, notifier);
				Console.writeLine("start workflow process");
				process.start(params);
			}
		});
	}
}
