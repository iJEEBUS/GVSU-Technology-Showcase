package Hub.Conrollers;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Time;
import java.sql.Timestamp;

public class printQueueOrder {

    private String LastName;
    private String FirstName;
    private String Email;
    private String PrintOne;
    private String PrintTwo;
    private String PrintThree;
    private String PrintFour;

    private Boolean ForClass;
    private String ClassName;
    private String ClassProof;
    private Boolean TermsAndConditions;

    private Timestamp SubmissionTime;


    /**
     * Constructor for a null print order
     */
    public printQueueOrder() {
        this.LastName = null;
        this.FirstName = null;
        this.Email = null;
        this.PrintOne = null;
        this.PrintTwo = null;
        this.PrintFour = null;
        this.SubmissionTime = null;

    }

    /**
     * Constructor used to create a new print order to submit to the queue.
     *
     * @param last - String - clients first name
     * @param first- String - clients last name
     * @param email- String - clients email
     * @param p1 - String - location of the 1st print file
     * @param p2 - String - location of the 2nd print file
     * @param p3 - String - location of the 3rd print file
     * @param p4 - String - location of the 4th print file
     */
    public printQueueOrder(String last,
                           String first,
                           String email,
                           String p1,
                           String p2,
                           String p3,
                           String p4,
                           Boolean for_class,
                           String class_name,
                           String class_proof,
                           Boolean t_and_c,
                           Timestamp time) {
        LastName = last;
        FirstName = first;
        Email = email;
        PrintOne = p1;
        PrintTwo = p2;
        PrintThree = p3;
        PrintFour = p4;
        ForClass = for_class;
        ClassName = class_name;
        ClassProof = class_proof;
        TermsAndConditions = t_and_c;
        SubmissionTime = time;
    }


    // Setters
    public void setFirstName(String str) {
        FirstName = str;
    }
    public void setLastName(String str) {
        LastName = str;
    }
    public void setEmail(String str) {
        Email = str;
    }
    public void setPrintOne(String str) { PrintOne = str; }
    public void setPrintTwo(String str) { PrintTwo = str; }
    public void setPrintThree(String str) { PrintThree = str; }
    public void setPrintFour(String str) { PrintFour = str; }
    public void setForClass(Boolean forClass) { ForClass = forClass; }

    public void setClassName(String className) { ClassName = className; }
    public void setClassProof(String classProof) { ClassProof = classProof; }
    public void setTermsAndConditions(Boolean termsAndConditions) { TermsAndConditions = termsAndConditions; }

    public void setSubmissionTime(Timestamp ts) { this.SubmissionTime = ts; }

    // Getters
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public String getEmail() {
        return Email;
    }
    public String getPrintOne() { return PrintOne; }
    public String getPrintTwo() { return PrintTwo; }
    public String getPrintThree() { return PrintThree; }
    public String getPrintFour() { return PrintFour; }
    public Boolean getForClass() { return ForClass; }
    public String getClassName() { return ClassName; }
    public String getClassProof() { return ClassProof; }
    public Boolean getTermsAndConditions() { return TermsAndConditions; }
    public Timestamp getSubmissionTime() { return SubmissionTime; }
}
