package model;

public class AkunModel {
    private String idAkun;
    private String username;
    private String password;
    private String role;

    // Constructor
    public AkunModel (){
        this.username = null;
        this.password = null;
        this.role = null;
        this.idAkun = null;
    }
    
    public AkunModel(String idAkun,String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.idAkun = idAkun;
    }

    // Getter dan Setter
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getIdAkun(){
        return idAkun;
    }
    
    public void setIdAkun(String idAkun){
        this.idAkun = idAkun;
    }
}
