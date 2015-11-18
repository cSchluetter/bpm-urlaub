package de.whs.holiday;

public class Application {
	private String applicant;
	private int days;
	private boolean isDenied;
	
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
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
		this.isDenied = isDenied;
	}
	
	@Override
	public String toString() {
		return String.format("Applicant: %s Days: %s isDenied: %s", applicant, days, isDenied);
	}
}
