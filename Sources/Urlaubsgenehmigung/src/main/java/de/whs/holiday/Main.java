package de.whs.holiday;

import java.util.HashMap;

import de.whs.holiday.console.Console;
import de.whs.holiday.console.DataProviderConsoleImpl;
import de.whs.holiday.console.NotifierConsoleImpl;
import de.whs.holiday.data.Application;
import de.whs.holiday.gui.ApplicationActionListener;
import de.whs.holiday.gui.DataProviderGuiImpl;
import de.whs.holiday.gui.NotifierGuiImpl;

public class Main {

	public static void main(String args[]) {

		final DataProvider inputProvider = new DataProviderGuiImpl();
		final Notifier notifier = new NotifierGuiImpl();
//
//		final DataProvider inputProvider = new DataProviderConsoleImpl();
//		final Notifier notifier = new NotifierConsoleImpl();
		
		final Service infoService = new ServiceImpl();

		Console.writeLine("Start with holiday application");
		inputProvider.getApplication(new ApplicationActionListener() {

			@Override
			public void actionPerformed(Application app) {			
				app.setSuperior(infoService.getSuperior(app.getApplicant()));
				app.setCosuperior(infoService.getCoSuperior(app.getApplicant()));
				app.setAdvisor(infoService.getHrAdvisor(app.getApplicant()));

				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("application", app);

				HolidayProcess process = new HolidayProcess(inputProvider, notifier);
				Console.writeLine("start workflow process");
				process.start(params);
			}
		});

	}

}
