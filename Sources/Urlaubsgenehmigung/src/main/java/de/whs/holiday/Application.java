package de.whs.holiday;

public class Application {
	private String applicant;
	private int days;
	private boolean isDenied;
	private String advisor;
		
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
	
	public String getSuperiorOfApplicant() {
		if (applicant.equals("detuwas")) return "dechef";
		if (applicant.equals("antje")) return "ennel";
		
		if (applicant.equals("dechef") || applicant.equals("ennel") || applicant.equals("stenno"))
			return "verkauf";
		
		if (applicant.equals("urlaub") || applicant.equals("machfrei"))
			return "stellein";
		
		if (applicant.equals("stellein") || applicant.equals("verkauf") ||
				applicant.equals("auchda") || applicant.equals("vizeboss"))
			 return "oberboss";
		
		throw new RuntimeException("Cannot resolve superior of " + applicant);
	}
	public String getAdvisor() {
		return advisor;
	}
	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}
}
