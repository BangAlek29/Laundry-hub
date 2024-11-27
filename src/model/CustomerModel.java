package model;

public class CustomerModel {
    private String idCustomer;
    private String idAkun;
    private String name;
    private String phone;
    private String address;

    public CustomerModel(String idCustomer, String name, String phone, String address) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    
    public CustomerModel(){
        
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getIdAkun(){
        return idAkun;
    }
    
    public void setIdAkun(String idAkun){
        this.idAkun = idAkun;
    }
}
