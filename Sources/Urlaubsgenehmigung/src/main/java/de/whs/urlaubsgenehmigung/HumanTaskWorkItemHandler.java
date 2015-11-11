package de.whs.urlaubsgenehmigung;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class HumanTaskWorkItemHandler implements WorkItemHandler {

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
		manager.completeWorkItem(workItem.getId(), null);		
	}
}
