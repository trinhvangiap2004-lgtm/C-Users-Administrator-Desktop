/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainmenu;

/**
 *
 * @author Admin
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class BilliardSimulation extends JFrame {
    private BilliardPanel panel;
    private int mapId;

    public BilliardSimulation(int map) {
        this.mapId = map;
        setTitle("Map " + map);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel = new BilliardPanel(mapId);
        add(panel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.BLACK);
        JButton startBtn = new JButton("Start");
        JButton stopBtn = new JButton("Stop");
        JButton addBtn = new JButton("Add Ball");
        JButton removeBtn = new JButton("Remove Ball");
        JButton backBtn = new JButton("Back");

        controlPanel.add(startBtn);
        controlPanel.add(stopBtn);
        controlPanel.add(addBtn);
        controlPanel.add(removeBtn);
        controlPanel.add(backBtn);
        add(controlPanel, BorderLayout.SOUTH);

        startBtn.addActionListener(e -> panel.start());
        stopBtn.addActionListener(e -> panel.stop());
        addBtn.addActionListener(e -> panel.addBall());
        removeBtn.addActionListener(e -> panel.removeBall());
        backBtn.addActionListener(e -> {
            new MainMenu().setVisible(true);
            dispose();
        });
    }
}
