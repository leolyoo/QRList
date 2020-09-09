package com.qrlist;

import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

public class QRListController implements KeyListener, ActionListener {
    private final QRListView view;
    private final QRListModel model;
    private final ExcelHelper excelHelper;

    public QRListController(QRListModel model, QRListView view) {
        this.model = model;
        this.view = view;
        view.setNumberFieldKeyListener(this);
        excelHelper = new ExcelHelper(model);
        view.setExcelButtonActionListener(this);
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
            String qr = view.getNumberFieldText().trim();
            if (qr.length() > 0) {
                try {
                    model.addRow(Constants.DATE_FORMAT.format(new Date()), qr);
                    sound();
                } catch (SQLException sqlException) {
                    Constants.LOG.append(sqlException.getMessage()).append("\n");
                    view.setLogArea(Constants.LOG);
                }
                view.refreshNumberField();
            }
        }
    }

    private void sound() {
        try (InputStream audioSource = this.getClass().getClassLoader().getResourceAsStream("sound_enter.wav")) {
            assert audioSource != null;
            try (InputStream bufferedInputStream = new BufferedInputStream(audioSource);
                 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream)) {
                Clip clip = AudioSystem.getClip();
                clip.stop();
                clip.open(audioInputStream);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("excel")) {
            excelHelper.export();
        }
    }
}
