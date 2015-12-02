package de.whs.holiday.console;

import de.whs.holiday.Notifier;

public class ConsoleNotifierImpl implements Notifier {

	@Override
	public void sendNotification(String title, String text) {
		Console.writeLine(title);
		Console.writeLine(text);
	}

}
