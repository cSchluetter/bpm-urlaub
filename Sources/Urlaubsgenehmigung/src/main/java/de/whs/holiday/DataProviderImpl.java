package de.whs.holiday;


public class DataProviderImpl implements DataProvider
{
	@Override
	public Application getApplication() {
		Application application = new Application();
		
		Console.writeLine("Applicant: ");
		application.setApplicant(Console.readLine());
		
		Console.writeLine("Days: ");
		application.setDays(Integer.parseInt(Console.readLine()));
		
		application.setDenied(false);		
		return application;
	}

	@Override
	public Application checkForApprovement(Application app) {
		String text = String.format("%s wants %s days holiday", app.getApplicant(), app.getDays());
		Console.writeLine(text);
		Console.writeLine("Do you want to approve? (y, n): ");
		String input = Console.readLine();
		
		app.setDenied(input.toLowerCase().startsWith("n"));
		
		return app;
	}
	
}