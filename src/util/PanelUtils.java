package util;

import javax.swing.JPanel;

public class PanelUtils {
    public static void switchPanel(JPanel mainPanel, JPanel newPanel) {
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.revalidate();
        mainPanel.add(newPanel);
        mainPanel.repaint();
        mainPanel.revalidate();
    }
}