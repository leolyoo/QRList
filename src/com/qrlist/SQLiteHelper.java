package com.qrlist;

import java.sql.*;
import java.util.Vector;

public class SQLiteHelper {
    private static final String CLASS_NAME = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:data.db";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS qr_records (\n"
            + "	date text NOT NULL,\n"
            + "	qr text NOT NULL\n"
            + ");";

    private static final String SQL_SELECT_ALL = "SELECT date, qr FROM qr_records;";
    private static final String SQL_INSERT = "INSERT INTO qr_records (date, qr) VALUES (?, ?)";

    static {
        try {
            Class.forName(CLASS_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(URL); Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL_CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Vector<QRData> selectAll() {
        Vector<QRData> data = new Vector<>();
        try (Connection connection = DriverManager.getConnection(URL); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                data.add(new QRData(resultSet.getString("date"), resultSet.getString("qr")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void insert(String date, String qr) {
        try (Connection connection = DriverManager.getConnection(URL); PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, date);
            statement.setString(2, qr);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
