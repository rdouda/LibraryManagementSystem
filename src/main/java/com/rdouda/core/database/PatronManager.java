package com.rdouda.core.database;

import com.rdouda.core.library.Patron;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PatronManager {
    public static Patron getPatronByMemberShipId(Patron patron) throws SQLException {
        PreparedStatement getPatronByMemberShipIdStatement =
                DatabaseManager.connection.prepareStatement(SQLQueries.GET_PATRON_BY_MEMBERSHIP_ID);
        getPatronByMemberShipIdStatement.setString(1, patron.getMemberShipId());
        ResultSet resultSet = getPatronByMemberShipIdStatement.executeQuery();
        if (resultSet.next()){
            int patronId = resultSet.getInt("patron_id");
            String name = resultSet.getString("name");
            String contactNumber = resultSet.getString("contact_number");
            String memberShipId = resultSet.getString("membership_id");
            return new Patron(patronId, name, contactNumber, memberShipId);
        }
        return null;
    }
    public static ArrayList<Patron> getAllPatrons() throws SQLException {
        ArrayList<Patron> patrons = new ArrayList<>();
        Statement getAllPatronsStatement = DatabaseManager.connection.createStatement();
        ResultSet resultSet = getAllPatronsStatement.executeQuery(SQLQueries.GET_ALL_PATRONS);
        while(resultSet.next()){
            int patronId = resultSet.getInt("patron_id");
            String name = resultSet.getString("name");
            String contactNumber = resultSet.getString("contact_number");
            String memberShipId = resultSet.getString("membership_id");
            Patron patron = new Patron(patronId, name, contactNumber, memberShipId);
            patrons.add(patron);
        }
        return patrons;
    }

    public static void addPatron(Patron patron) throws SQLException{
        PreparedStatement addPatronStatement =
                DatabaseManager.connection.prepareStatement(SQLQueries.ADD_PATRON);
        addPatronStatement.setString(1, patron.getName());
        addPatronStatement.setString(2, patron.getContactNumber());
        addPatronStatement.setString(3, patron.getMemberShipId());
        int rowsAffected = addPatronStatement.executeUpdate();
    }

    public static void removePatron(Patron patron) throws SQLException{
        PreparedStatement removePatronStatement =
                DatabaseManager.connection.prepareStatement(SQLQueries.DELETE_PATRON_BY_MEMBERSHIP_ID);
        removePatronStatement.setString(1, patron.getMemberShipId());
        int rowsAffected = removePatronStatement.executeUpdate();
    }

    public static void removeAllPatrons() throws SQLException{
        Statement removeAllPatronsStatement = DatabaseManager.connection.createStatement();
        removeAllPatronsStatement.executeUpdate(SQLQueries.REMOVE_ALL_PATRONS);
    }
}
