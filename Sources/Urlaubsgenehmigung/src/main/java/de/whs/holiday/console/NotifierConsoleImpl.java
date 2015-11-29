package de.whs.holiday.console;

import de.whs.holiday.Notifier;

public class NotifierConsoleImpl implements Notifier {

	@Override
	public void sendNotification(String title, String text) {
		Console.writeLine(title);
		Console.writeLine(text);
	}

}
