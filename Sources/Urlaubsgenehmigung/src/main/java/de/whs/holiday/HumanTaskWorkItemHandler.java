package de.whs.holiday;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import de.whs.holiday.console.Console;
import de.whs.holiday.data.Application;
import de.whs.holiday.gui.ApplicationActionListener;

public class HumanTaskWorkItemHandler implements WorkItemHandler {

	private final UserInterface userInterface;
	private boolean isTimerRunning = false;
	
	public boolean isTimerRunning() { return isTimerRunning; }

	public HumanTaskWorkItemHandler(UserInterface userInterface) {
		this.userInterface = userInterface;
	}
		
	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		System.out.println("abort WorkItem: " + workItem);
		manager.abortWorkItem(workItem.getId());
	}

	@Override
	public void executeWorkItem(final WorkItem workItem, final WorkItemManager manager) {
		System.out.println("execute WorkItem: " + workItem);

		String taskName = (String) workItem.getParameter("TaskName");
		
		final Map<String, Object> params = new HashMap<String, Object>();
		Application app = (Application)workItem.getParameter("application");

		ApplicationActionListener callback = new ApplicationActionListener() {
			@Override
			public void actionPerformed(Application app) {
				params.put("application", app);
				manager.completeWorkItem(workItem.getId(), params);
				Console.writeLine("completed WorkItem: " + workItem);
			}
		};		
		
		switch (taskName) {			
			case "superiorApprove":
				userInterface.checkForSuperiorApprovement(app,callback);
				isTimerRunning = true;
				break;
				
			case "headOfHrApprove":
				userInterface.checkForHrApprovement(app,callback);
				break;
				
			case "advisorHrApprove":
				userInterface.checkForAvailableDays(app,callback);
				break;
				
			case "coSuperiorApprove":
				userInterface.checkForCoSuperiorApprovment(app,callback);
				break;
		}
	}
}
