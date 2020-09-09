package com.qrlist;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ExcelHelper {
    private final TableModel model;

    public ExcelHelper(final TableModel model) {
        this.model = model;

    }

    public void export() {
        final SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        final SXSSFSheet sheet = workbook.createSheet("QRList");
        sheet.trackAllColumnsForAutoSizing();

        final Row columnRow = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            Cell cell = columnRow.createCell(i);
            cell.setCellValue(model.getColumnName(i));
        }

        Row row;
        for (int i = 0; i < model.getRowCount(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < model.getColumnCount(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(model.getValueAt(i, j).toString());
            }
        }
        for (int i = 0; i < model.getColumnCount(); i++) {
            sheet.autoSizeColumn(i, true);
        }

        try {
            final FileOutputStream stream = new FileOutputStream(Constants.FILE_NAME_DATE_FORMAT.format(new Date()) + "_report.xlsx");
            workbook.write(stream);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            workbook.dispose();
        }
    }
}
