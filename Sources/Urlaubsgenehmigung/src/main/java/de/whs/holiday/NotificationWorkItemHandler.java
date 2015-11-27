package de.whs.holiday;

import org.kie.api.runtime.process.WorkItem;

import org.kie.api.runtime.process.WorkItemHandler;

import org.kie.api.runtime.process.WorkItemManager;

public class NotificationWorkItemHandler implements WorkItemHandler {

	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {

		// extract parameters

		String from = (String) workItem.getParameter("From");
		String to = (String) workItem.getParameter("To");
		String message = (String) workItem.getParameter("Message");
		
		String text = String.format("From: %s To %s Message: %s", from, to, message);
		
		System.out.println(text);

		manager.completeWorkItem(workItem.getId(), null);

	}

	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {

		// Do nothing, notifications cannot be aborted

	}

}