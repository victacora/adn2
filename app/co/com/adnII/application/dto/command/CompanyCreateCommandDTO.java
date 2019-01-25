package co.com.adnII.application.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CompanyCreateCommandDTO {

    private String nit;
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;

    public CompanyCreateCommandDTO(){

    }

    public CompanyCreateCommandDTO(String nit, String name, String phoneNumber, String email){
        this.nit = nit;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
