import java.util.HashMap;

import org.junit.Test;

import de.whs.holiday.DataProvider;
import de.whs.holiday.HolidayProcess;
import de.whs.holiday.Service;
import de.whs.holiday.ServiceImpl;
import de.whs.holiday.data.Application;


public class HolidayProcessTest {

	@Test
	public void test() {
		
		DataProvider fakeProvider = new DataProvider() {
			
			@Override
			public Application getApplication() {
				Application app = new Application();
				app.setApplicant("ennel");
				app.setDays(12);
				app.setDenied(false);
				return app;
			}
			
			@Override
			public Application checkForSuperiorApprovement(Application app) {
				app.setDenied(true);
				return app;
			}

			@Override
			public Application chechForHrApprovement(Application app) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Application checkForAvailableDays(Application app) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Application checkForCoSuperiorApprovment(Application app) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		
		Service infoService = new ServiceImpl();
		Application app = fakeProvider.getApplication();		
		app.setSuperior(infoService.getSuperior(app.getApplicant()));
		app.setCosuperior(infoService.getCoSuperior(app.getApplicant()));
		app.setAdvisor(infoService.getHrAdvisor(app.getApplicant()));
			
		HashMap<String, Object> params= new HashMap<String, Object>();
		params.put("application", app);
		
		HolidayProcess target = new HolidayProcess(fakeProvider);
		target.start(params);
	}

}
