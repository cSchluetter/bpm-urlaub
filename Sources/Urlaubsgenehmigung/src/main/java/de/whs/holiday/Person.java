package de.whs.holiday;

public class Person {
	private String name;
	private int anzahlUrlaubsTage;
	
	private String genehmiger1;
	private String genehmiger2;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAnzahlUrlaubsTage() {
		return anzahlUrlaubsTage;
	}
	public void setAnzahlUrlaubsTage(int anzahlUrlaubsTage) {
		this.anzahlUrlaubsTage = anzahlUrlaubsTage;
	}
	public String getGenehmiger1() {
		return genehmiger1;
	}
	public void setGenehmiger1(String genehmiger1) {
		this.genehmiger1 = genehmiger1;
	}
	public String getGenehmiger2() {
		return genehmiger2;
	}
	public void setGenehmiger2(String genehmiger2) {
		this.genehmiger2 = genehmiger2;
	}
}
