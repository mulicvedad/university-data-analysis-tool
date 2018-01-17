package ba.unsa.etf.bp.udat.ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseData {
    @JsonProperty("username")
    public String username;

    @JsonProperty("jwt")
    public String jwt;

    @JsonProperty("role")
    public String role;

    public LoginResponseData() {}

    public LoginResponseData(String role, String jwt) {
        this.role = role;
        this.jwt = jwt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
