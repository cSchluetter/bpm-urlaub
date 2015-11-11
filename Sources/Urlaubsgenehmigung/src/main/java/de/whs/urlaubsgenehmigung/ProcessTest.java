package de.whs.urlaubsgenehmigung;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample file to launch a process.
 */
public class ProcessTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-process");
        	
        	HumanTaskWorkItemHandler handler = new HumanTaskWorkItemHandler();
        	kSession.getWorkItemManager().registerWorkItemHandler("Human Task", handler);
        	
        	Person p = new Person();
    		p.setName("Fr. Antje");
    		p.setAnzahlUrlaubsTage(5);
    		p.setGenehmiger1("Fr. Ennel");
    		p.setGenehmiger2("Fr. Verkauf");

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("person", p);
    		
            // start a new process instance
            kSession.startProcess("de.whs.urlaubsgenehmigung.Urlaubsantrag", params);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
