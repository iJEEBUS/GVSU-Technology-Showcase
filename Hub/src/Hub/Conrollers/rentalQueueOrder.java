package Hub.Conrollers;

public class rentalQueueOrder {

    private String LastName;
    private String FirstName;
    private String Email;
    private String Technology;
    private String ReturnDate;
    private String AdditionalComponents;
    private String Comments;

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
    public rentalQueueOrder(String last, String first, String email, String tech, String return_date, String comps, String comments) {
        this.LastName = last;
        this.FirstName = first;
        this.Email = email;
        this.Technology = tech;
        this.ReturnDate = return_date;
        this.AdditionalComponents = comps;
        this.Comments = comments;
    }

    // Setters
    public void setLastName(String str) { this.LastName = str; }
    public void setFirstName(String str) { this.FirstName = str; }
    public void setEmail(String str) { this.Email = str; }
    public void setTechnology(String technology) { this.Technology = technology; }
    public void setReturnDate(String returnDate) { this.ReturnDate = returnDate; }
    public void setAdditionalComponents(String comps) {this.AdditionalComponents = comps; }
    public void setComments(String comments) { this.Comments = comments; }

    // Getters
    public String getLastName() { return this.LastName; }
    public String getFirstName() { return this.FirstName; }
    public String getEmail() { return this.Email; }
    public String getTechnology() { return this.Technology; }
    public String getReturnDate() { return ReturnDate; }
    public String getAdditionalComponents() { return this.AdditionalComponents; }
    public String getComments() { return Comments; }
}
