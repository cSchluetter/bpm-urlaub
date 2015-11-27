package de.whs.holiday;


public interface DataProvider
{
	Application getApplication();

	Application checkForApprovement(Application app);
	
	Application chechForHrApprovement(Application app);

	Application checkForAvailableDays(Application app);

	Application checkForCoSuperiorApprovment(Application app);
}