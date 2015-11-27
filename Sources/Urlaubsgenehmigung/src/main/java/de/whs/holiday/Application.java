package de.whs.holiday;

public class Application {
	private String applicant;
	private int days;
	private boolean isDenied;
	private String advisor;
	private Notification notification;
	private String holidaytype;

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
		this.isDenied = isDenied;
	}

	@Override
	public String toString() {
		return String.format("Applicant: %s Days: %s isDenied: %s", applicant,
				days, isDenied);
	}

	public String getSuperiorOfApplicant() {
//		if (applicant.equals("detuwas"))
//			return "dechef";
//		if (applicant.equals("antje"))
//			return "ennel";
//
//		if (applicant.equals("dechef") || applicant.equals("ennel")
//				|| applicant.equals("stenno"))
//			return "verkauf";
//
//		if (applicant.equals("urlaub") || applicant.equals("machfrei"))
//			return "stellein";
//
//		if (applicant.equals("stellein") || applicant.equals("verkauf")
//				|| applicant.equals("auchda") || applicant.equals("vizeboss"))
//			return "oberboss";
//
//		throw new RuntimeException("Cannot resolve superior of " + applicant);
		
		 Superiors s = new Superiors();
	        if (applicant.equals("detuwas")) {
	            s.superior = "dechef";
	            s.coSuperior = "verkauf";
	        } 
	        else if (applicant.equals("antje")) {
	            s.superior = "ennel";
	            s.coSuperior = "verkauf";
	        }
	        else if (applicant.equals("dechef") || applicant.equals("ennel") || applicant.equals("stenno")) {
	            s.superior= "verkauf";
	            s.coSuperior = "oberboss";
	        }
	        else if (applicant.equals("urlaub") || applicant.equals("machfrei")) {
	            s.superior= "stellein";
	            s.coSuperior = "oberboss";
	        } 
	        else if (applicant.equals("stellein") || applicant.equals("verkauf") ||
	                applicant.equals("auchda") || applicant.equals("vizeboss")) {
	            s.superior = "oberboss";
	            s.coSuperior = "vizeboss";
	        }
	        else if (applicant.equals("oberboss")) {
	            s.superior = "vizeboss";
	            s.coSuperior = "oberboss";
	        }
	        else
	            throw new RuntimeException("Cannot resolve superior of " + applicant);
	        
	        return s.superior;
		
	}

	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	public boolean checkDaycountForExtraHoliday() {
		if (holidaytype.equals("umzug") && days > 1) {
			notification.setMessage("Max. 1 Tag bei Umzug");
			isDenied = true;
		} else if (holidaytype.equals("geburtTodesfall") && days > 2) {
			notification.setMessage("Max 2. Tage bei Geburts - oder Tadesfall");
			isDenied = true;
		} else
			isDenied = false;

		return isDenied;
	}
	
	public boolean applicantIsBoardMember(){
		return applicant.equals("oberboss") || applicant.equals("vizeboss") || applicant.equalsIgnoreCase("auchda");
	}
	
	private class Superiors {
        public String superior;
        public String coSuperior;
    }
    
    private Superiors getSuperior(String applicant) {
        Superiors s = new Superiors();
        if (applicant.equals("detuwas")) {
            s.superior = "dechef";
            s.coSuperior = "verkauf";
        } 
        else if (applicant.equals("antje")) {
            s.superior = "ennel";
            s.coSuperior = "verkauf";
        }
        else if (applicant.equals("dechef") || applicant.equals("ennel") || applicant.equals("stenno")) {
            s.superior= "verkauf";
            s.coSuperior = "oberboss";
        }
        else if (applicant.equals("urlaub") || applicant.equals("machfrei")) {
            s.superior= "stellein";
            s.coSuperior = "oberboss";
        } 
        else if (applicant.equals("stellein") || applicant.equals("verkauf") ||
                applicant.equals("auchda") || applicant.equals("vizeboss")) {
            s.superior = "oberboss";
            s.coSuperior = "vizeboss";
        }
        else if (applicant.equals("oberboss")) {
            s.superior = "vizeboss";
            s.coSuperior = "oberboss";
        }
        else
            throw new RuntimeException("Cannot resolve superior of " + applicant);
        
        return s;
    }
}
