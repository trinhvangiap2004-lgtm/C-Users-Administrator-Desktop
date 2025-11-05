/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mainmenu;

/**
 *
 * @author Admin
 */

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    public MainMenu() {
        setTitle("Billiard Simulation - Minimalist Version");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridLayout(5, 1, 10, 10));
        getContentPane().setBackground(Color.BLACK);

        JLabel title = new JLabel("BILLIARD SIMULATION", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Consolas", Font.BOLD, 22));

        JButton map1Btn = createButton("Map 1");
        JButton map2Btn = createButton("Map 2");
        JButton map3Btn = createButton("Map 3");
        JButton exitBtn = createButton("Exit");

        add(title);
        add(map1Btn);
        add(map2Btn);
        add(map3Btn);
        add(exitBtn);

        map1Btn.addActionListener(e -> openSimulation(1));
        map2Btn.addActionListener(e -> openSimulation(2));
        map3Btn.addActionListener(e -> openSimulation(3));
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Consolas", Font.BOLD, 16));
        return btn;
    }

    private void openSimulation(int map) {
        new BilliardSimulation(map).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}

