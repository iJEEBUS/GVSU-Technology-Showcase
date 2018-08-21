package Hub.Conrollers;

import java.sql.Time;
import java.sql.Timestamp;

public class rentalQueueOrder {

    private String LastName;
    private String FirstName;
    private String Email;
    private String Technology;
    private String AdditionalComponents;
    private String Comments;
    private Timestamp ReturnDate;
    private Boolean Signature;
    private Timestamp SubmissionTime;

    // Constructors
    public rentalQueueOrder() {
        this.LastName = null;
        this.FirstName = null;
        this.Email = null;
        this.Technology = null;
        this.ReturnDate = null;
        this.AdditionalComponents = null;
        this.Comments = null;
    }
    public rentalQueueOrder(String last,
                            String first,
                            String email,
                            String tech,
                            String comps,
                            String cmts,
                            Timestamp return_date,
                            Boolean sign,
                            Timestamp ts) {
        LastName = last;
        FirstName = first;
        Email = email;
        Technology = tech;

        AdditionalComponents = comps;
        Comments = cmts;
        ReturnDate = return_date;
        Signature = sign;
        SubmissionTime = ts;
    }

    // Setters
    public void setLastName(String str) { LastName = str; }
    public void setFirstName(String str) { FirstName = str; }
    public void setEmail(String str) { Email = str; }
    public void setTechnology(String technology) { Technology = technology; }
    public void setAdditionalComponents(String comps) { AdditionalComponents = comps; }
    public void setComments(String comments) { Comments = comments; }
    public void setReturnDate(Timestamp returnDate) { ReturnDate = returnDate; }
    public void setSignature(Boolean signature) { Signature = signature; }
    public void setSubmissionTime(Timestamp submissionTime) { SubmissionTime = submissionTime; }

    // Getters
    public String getLastName() { return LastName; }
    public String getFirstName() { return FirstName; }
    public String getEmail() { return Email; }
    public String getTechnology() { return Technology; }
    public String getAdditionalComponents() { return AdditionalComponents; }
    public String getComments() { return Comments; }
    public Timestamp getReturnDate() { return ReturnDate; }
    public Boolean getSignature() { return Signature; }
    public Timestamp getSubmissionTime() { return SubmissionTime; }
}
