package br.com.rodrigofaria.awsrdssample.dto;

public class ProductDTO {

    private Long id;
    private String name;
    private String brand;
    private Double value;

    public ProductDTO() { }

    public ProductDTO(Long id, String name, String brand, Double value) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.value = value;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductDTO[" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "brand=" + brand + ", " +
                "value=" + value + ']';
    }
}
