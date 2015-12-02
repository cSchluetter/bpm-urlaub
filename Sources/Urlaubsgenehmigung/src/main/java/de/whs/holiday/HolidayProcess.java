package de.whs.holiday;

import java.util.HashMap;

import org.kie.api.KieServices;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class HolidayProcess implements ProcessEventListener {

	private DataProvider dataProvider;
	private Notifier notifier;
	private KieSession kSession;
	
	public HolidayProcess(DataProvider provider, Notifier notifier) {
		this.dataProvider = provider;
		this.notifier = notifier;
	}
	
	public void start(HashMap<String,Object> params) {
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			kSession = kContainer.newKieSession("ksession-process");
			kSession.addEventListener(this);
			
			KieRuntimeLogger logger = ks.getLoggers().newFileLogger(kSession, "workflowLog");			
			
			kSession.getWorkItemManager().registerWorkItemHandler("Human Task", new HumanTaskWorkItemHandler(dataProvider));
			kSession.getWorkItemManager().registerWorkItemHandler("Notification", new NotificationWorkItemHandler(notifier));

			// start a new process instance
			kSession.startProcess("de.whs.holiday.Urlaubsantrag", params);
			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	@Override
	public void afterNodeLeft(ProcessNodeLeftEvent arg0) {

	}

	@Override
	public void afterNodeTriggered(ProcessNodeTriggeredEvent arg0) {

	}

	@Override
	public void afterProcessCompleted(ProcessCompletedEvent arg0) {
		kSession.destroy();
	}

	@Override
	public void afterProcessStarted(ProcessStartedEvent arg0) {

	}

	@Override
	public void afterVariableChanged(ProcessVariableChangedEvent arg0) {
	
	}

	@Override
	public void beforeNodeLeft(ProcessNodeLeftEvent arg0) {

	}

	@Override
	public void beforeNodeTriggered(ProcessNodeTriggeredEvent arg0) {
	
	}

	@Override
	public void beforeProcessCompleted(ProcessCompletedEvent arg0) {

	}

	@Override
	public void beforeProcessStarted(ProcessStartedEvent arg0) {

	}

	@Override
	public void beforeVariableChanged(ProcessVariableChangedEvent arg0) {

	}
}