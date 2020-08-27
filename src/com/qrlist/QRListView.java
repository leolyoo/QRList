package com.qrlist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class QRListView extends JFrame {
    private static final String TITLE = "QRList";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private final NumberField numberField;
    private final JTable table;
    private final JTextArea logArea;

    public QRListView(QRListModel qrListModel) {
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        numberField = new NumberField();
        table = new JTable(qrListModel);
        logArea = new JTextArea();
        logArea.setEditable(false);

        add(numberField, BorderLayout.NORTH);
        add(logArea, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

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
}
