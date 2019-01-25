package co.com.adnII.infrastructure.persistence.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class CompanyEntity {

    public CompanyEntity() {
    }

    public CompanyEntity(String nit,
                         String name,
                         String phoneNumber,
                         String email) {
        this.name = name;
        this.nit = nit;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 20)
    private String nit;

    @Column(length = 100)
    private String email;

    @Column(name = "phone_number",length = 20)
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
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

