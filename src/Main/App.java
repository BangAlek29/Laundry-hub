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

import controller.LoginController;

/**
 *
 * @author dzikr
 */
public class App {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        // Global Styling for Rounded Components
        UIManager.put("Button.arc", 12);
        UIManager.put("Component.arc", 12);
        UIManager.put("ProgressBar.arc", 12);
        UIManager.put("TextComponent.arc", 12);

        // Table Styling
        UIManager.put("Table.background", new Color(40, 40, 45));
        UIManager.put("Table.foreground", Color.LIGHT_GRAY);
        UIManager.put("Table.selectionBackground", new Color(80, 140, 190));
        UIManager.put("Table.selectionForeground", Color.WHITE);
        UIManager.put("Table.gridColor", new Color(55, 55, 60));
        UIManager.put("Table.rowHeight", 28);

        // Table Header Styling
        UIManager.put("TableHeader.background", new Color(45, 45, 50));
        UIManager.put("TableHeader.foreground", Color.WHITE);
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 16));
        UIManager.put("TableHeader.cellBorder", BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // Table Font
        UIManager.put("Table.font", new Font("Segoe UI", Font.PLAIN, 14));

        // Tooltip Styling
        UIManager.put("ToolTip.background", new Color(50, 50, 55));
        UIManager.put("ToolTip.foreground", Color.WHITE);
        LoginController login = new LoginController();

    }
}
