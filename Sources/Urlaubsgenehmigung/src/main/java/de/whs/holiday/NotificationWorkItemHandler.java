package de.whs.holiday;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

import de.whs.holiday.console.Console;

public class NotificationWorkItemHandler implements WorkItemHandler {
	
	private final Notifier notifier;
	
	public NotificationWorkItemHandler(Notifier notifier){
		this.notifier = notifier;
	}

	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {

		Console.writeLine("execute WorkItem: " + workItem);

		String from = (String) workItem.getParameter("From");
		String to = (String) workItem.getParameter("To");
		String message = (String) workItem.getParameter("Message");
		
		String title = String.format("Hey %s - eine Nachricht von %s", to, from);
		String text = message;
		
		notifier.sendNotification(title, text);

		manager.completeWorkItem(workItem.getId(), null);
		Console.writeLine("completed WorkItem: " + workItem);
	}

	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		System.out.println("abort WorkItem: " + workItem);
		manager.abortWorkItem(workItem.getId());
	}

}