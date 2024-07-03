package it.polito.emergency;

public class Department {
    private String name; 
    private int maxPatients;
	private int patients=0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxPatients() {
		return maxPatients;
	}
	public void setMaxPatients(int maxPatients) {
		this.maxPatients = maxPatients;
	}
	public Department(String name, int maxPatients) {
		this.name = name;
		this.maxPatients = maxPatients;
	}
	public int getPatients() {
		return patients;
	}
	public void setPatients() {
		this.patients = patients+1;
	}
}
