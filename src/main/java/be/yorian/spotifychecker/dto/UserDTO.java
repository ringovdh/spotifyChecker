package be.yorian.spotifychecker.dto;

public class UserDTO {

    private final String userName;

    public UserDTO(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
