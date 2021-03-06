package com.qrlist;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.Vector;

public class QRListModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"date", "qr"};
    private final Vector<QRData> data = new Vector<>();

    public QRListModel() throws ClassNotFoundException, SQLException {
        SQLiteHelper.isReady();
        SQLiteHelper.createTable();
        data.addAll(SQLiteHelper.selectAll());
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        QRData qrData = data.elementAt(rowIndex);
        Object[] rowData = {qrData.date, qrData.qr};
        return rowData[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    public void addRow(String date, String qr) throws SQLException {
        SQLiteHelper.insert(date, qr);
        data.add(new QRData(date, qr));
        fireTableDataChanged();
    }
}
