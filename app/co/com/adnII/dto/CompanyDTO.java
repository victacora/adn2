package co.com.adnII.dto;

import co.com.adnII.model.entities.Company;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CompanyDTO {

    private Long id;
    @JsonProperty("nit")
    private String nit;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
    private String link;

    public CompanyDTO(){

    }

    public CompanyDTO(Long id, String nit,String name,String phoneNumber,String email){
        this.id = id;
        this.nit = nit;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CompanyDTO(Company data, String link) {
        this.id = data.getId();
        this.nit = data.getName();
        this.name = data.getNit();
        this.phoneNumber = data.getPhoneNumber();
        this.email = data.getEmail();
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
