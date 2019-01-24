package co.com.adnII.model.entities;

import javax.persistence.*;

    @Entity
    @Table(name = "company")
    public class Company {

        public Company() {
        }

        public Company(String nit,
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
        @Column(name = "ID")
        private Long id;

        @Column(name = "NAME",length = 100)
        private String name;

        @Column(name = "NIT",length = 20)
        private String nit;

        @Column(name = "EMAIL",length = 100)
        private String email;

        @Column(name = "PHONE_NUMBER",length = 20)
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

