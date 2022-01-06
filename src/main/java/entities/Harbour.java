package entities;

import dtos.HarbourDTO;

import javax.persistence.*;
import java.util.List;

@Table(name = "harbour")
@Entity
public class Harbour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String address;
    private int capacity;

    @OneToMany(mappedBy = "harbour")
    private List<Boat> boats;


    public Harbour() {
    }

    public Harbour(String name, String address, int capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public Harbour(HarbourDTO harbourDTO) {
        if (harbourDTO != null){
            this.id = id;
        }
        this.name = harbourDTO.getName();
        this.address = harbourDTO.getAddress();
        this.capacity = harbourDTO.getCapacity();
        this.boats = harbourDTO.getBoats();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}