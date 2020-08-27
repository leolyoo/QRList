package com.qrlist;

import javax.swing.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            QRListModel model = new QRListModel();
            QRListView view = new QRListView(model);
            new QRListController(model, view);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
