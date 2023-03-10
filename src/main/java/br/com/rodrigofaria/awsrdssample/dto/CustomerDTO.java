package br.com.rodrigofaria.awsrdssample.dto;

public class CustomerDTO {

    private Long id;
    private String name;
    private String email;
    private Long cpf;

    public CustomerDTO() { }

    public CustomerDTO(Long id, String name, String email, Long cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "CustomerDTO[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "email=" + email + ", " +
                "cpf=" + cpf + ']';
    }
}
