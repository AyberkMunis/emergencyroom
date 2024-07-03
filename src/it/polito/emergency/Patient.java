package it.polito.emergency;

import it.polito.emergency.EmergencyApp.*;

public class Patient {


    private String fiscalCode;
    private String name;
    private String surname;
    private String dateofbirth;
    private PatientStatus status;
    private String reason; 
    private String dateTimeAccepted;
    private Professional prof;

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public Professional getProf() {
        return prof;
    }

    public void setProf(Professional prof) {
        this.prof = prof;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateOfbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    public String getFiscalCode() {
        return this.fiscalCode;
    }

    public String getName() {
        return this.name;
    }



	public Patient(String fiscalCode, String name, String surname, String dateofbirth,
            String reason, String dateTimeAccepted,PatientStatus status) {
        this.fiscalCode = fiscalCode;
        this.name = name;
        this.surname = surname;
        this.dateofbirth = dateofbirth;
        this.status = status;
        this.reason = reason;
        this.dateTimeAccepted = dateTimeAccepted;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getDateOfBirth() {
        return this.dateofbirth;
    }

    public String getReason() {
        return this.reason;
    }

    public String getDateTimeAccepted() {
        return this.dateTimeAccepted;
    }

    public PatientStatus getStatus() {
        return this.status;
    }
}
