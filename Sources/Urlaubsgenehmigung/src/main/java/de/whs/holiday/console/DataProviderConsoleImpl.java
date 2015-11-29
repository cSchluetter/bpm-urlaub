package de.whs.holiday.console;

import de.whs.holiday.DataProvider;
import de.whs.holiday.data.Application;
import de.whs.holiday.gui.ApplicationActionListener;


public class DataProviderConsoleImpl implements DataProvider
{
	@Override
	public void getApplication(ApplicationActionListener callback) {
		Application application = new Application();
		
		Console.writeLine("Applicant: ");
		application.setApplicant(Console.readLine());
		
		Console.writeLine("Days: ");
		application.setDays(Integer.parseInt(Console.readLine()));
		
		Console.writeLine("Holiday type?");	
		application.setHolidaytype(Console.readLine());
				
		application.setDenied(false);		
		
		callback.actionPerformed(application);
	}

	//superior approve
	@Override
	public void checkForSuperiorApprovement(Application app, ApplicationActionListener callback) {
		String text = String.format("%s wants %s day(s) %s holiday", app.getApplicant(), app.getDays(), app.getHolidaytype());
		Console.writeLine(text);
		Console.writeLine("Do you want to approve? (y, n): ");
		String input = Console.readLine();
		
		app.setDenied(input.toLowerCase().startsWith("n"));
		
		if (app.isDenied()){
			app.getNotification().setMessage("Vorgesetzer hat abgelehnt");
			app.getNotification().setFrom(app.getSuperior());
		}
		
		callback.actionPerformed(app);
	}

	//head of hr approve
	@Override
	public void checkForHrApprovement(Application app, ApplicationActionListener callback) {
		String text = String.format("%s wants %s day(s) %s holiday", app.getApplicant(), app.getDays(), app.getHolidaytype());
		Console.writeLine(text);
		Console.writeLine("Do you want to approve? (y, n): ");
		String input = Console.readLine();
		
		app.setDenied(input.toLowerCase().startsWith("n"));
		
		if (app.isDenied()){
			app.getNotification().setMessage("Personalleiter hat abgelehnt");
			app.getNotification().setFrom("Personalleiter");
		}
			
		callback.actionPerformed(app);
	}

	//Advisor approve
	@Override
	public void checkForAvailableDays(Application app, ApplicationActionListener callback) {
		String text = String.format("%s wants %s day(s) %s holiday", app.getApplicant(), app.getDays(), app.getHolidaytype());
		Console.writeLine(text);
		text = String.format("Do you want to approve? Have %s engough free days? (y, n)", app.getApplicant());
		Console.writeLine(text);
		String input = Console.readLine();
		
		app.setDenied(input.toLowerCase().startsWith("n"));
		
		if (app.isDenied()) {
			app.getNotification().setMessage("Personal Sachbearbeiter hat abgelehnt");			
			app.getNotification().setFrom(app.getAdvisor());			
		}
			
		callback.actionPerformed(app);
	}

	//co superior approve
	@Override
	public void checkForCoSuperiorApprovment(Application app, ApplicationActionListener callback) {
		String text = String.format("%s wants %s day(s) %s holiday", app.getApplicant(), app.getDays(), app.getHolidaytype());
		Console.writeLine(text);
		Console.writeLine("Do you want to approve? (y, n): ");
		String input = Console.readLine();
		
		app.setDenied(input.toLowerCase().startsWith("n"));
		
		if (app.isDenied()){
			app.getNotification().setMessage("Stellvertreter-Vorgesetzer hat abgelehnt");
			app.getNotification().setFrom(app.getCosuperior());
		}
				
		callback.actionPerformed(app);
	}
}