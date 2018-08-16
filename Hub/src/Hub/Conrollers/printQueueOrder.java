package Hub.Conrollers;

public class printQueueOrder {

    private String LastName;
    private String FirstName;
    private String Email;

    public printQueueOrder() {
        this.LastName = null;
        this.FirstName = null;
        this.Email = null;
    }

    public printQueueOrder(String last, String first, String email) {
        this.LastName = last;
        this.FirstName = first;
        this.Email = email;
    }

    public void setLastName(String str) {
        this.LastName = str;
    }

    public void setFirstName(String str) {
        this.FirstName = str;
    }

    public void setEmail(String str) {
        this.Email = str;
    }

    public String getLastName() {
        return this.LastName;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public String getEmail() {
        return this.Email;
    }


}
