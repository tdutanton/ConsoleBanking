package Customer;

public class Customer {
    private final Integer id;
    private final String fullName;

    public Customer (Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Integer getId() {return this.id;}
    public String getFullName() {return fullName;}
}
