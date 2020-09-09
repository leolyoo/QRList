package com.qrlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class QRListView extends JFrame {
    private static final String TITLE = "[학생지원팀] 입출입 관리 시스템";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private final NumberField numberField;
    private final JTextArea logArea;
    private final JButton excelButton;

    public QRListView(QRListModel qrListModel) {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        numberField = new NumberField();
        JTable table = new JTable(qrListModel);
        logArea = new JTextArea();
        logArea.setEditable(false);
        excelButton = new JButton("Excel");
        excelButton.setActionCommand("excel");

        add(numberField, BorderLayout.NORTH);
        add(logArea, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(excelButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public String getNumberFieldText() {
        return numberField.getText();
    }

    public void refreshNumberField() {
        numberField.setText("");
    }

    public void setNumberFieldKeyListener(KeyListener keyListener) {
        numberField.addKeyListener(keyListener);
    }

    public void setLogArea(StringBuilder log) {
        logArea.setText(String.valueOf(log));
    }

    public void setExcelButtonActionListener(ActionListener actionListener) {
        excelButton.addActionListener(actionListener);
    }
}
