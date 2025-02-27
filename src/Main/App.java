/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import controller.LoginController;

/**
 *
 * @author dzikr
 */
public class App {
    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        UIManager.put("ComboBox.background", new java.awt.Color(40, 40, 40));
        UIManager.put("Spinner.background", new java.awt.Color(40, 40, 40));
        UIManager.put("Spinner.arrowButtonBackground", new java.awt.Color(40, 40, 40));
        UIManager.put("Spinner.arrowButtonForeground", new java.awt.Color(255, 255, 255));
        
        // Global Styling for Rounded Components
        UIManager.put("Button.arc", 15);
        UIManager.put("Component.arc", 15);
        UIManager.put("ProgressBar.arc", 15);
        UIManager.put("TextComponent.arc", 15);
        UIManager.put( "Component.focusWidth", 1 );
        UIManager.put( "ScrollBar.showButtons", true );

        // Table Styling
        UIManager.put("Table.background", new Color(40, 40, 40));
        UIManager.put("Table.foreground", new Color(220, 220, 220));
        UIManager.put("Table.selectionBackground", new Color(80, 120, 160));
        UIManager.put("Table.selectionForeground", Color.WHITE);
        UIManager.put("Table.rowHeight", 32);

        UIManager.put("TableHeader.background", new Color(55, 55, 60));
        UIManager.put("TableHeader.foreground", new Color(240, 240, 240));
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 18));
        UIManager.put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(10, 10, 10, 10));

        LoginController login = new LoginController();

    }
}
