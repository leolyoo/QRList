package com.qrlist;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class QRListController implements KeyListener {
    private final QRListView view;
    private final QRListModel model;

    public QRListController() {
        model = new QRListModel();
        view = new QRListView(model);
        view.setNumberFieldKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String s = view.getNumberFieldText().trim();
            if (s.length() > 0) {
                model.addRow(s);
                model.fireTableDataChanged();
                view.refreshNumberField();
            }
        }
    }
}
