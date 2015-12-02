package de.whs.holiday.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import de.whs.holiday.UserInterface;
import de.whs.holiday.data.Application;
import de.whs.holiday.data.Notification;


public class SwingUserInterfaceImpl implements UserInterface
{	
	private JDialog superiorDialog;
	
	@Override
	public void getApplication(ApplicationActionListener callback) {
		StartDialog.start(callback);
	}

	//superior approve
	@Override
	public void checkForSuperiorApprovement(Application app , ApplicationActionListener callback) {
		Notification denyNotification = new Notification();
		denyNotification.setFrom(app.getSuperior());
		denyNotification.setMessage("Vorgesetzer hat abgelehnt");
		
		superiorDialog = approve(app, callback,app.getSuperior(), denyNotification);		
	}

	//head of hr approve
	@Override
	public void checkForHrApprovement(Application app, ApplicationActionListener callback) {
		Notification denyNotification = new Notification();
		denyNotification.setFrom("Personalleiter");
		denyNotification.setMessage("Personalleiter hat abgelehnt");

		approve(app, callback,"stellein", denyNotification);
	}

	//Advisor approve
	@Override
	public void checkForAvailableDays(Application app, ApplicationActionListener callback) {
		Notification denyNotification = new Notification();
		denyNotification.setFrom(app.getAdvisor());
		denyNotification.setMessage("Personal Sachbearbeiter hat abgelehnt");
		
		approve(app, callback,app.getAdvisor(), denyNotification);	
	}

	//co superior approve
	@Override
	public void checkForCoSuperiorApprovment(Application app, ApplicationActionListener callback) {
		if (superiorDialog != null)
			superiorDialog.dispose();
		
		Notification denyNotification = new Notification();
		denyNotification.setFrom(app.getCosuperior());
		denyNotification.setMessage("Stellvertreter-Vorgesetzer hat abgelehnt");
		
		approve(app, callback,app.getCosuperior(), denyNotification);	
	}
	
	private JDialog approve(final Application app, final ApplicationActionListener callback,String approver, final Notification denyNotification){
		String title = String.format("Urlaubsantrag - was tun, %s?", approver);
		String text = String.format("%s möchte %s Tag(e) Urlaub - Typ: %s", app.getApplicant(), app.getDays(), app.getHolidaytype());
		JDialog dialog = ApproveDialog.start(title,text, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				switch (action) {
				case "approved":
					app.setDenied(false);
					break;

				case "denied":
					app.setDenied(true);
					break;
				}	
				
				if (app.isDenied()){
					app.getNotification().setMessage(denyNotification.getMessage());
					app.getNotification().setFrom(denyNotification.getFrom());
				}
				callback.actionPerformed(app);
			}
		});		
		return dialog;
	}
}