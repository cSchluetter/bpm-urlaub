import org.junit.Test;

import de.whs.holiday.Application;
import de.whs.holiday.DataProvider;
import de.whs.holiday.HolidayProcess;


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
			public Application checkForApprovement(Application app) {
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
		};
		
		HolidayProcess target = new HolidayProcess(fakeProvider);
		target.start();
	}

}
