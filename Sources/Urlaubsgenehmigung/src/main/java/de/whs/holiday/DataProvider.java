package de.whs.holiday;

import de.whs.holiday.data.Application;


public interface DataProvider
{
	Application getApplication();

	Application checkForSuperiorApprovement(Application app);
	
	Application chechForHrApprovement(Application app);

	Application checkForAvailableDays(Application app);

	Application checkForCoSuperiorApprovment(Application app);
}