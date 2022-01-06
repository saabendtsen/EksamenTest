package dtos;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String username;
    private String password;
    private String name;
    private String address;
    private String phone;


    public static List<UserDTO> getDtos(List<User> u){
        List<UserDTO> userDTOSdtos = new ArrayList();
        u.forEach(um -> userDTOSdtos.add(new UserDTO(um)));
        return userDTOSdtos;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        if (user.getPhone() != null){
            this.phone = user.getPhone();
        }
        if (user.getAddress() != null){
            this.address = user.getAddress();
        }
        if (user.getName() != null){
            this.name = user.getName();
        }
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
