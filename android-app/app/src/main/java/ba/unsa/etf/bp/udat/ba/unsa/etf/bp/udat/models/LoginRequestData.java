package ba.unsa.etf.bp.udat.ba.unsa.etf.bp.udat.models;

public class LoginRequestData {
    private String username;
    private String password;

    public LoginRequestData(String username, String password) {
        this.username = username;
        this.password = password;
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
}
