package com.qrlist;

import java.util.Date;
import java.util.Objects;

public class QRData {
    public final Date date;
    public final String qr;

    public QRData(String qr) {
        date = new Date();
        this.qr = qr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QRData qrData = (QRData) o;
        return Objects.equals(date, qrData.date) &&
                Objects.equals(qr, qrData.qr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, qr);
    }
}
