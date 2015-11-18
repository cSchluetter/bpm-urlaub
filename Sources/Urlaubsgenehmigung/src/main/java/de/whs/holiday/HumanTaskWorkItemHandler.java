package de.whs.holiday;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;




public class HumanTaskWorkItemHandler implements WorkItemHandler {

	private final DataProvider dataProvider;

	public HumanTaskWorkItemHandler(DataProvider dataProvider) {
		this.dataProvider = dataProvider;
	}
		
	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// Nothing to do
		return;
	}

	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		System.out.println("executeWorkItem: " + workItem);

		String taskName = (String) workItem.getParameter("TaskName");
		System.out.println("Task-Name: " + taskName);
		
		Map<String, Object> params = new HashMap<String, Object>();
		Application app;

		switch (taskName) {
			case "applyForHoliday":
				app = dataProvider.getApplication();				
				params.put("application", app);				
				break;
				
			case "approve":
				app = (Application)workItem.getParameter("application");
				app = dataProvider.checkForApprovement(app);
				params.put("application", app);
				break;
		}

		manager.completeWorkItem(workItem.getId(), params);
	}
}
