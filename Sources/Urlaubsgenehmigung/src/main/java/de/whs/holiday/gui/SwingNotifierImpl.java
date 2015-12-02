package de.whs.holiday.gui;

import de.whs.holiday.Notifier;

public class SwingNotifierImpl implements Notifier {

	@Override
	public void sendNotification(String title, String text) {
		NotificationDialog.start(title, text);
	}

}
