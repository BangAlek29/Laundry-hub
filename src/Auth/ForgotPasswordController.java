/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Auth;

import model.AkunModel;
import model.CustomerModel;

/**
 *
 * @author dzikr
 */
public class ForgotPasswordController {
    private final forgotPassword view;
    private final AkunModel akun;
    private final CustomerModel cust;
    
    public ForgotPasswordController(){
        view = new forgotPassword();
        akun = new AkunModel();
        cust = new CustomerModel();
        view.setVisible(true);
    }
}
