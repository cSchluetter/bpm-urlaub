package de.whs.holiday;

import java.util.HashMap;

import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample file to launch a process.
 */
public class HolidayProcess {

	private DataProvider dataProvider;
	
	public HolidayProcess(DataProvider provider) {
		this.dataProvider = provider;
	}
	
	public void start(HashMap<String,Object> params) {
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-process");

			KieRuntimeLogger logger = ks.getLoggers().newFileLogger(kSession, "workflowLog");
			
			
			kSession.getWorkItemManager().registerWorkItemHandler("Human Task", new HumanTaskWorkItemHandler(dataProvider));
			kSession.getWorkItemManager().registerWorkItemHandler("Notification", new NotificationWorkItemHandler());

			

			// start a new process instance
			kSession.startProcess("de.whs.holiday.Urlaubsantrag", params);
			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}