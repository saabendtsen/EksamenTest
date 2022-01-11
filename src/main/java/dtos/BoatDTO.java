package dtos;

import entities.Boat;

import java.util.List;

public class BoatDTO {

    private Long id;
    private String brand;
    private String make;
    private String name;
    private String image;
    private List<UserDTO> owners;


    public BoatDTO() {
    }

    public BoatDTO(Boat boat) {
        if(boat.getId() != null){
            this.id = boat.getId();
        }
        this.brand = boat.getBrand();
        this.make = boat.getBrand();
        this.name = boat.getName();
        this.image = boat.getImage();
    }

    public BoatDTO(String brand, String make, String name, String image) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
