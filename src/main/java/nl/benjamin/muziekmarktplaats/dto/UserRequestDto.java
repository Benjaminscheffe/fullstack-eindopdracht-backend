package nl.benjamin.muziekmarktplaats.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDto {

    @NotBlank
    @Size(min=3, max=20)
    public String username;

    @NotBlank
    @Size(min=3, max=30)
    public String email;

    @NotBlank
    @Size(min=8, max=32)
    public String password;

    public UserRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
