package de.whs.holiday;


public class HumanResourceServiceImpl implements HumanResourceService {

	@Override
	public String getSuperior(String applicant) {
		return getSuperiors(applicant).superior;
	}

	@Override
	public String getCoSuperior(String applicant) {
		return getSuperiors(applicant).coSuperior;
	}

	@Override
	public String getHrAdvisor(String applicant) {
		if (applicant.toUpperCase().charAt(0) < 'L')
			return "machfrei";
		else
			return "urlaub";
	}

	private class Superiors {
        public String superior;
        public String coSuperior;
    }
    
    private Superiors getSuperiors(String applicant) {
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
