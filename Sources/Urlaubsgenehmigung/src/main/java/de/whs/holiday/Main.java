package de.whs.holiday;

import java.util.HashMap;

import de.whs.holiday.console.DataProviderConsoleImpl;
import de.whs.holiday.data.Application;

public class Main {

	public static void main(String args[]){
		
		DataProvider inputProvider = new DataProviderConsoleImpl();
		Service infoService = new ServiceImpl();
		
		Application app = inputProvider.getApplication();
		app.setSuperior(infoService.getSuperior(app.getApplicant()));
		app.setCosuperior(infoService.getCoSuperior(app.getApplicant()));
		app.setAdvisor(infoService.getHrAdvisor(app.getApplicant()));
			
		HashMap<String, Object> params= new HashMap<String, Object>();
		params.put("application", app);
		
		HolidayProcess process = new HolidayProcess(inputProvider);
		process.start(params);
	}
	
}
