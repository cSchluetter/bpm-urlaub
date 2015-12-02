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

	private final DataProvider dataProvider;
	private boolean isTimerRunning = false;
	
	public boolean isTimerRunning() { return isTimerRunning; }

	public HumanTaskWorkItemHandler(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
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
				dataProvider.checkForSuperiorApprovement(app,callback);
				isTimerRunning = true;
				break;
				
			case "headOfHrApprove":
				dataProvider.checkForHrApprovement(app,callback);
				break;
				
			case "advisorHrApprove":
				dataProvider.checkForAvailableDays(app,callback);
				break;
				
			case "coSuperiorApprove":
				dataProvider.checkForCoSuperiorApprovment(app,callback);
				break;
		}
	}
}
