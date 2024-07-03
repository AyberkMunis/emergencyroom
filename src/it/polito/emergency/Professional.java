package it.polito.emergency;

import java.util.ArrayList;

public class Professional {
    private String id; 
    private String name; 
    private String surname; 
    private String specialization; 
    private String period;
    private ArrayList<Patient>patients2=new ArrayList<Patient>();

    
    public ArrayList<Patient> getPatients2() {
        return patients2;
    }

    public void setPatients2(ArrayList<Patient> patients2) {
        this.patients2 = patients2;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }
    public boolean checking(String a){
        String[] a1=a.split(" to ");
        String[] a2=a1[0].split("-");
        String[] a3=a1[1].split("-");
        String[] period1=period.split(" to ");
        String[] period2=period1[0].split("-");
        String[] period3=period1[1].split("-");
        if((Integer.parseInt(period2[0])>=Integer.parseInt(a2[0]))&&(Integer.parseInt(period2[1])>=Integer.parseInt(a2[1]))&&(Integer.parseInt(period2[2])>=Integer.parseInt(a2[2]))){
            return true;
        }
        return false;



    }
    public boolean checking2(String a){
        String[] a1=a.split("-");
        String[] period1=period.split(" to ");
        String[] period2=period1[0].split("-");
        String[] period3=period1[1].split("-");
        if((Integer.parseInt(period2[0])<=Integer.parseInt(a1[0]))&&(Integer.parseInt(period2[1])<=Integer.parseInt(a1[1]))&&(Integer.parseInt(period2[2])<=Integer.parseInt(a1[2]))){
            return true;
        }
        return false;



    }

    public Professional(String id, String name, String surname, String specialization, String period) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.specialization = specialization;
		this.period = period;
	}

	public String getSpecialization() {
        return this.specialization;
    }

    public String getPeriod() {
        return this.period;
    }

}
