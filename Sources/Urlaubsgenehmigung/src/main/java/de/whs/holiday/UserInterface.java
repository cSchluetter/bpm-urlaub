package de.whs.holiday;

import de.whs.holiday.data.Application;
import de.whs.holiday.gui.ApplicationActionListener;


public interface UserInterface
{
	public void getApplication(ApplicationActionListener callback);

	public void checkForSuperiorApprovement(Application app, ApplicationActionListener callback);
	
	public void checkForHrApprovement(Application app, ApplicationActionListener callback);

	public void checkForAvailableDays(Application app, ApplicationActionListener callback);

	public void checkForCoSuperiorApprovment(Application app, ApplicationActionListener callback);
}