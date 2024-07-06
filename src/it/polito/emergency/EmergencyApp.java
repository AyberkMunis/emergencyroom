package it.polito.emergency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EmergencyApp {

    public enum PatientStatus {
        ADMITTED,
        DISCHARGED,
        HOSPITALIZED
    }
    
    /**
     * Add a professional working in the emergency room
     * 
     * @param id
     * @param name
     * @param surname
     * @param specialization
     * @param period
     * @param workingHours
     */
    private HashMap<String,Professional> profs=new HashMap<String,Professional>();
    public HashMap<String,Department> deps=new HashMap<String,Department>();
    private SortedMap<String,Patient> patients=new TreeMap<String,Patient>();
    private HashMap<String,Report> reports=new HashMap<String,Report>();
    private HashMap<Patient,Professional> p2prof=new HashMap<Patient,Professional>();
    char[] chars={};
    private Integer rid=1;
    public boolean fiscal(String s){
        int a=0;
        for(int i=0;i<s.length();i++){
            if(Character.isDigit(s.charAt(i))){
                a+=1;
            }
            

        }
        if(a==0){
            return false;
        }
        else{
            return true;
        }
    }
    public void addProfessional(String id, String name, String surname, String specialization, String period) {
        //TODO: to be implemented
        profs.put(id,new Professional(id, name, surname, specialization, period));
    }

    /**
     * Retrieves a professional utilizing the ID.
     *
     * @param id The id of the professional.
     * @return A Professional.
     * @throws EmergencyException If no professional is found.
     */    
    public Professional getProfessionalById(String id) throws EmergencyException {
        if(!profs.containsKey(id)){
            throw new EmergencyException("Id not found");
        }
        return profs.get(id);
    }

    /**
     * Retrieves the list of professional IDs by their specialization.
     *
     * @param specialization The specialization to search for among the professionals.
     * @return A list of professional IDs who match the given specialization.
     * @throws EmergencyException If no professionals are found with the specified specialization.
     */    
    public List<String> getProfessionals(String specialization) throws EmergencyException {
        //TODO: to be implemented
        if(profs.values().stream().filter(c->c.getSpecialization().equals(specialization)).map(Professional::getId).collect(Collectors.toList()).size()==0){
            throw new EmergencyException("No doctor Found");
        }
        return profs.values().stream().filter(c->c.getSpecialization().equals(specialization)).map(Professional::getId).collect(Collectors.toList());
    }

    /**
     * Retrieves the list of professional IDs who are specialized and available during a given period.
     *
     * @param specialization The specialization to search for among the professionals.
     * @param period The period during which the professional should be available, formatted as "YYYY-MM-DD to YYYY-MM-DD".
     * @return A list of professional IDs who match the given specialization and are available during the period.
     * @throws EmergencyException If no professionals are found with the specified specialization and period.
     */    
    public List<String> getProfessionalsInService(String specialization, String period) throws EmergencyException {
        //TODO: to be implemented

        if(profs.values().stream().filter(c->c.checking(period)&&c.getSpecialization().equals(specialization)).collect(Collectors.toList()).size()==0){
            throw new EmergencyException("No doctor Found");
        }

      
        return profs.values().stream().filter(c->c.checking(period)&&c.getSpecialization().equals(specialization)).map(Professional::getId).collect(Collectors.toList());
    }

    /**
     * Adds a new department to the emergency system if it does not already exist.
     *
     * @param name The name of the department.
     * @param maxPatients The maximum number of patients that the department can handle.
     * @throws EmergencyException If the department already exists.
     */
    public void addDepartment(String name, int maxPatients){
        if(!deps.keySet().stream().filter(c->c.equals(name)).findAny().isPresent()){
            deps.put(name,new Department(name, maxPatients));
        }
        
       

        //TODO: to be implemented
    }

    /**
     * Retrieves a list of all department names in the emergency system.
     *
     * @return A list containing the names of all registered departments.
     * @throws EmergencyException If no departments are found.
     */
    public List<String> getDepartments() throws EmergencyException {
        //TODO: to be implemented
        if(deps.size()==0){
            throw new EmergencyException("No department is found");
        }
        return deps.values().stream().map(Department::getName).collect(Collectors.toList());
    }

    /**
     * Reads professional data from a CSV file and stores it in the application.
     * Each line of the CSV should contain a professional's ID, name, surname, specialization, period of availability, and working hours.
     * The expected format of each line is: matricola, nome, cognome, specializzazione, period, orari_lavoro
     * 
     * @param reader The reader used to read the CSV file. Must not be null.
     * @return The number of professionals successfully read and stored from the file.
     * @throws IOException If there is an error reading from the file or if the reader is null.
     */
    public int readFromFileProfessionals(Reader reader) throws IOException {
        if(reader==null){
            throw new IOException();
        }
        int intValueOfChar;
        String targetString = "";
        while ((intValueOfChar = reader.read()) != -1) {
            targetString += (char) intValueOfChar;
        }
        reader.close();
        String[] spString=targetString.split("\n");
        for(int i=1;i<spString.length;i++){
            String[] prof=spString[i].split(",");
        addProfessional(prof[0],prof[1], prof[2], prof[3],prof[4]);

        }
        


	

        return spString.length-1;
    }

    /**
     * Reads department data from a CSV file and stores it in the application.
     * Each line of the CSV should contain a department's name and the maximum number of patients it can accommodate.
     * The expected format of each line is: nome_reparto, num_max
     * 
     * @param reader The reader used to read the CSV file. Must not be null.
     * @return The number of departments successfully read and stored from the file.
     * @throws IOException If there is an error reading from the file or if the reader is null.
     */    
    public int readFromFileDepartments(Reader reader) throws IOException {
        //TODO: to be implemented
        if(reader==null){
            throw new IOException();
        }
        int intValueOfChar;
        String targetString = "";
        while ((intValueOfChar = reader.read()) != -1) {
            targetString += (char) intValueOfChar;
        }
        reader.close();
        String[] spString=targetString.split("\n");
        for(int i=1;i<spString.length;i++){
            String[] d=spString[i].split(",");
        addDepartment(d[0], Integer.parseInt(d[1]) );

        }
        


	

        return spString.length-1;
    }

    /**
     * Registers a new patient in the emergency system if they do not exist.
     * 
     * @param fiscalCode The fiscal code of the patient, used as a unique identifier.
     * @param name The first name of the patient.
     * @param surname The surname of the patient.
     * @param dateOfBirth The birth date of the patient.
     * @param reason The reason for the patient's visit.
     * @param dateTimeAccepted The date and time the patient was accepted into the emergency system.
     */
    public Patient addPatient(String fiscalCode, String name, String surname, String dateOfBirth, String reason, String dateTimeAccepted) {
        patients.put(fiscalCode,new Patient(fiscalCode, name, surname, dateOfBirth,reason,dateTimeAccepted,PatientStatus.ADMITTED));
        return patients.get(fiscalCode);
    }

    /**
     * Retrieves a patient or patients based on a fiscal code or surname.
     *
     * @param identifier Either the fiscal code or the surname of the patient(s).
     * @return A single patient if a fiscal code is provided, or a list of patients if a surname is provided.
     *         Returns an empty collection if no match is found.
     */    
    public List<Patient> getPatient(String identifier) throws EmergencyException {
        //TODO: to be implemented
        if(!fiscal(identifier)){
            return patients.values().stream().filter(p->p.getSurname().equals(identifier)).collect(Collectors.toList());
        }

        return patients.values().stream().filter(c->c.getFiscalCode().equals(identifier)).collect(Collectors.toList());
    }

    /**
     * Retrieves the fiscal codes of patients accepted on a specific date, 
     * sorted by acceptance time in descending order.
     *
     * @param date The date of acceptance to filter the patients by, expected in the format "yyyy-MM-dd".
     * @return A list of patient fiscal codes who were accepted on the given date, sorted from the most recent.
     *         Returns an empty list if no patients were accepted on that date.
     */
    public List<String> getPatientsByDate(String date) {
        //TODO: to be implemented
        return patients.values().stream().filter(p->p.getDateTimeAccepted().equals(date)).map(Patient::getFiscalCode).collect(Collectors.toList());
    }

    /**
     * Assigns a patient to a professional based on the required specialization and checks availability during the request period.
     *
     * @param fiscalCode The fiscal code of the patient.
     * @param specialization The required specialization of the professional.
     * @return The ID of the assigned professional.
     * @throws EmergencyException If the patient does not exist, if no professionals with the required specialization are found, or if none are available during the period of the request.
     */
    public String assignPatientToProfessional(String fiscalCode, String specialization) throws EmergencyException {
        if(!patients.containsKey(fiscalCode)||!profs.values().stream().filter(c->c.checking2(patients.get(fiscalCode).getDateTimeAccepted())).findAny().isPresent()||profs.values().stream().filter(c->c.getSpecialization().equals(specialization)).collect(Collectors.toList()).size()==0){
         throw new EmergencyException();
    }
    Professional p1=profs.values().stream().filter(c->c.checking2(patients.get(fiscalCode).getDateTimeAccepted())&&c.getSpecialization().equals(specialization)).findFirst().get();
    p1.getPatients2().add(patients.get(fiscalCode));
    patients.get(fiscalCode).setProf(p1);
    p2prof.put(patients.get(fiscalCode), p1);
        return p1.getId();
    }

    public Report saveReport(String professionalId, String fiscalCode, String date, String description) throws EmergencyException {
        if(!profs.containsKey(professionalId)){
            throw new EmergencyException();
        }
        Report r=new Report(professionalId, fiscalCode, date, description, String.valueOf(rid));
        reports.put(String.valueOf(rid), r);
        rid+=1;
        
        return r;
    }

    /**
     * Either discharges a patient or hospitalizes them depending on the availability of space in the requested department.
     * 
     * @param fiscalCode The fiscal code of the patient to be discharged or hospitalized.
     * @param departmentName The name of the department to which the patient might be admitted.
     * @throws EmergencyException If the patient does not exist or if the department does not exist.
     */
    public void dischargeOrHospitalize(String fiscalCode, String departmentName) throws EmergencyException {

        if(!patients.containsKey(fiscalCode)||!deps.containsKey(departmentName)){
            throw new EmergencyException();
        }
        
        if(deps.get(departmentName).getPatients()==deps.get(departmentName).getMaxPatients()){
            patients.get(fiscalCode).setStatus(PatientStatus.DISCHARGED);
        }
        else{
            patients.get(fiscalCode).setStatus(PatientStatus.HOSPITALIZED);
            deps.get(departmentName).setPatients();

           
        }
    }

    /**
     * Checks if a patient is currently hospitalized in any department.
     *
     * @param fiscalCode The fiscal code of the patient to verify.
     * @return 0 if the patient is currently hospitalized, -1 if not hospitalized or discharged.
     * @throws EmergencyException If no patient is found with the given fiscal code.
     */
    public int verifyPatient(String fiscalCode) throws EmergencyException{
        //TODO: to be implemented
        if(!patients.containsKey(fiscalCode)){
            throw new EmergencyException();
        }
        if(patients.get(fiscalCode).getStatus()==PatientStatus.DISCHARGED){
            return 0;
        }else if(patients.get(fiscalCode).getStatus()==PatientStatus.HOSPITALIZED){
            return 1;
        }
        return -1;
    }

    /**
     * Returns the number of patients currently being managed in the emergency room.
     *
     * @return The total number of patients in the system.
     */    
    public int getNumberOfPatients() {
        //TODO: to be implemented
        return (int) patients.values().stream().filter(c->c.getStatus()==PatientStatus.ADMITTED).count();
    }

    /**
     * Returns the number of patients admitted on a specified date.
     *
     * @param dateString The date of interest provided as a String (format "yyyy-MM-dd").
     * @return The count of patients admitted on that date.
     */
    public int getNumberOfPatientsByDate(String date) {
        //TODO: to be implemented
        return (int) patients.values().stream().filter(c->c.getDateTimeAccepted().equals(date)).count();
    }

    public int getNumberOfPatientsHospitalizedByDepartment(String departmentName) throws EmergencyException {
        //TODO: to be implemented
        if(!deps.containsKey(departmentName)){
            throw new EmergencyException();
        }
        return (int) deps.get(departmentName).getPatients();
    }

    /**
     * Returns the number of patients who have been discharged from the emergency system.
     *
     * @return The count of discharged patients.
     */
    public int getNumberOfPatientsDischarged() {
        //TODO: to be implemented
        return (int) patients.values().stream().filter(c->c.getStatus()==PatientStatus.DISCHARGED).count();
    }

    /**
     * Returns the number of discharged patients who were treated by professionals of a specific specialization.
     *
     * @param specialization The specialization of the professionals to filter by.
     * @return The count of discharged patients treated by professionals of the given specialization.
     */
    public int getNumberOfPatientsAssignedToProfessionalDischarged(String specialization) {
        //TODO: to be implemented
        return (int)p2prof.keySet().stream().filter(c->c.getStatus()==PatientStatus.DISCHARGED&&c.getProf().getSpecialization().equals(specialization)).count();
    }
}
