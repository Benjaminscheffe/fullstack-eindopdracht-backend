package nl.benjamin.muziekmarktplaats.dto;

public class AuthenticationResponse {

    private final String jwt;
    private final Long id;

    public AuthenticationResponse(String jwt, Long id) {
        this.jwt = jwt;
        this.id = id;
    }

    public String getJwt() {
        return jwt;
    }

    public Long getId() {
        return id;
    }

}