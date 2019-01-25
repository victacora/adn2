package co.com.adnII.domain.entities;

    public class Company {

        private Long id;
        private String name;
        private String nit;
        private String email;
        private String phoneNumber;


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

