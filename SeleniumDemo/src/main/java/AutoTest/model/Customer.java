package Autotest.model;

public class Customer {

  private String customerId;
  private String customerName;
  private String gender;
  private String dateOfBirth;
  private String address;
  private String city;
  private String state;
  private String pin;
  private String telephone;
  private String email;
  private String password;

  public Customer(String customerId, String customerName, String gender, String dateOfBirth,
                  String address, String city, String state, String pin, String telephone, String email,
                  String password) {
    this.customerId = customerId;
    this.customerName = customerName;
    this.gender = gender;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
    this.city = city;
    this.state = state;
    this.pin = pin;
    this.telephone = telephone;
    this.email = email;
    this.password = password;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static final class Builder {

    private String customerId;
    private String customerName;
    private String gender;
    private String dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String telephone;
    private String email;
    private String password;

    private Builder() {}

    public static Builder aCustomer() {
      return new Builder();
    }

    public Builder withCustomerId(String customerId) {
      this.customerId = customerId;
      return this;
    }

    public Builder withCustomerName(String customerName) {
      this.customerName = customerName;
      return this;
    }

    public Builder withGender(String gender) {
      this.gender = gender;
      return this;
    }

    public Builder withDateOfBirth(String dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
      return this;
    }

    public Builder withAddress(String address) {
      this.address = address;
      return this;
    }

    public Builder withCity(String city) {
      this.city = city;
      return this;
    }

    public Builder withState(String state) {
      this.state = state;
      return this;
    }

    public Builder withPin(String pin) {
      this.pin = pin;
      return this;
    }

    public Builder withTelephone(String telephone) {
      this.telephone = telephone;
      return this;
    }

    public Builder withEmail(String email) {
      this.email = email;
      return this;
    }

    public Builder withPassword(String password) {
      this.password = password;
      return this;
    }

    public Customer build() {
      return new Customer(customerId, customerName, gender, dateOfBirth, address, city, state, pin,
          telephone, email, password);
    }
  }
}
