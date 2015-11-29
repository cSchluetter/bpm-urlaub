package de.whs.holiday.gui;

import de.whs.holiday.DataProvider;
import de.whs.holiday.data.Application;


public class DataProviderGuiImpl implements DataProvider
{
	@Override
	public Application getApplication() {
		Application application = new Application();
		
		Console.writeLine("Applicant: ");
		application.setApplicant(Console.readLine());
		
		Console.writeLine("Days: ");
		application.setDays(Integer.parseInt(Console.readLine()));
		
		Console.writeLine("Holiday type?");	
		application.setHolidaytype(Console.readLine());
		
		if (application.getApplicant().toUpperCase().charAt(0) < 'L')
			application.setAdvisor("machfrei");
		else
			application.setAdvisor("urlaub");
		
		application.setDenied(false);		
		return application;
	}

	//superior approve
	@Override
	public Application checkForApprovement(Application app) {
		String text = String.format("%s wants %s days holiday", app.getApplicant(), app.getDays());
		Console.writeLine(text);
		Console.writeLine("Do you want to approve? (y, n): ");
		String input = Console.readLine();
		
		app.setDenied(input.toLowerCase().startsWith("n"));
		
		if (app.isDenied())
			app.getNotification().setMessage("Vorgesetzer hat abgelehnt");
		else 
			app.getNotification().setMessage("Vorgesetzer hat genehmigt");

		
		return app;
	}

	//head of hr approve
	@Override
	public Application chechForHrApprovement(Application app) {
		String text = String.format("%s wants %s days holiday", app.getApplicant(), app.getDays());
		Console.writeLine(text);
		Console.writeLine("Do you want to approve? (y, n): ");
		String input = Console.readLine();
		
		app.setDenied(input.toLowerCase().startsWith("n"));
		
		if (app.isDenied())
			app.getNotification().setMessage("Personalleiter hat abgelehnt");
		else 
			app.getNotification().setMessage("Personalleiter hat genehmigt");

		
		return app;
	}

	//Advisor approve
	@Override
	public Application checkForAvailableDays(Application app) {
		String text = String.format("Have %s engough free days? (y, n)", app.getApplicant());
		Console.writeLine(text);
		String input = Console.readLine();
		
		app.setDenied(input.toLowerCase().startsWith("n"));
		
		if (app.isDenied())
			app.getNotification().setMessage("Personal Sachbearbeiter hat abgelehnt");
		else 
			app.getNotification().setMessage("Personal Sachbearbeiter hat genehmigt");

		return app;
	}

	//co superior approve
	@Override
	public Application checkForCoSuperiorApprovment(Application app) {
		String text = String.format("%s wants %s days holiday", app.getApplicant(), app.getDays());
		Console.writeLine(text);
		Console.writeLine("Do you want to approve? (y, n): ");
		String input = Console.readLine();
		
		app.setDenied(input.toLowerCase().startsWith("n"));
		
		if (app.isDenied())
			app.getNotification().setMessage("Stellvertreter-Vorgesetzer hat abgelehnt");
		else 
			app.getNotification().setMessage("Stellvertreter-Vorgesetzer hat genehmigt");

		
		return app;
	}
}