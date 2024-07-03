package it.polito.emergency;

public class Report {
    private String professionalId;
    private String fiscalCode; 
    private String date;
    private String description;
    private String ID;

    public String getProfessionalId() {
        return professionalId;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public String getDate() {
        return date;
    }


    public String getDescription() {
        return description;
    }

    public Report(String professionalId, String fiscalCode, String date, String description, String iD) {
        this.professionalId = professionalId;
        this.fiscalCode = fiscalCode;
        this.date = date;
        this.description = description;
        ID = iD;
    }

    public String getId() {
        return ID;
    }

    public void setId(String iD) {
        ID = iD;
    }
    
}
