package de.whs.holiday;


public interface DataProvider
{
	Application getApplication();

	Application checkForApprovement(Application app);
}