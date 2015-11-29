package de.whs.holiday.data;


public class Application {
	private String applicant;
	private int days;
	private boolean isDenied;
	private String advisor;
	private String superior;
	private String cosuperior;
	private Notification notification;
	private String holidaytype;
	
	public String getSuperior() {
		return superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public String getCosuperior() {
		return cosuperior;
	}

	public void setCosuperior(String cosuperior) {
		this.cosuperior = cosuperior;
	}

	public String getHolidaytype() {
		return holidaytype.toString();
	}

	public void setHolidaytype(String holidaytype) {
		this.holidaytype = holidaytype;
	}

	public Application() {
		this.notification = new Notification();
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
		this.notification.setTo(applicant);
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public boolean isDenied() {
		return isDenied;
	}

	public void setDenied(boolean isDenied) {
		this.isDenied = this.isDenied | isDenied;
	}

	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	@Override
	public String toString() {
		return String.format("Applicant: %s Days: %s isDenied: %s", applicant, days, isDenied);
	}
		
}
