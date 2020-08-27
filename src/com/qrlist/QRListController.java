package com.qrlist;

import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QRListController implements KeyListener {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private final QRListView view;
    private final QRListModel model;
    private static final StringBuilder LOG = new StringBuilder();

    public QRListController(QRListModel model, QRListView view) {
        this.model = model;
        this.view = view;
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
            String qr = view.getNumberFieldText().trim();
            if (qr.length() > 0) {
                try {
                    model.addRow(DATE_FORMAT.format(new Date()), qr);
                    sound();
                } catch (SQLException sqlException) {
                    LOG.append(sqlException.getMessage()).append("\n");
                    view.setLogArea(LOG);
                }
                view.refreshNumberField();
            }
        }
    }

    private void sound() {
        try (InputStream audioSource = this.getClass().getClassLoader().getResourceAsStream("sound_enter.wav");
             InputStream bufferedInputStream = new BufferedInputStream(audioSource);
             AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream)) {
            Clip clip = AudioSystem.getClip();
            clip.stop();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
