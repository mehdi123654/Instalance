/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossify.services;

import com.crossify.DAO.IServicesApplication;
import com.crossify.entities.Application;
import com.crossify.entities.CombinedResult;
import com.crossify.entities.Freelance;
import com.crossify.utils.MyConnection;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 *
 * @author emnaa
 */
public class CRUDApplication implements IServicesApplication {

    Connection conn = MyConnection.getInstance().getConnection();

    @Override
    public void addApplication(Application a) {
        try {
            String req = "INSERT INTO application (`idFreelance`, `idFreelancer`,`idBO`,`FN_Freelancer`,`LN_Freelancer`,`EmailFreelancer`,`url_CV` ,`Confirmation` ,`Notification`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, a.getIdFreelance());
            ps.setInt(2, a.getIdFreelancer());
            ps.setInt(3, a.getIdBO());
            ps.setString(4, a.getFN());
            ps.setString(5, a.getLN());
            ps.setString(6, a.getEmailF());
            ps.setString(7, a.getUrlCV());
            ps.setBoolean(8, false);
            ps.setBoolean(9, false);
            ps.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Good Luck with the offer");
            alert.setHeaderText("Your Offer Has Been Succesfuly Sent!");
            alert.setContentText("Wait for an email response soon!");
            alert.showAndWait();
            System.out.println("Application added");
            incrementNB(a.getIdFreelance());
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Could not send application");
            alert.setHeaderText("An error has occurred! ");
            alert.setContentText("Please try again! ");
            alert.showAndWait();
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public int nbApplicants(int idOffer) {
        int numApplicants = 0;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM application WHERE idFreelance = ?");
            stmt.setInt(1, idOffer);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            numApplicants = rs.getInt(1);
            rs.close();
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(CRUDApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numApplicants;
    }

    @Override
    public void incrementNB(int idOffer) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE freelance SET nbApplicants = nbApplicants + 1 WHERE idFreelance = " + idOffer;
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public ObservableList<Application> displayById(int id) {
        ObservableList<Application> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM application where idFreelance = ?";

            PreparedStatement st = conn.prepareStatement(request3);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Application a = new Application();
                a.setIdFreelancer(rs.getInt("idFreelancer"));
                a.setFN(rs.getString("FN_Freelancer"));
                a.setLN(rs.getString("LN_Freelancer"));
                a.setEmailF(rs.getString("EmailFreelancer"));
                a.setUrlCV(rs.getString("url_CV"));
                a.setConf(rs.getBoolean("Confirmation"));
                a.setNotif(rs.getBoolean("Notification"));
                a.setAddDate(rs.getTimestamp("ApplicationDate"));
                myList.add(a);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void setConfirmed(int idFreelancer, boolean confirmed) {
        String sql = "UPDATE application SET Confirmation = ? WHERE idFreelancer = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, confirmed);
            stmt.setInt(2, idFreelancer);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNotified(int idFreelancer, boolean notified) {
        try {
            String query = "UPDATE application SET Notification = ? WHERE idFreelancer = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setBoolean(1, notified);
            preparedStatement.setInt(2, idFreelancer);
            preparedStatement.executeUpdate();
            System.out.println("Notification updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating notification: " + e.getMessage());
        }

    }

    @Override
    public void sendToPython(int idFreelancer) {
        try {
            // Execute the SQL query
            String sqlQuery = "SELECT historysearch.ID_user, historysearch.search, historysearch.resultIDs, freelance.category_F, freelance.description, freelance.AddDate "
                    + "FROM historysearch "
                    + "JOIN freelance "
                    + "ON FIND_IN_SET(freelance.idFreelance, historysearch.resultIDs) > 0 "
                    + "WHERE historysearch.ID_user = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlQuery);
            stmt.setInt(1, idFreelancer);
            ResultSet rs = stmt.executeQuery();

            launchPython(rs);
            // Close the statement and result set
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void launchPython(ResultSet combinedResult) throws IOException, InterruptedException, SQLException {
        Gson gson = new Gson();

        // Convert the ResultSet to a list of CombinedResult objects
        List<CombinedResult> resultList = new ArrayList<>();
        while (combinedResult.next()) {
            CombinedResult result = new CombinedResult();
            result.setIdUser(combinedResult.getInt("ID_user"));
            result.setSearch(combinedResult.getString("search"));
            result.setResultIds(combinedResult.getString("resultIDs"));
            result.setCategory(combinedResult.getString("category_F"));
            result.setDescription(combinedResult.getString("description"));
            result.setAddDate(combinedResult.getTimestamp("AddDate"));
            resultList.add(result);
        }

        // Serialize the list of CombinedResult objects to JSON
        String jsonResult = gson.toJson(resultList);

        System.out.println("jsonResult: " + jsonResult); // Print the JSON data

        // Convert the notebook to a Python script using nbconvert
        ProcessBuilder nbConvertBuilder = new ProcessBuilder(
                "jupyter", "nbconvert", "--to", "python", "recommandationSystem.py");
        Process nbConvertProcess = nbConvertBuilder.start();
        int nbConvertExitCode = nbConvertProcess.waitFor();
        if (nbConvertExitCode != 0) {
            System.out.println("Failed to convert the notebook to a Python script");
            return;
        }

        // Get the standard output stream of nbconvert and read the Python script
        InputStream nbConvertStdout = nbConvertProcess.getInputStream();
        ByteArrayOutputStream nbConvertOutput = new ByteArrayOutputStream();
        byte[] nbConvertBuffer = new byte[1024];
        int nbConvertBytesRead;
        while ((nbConvertBytesRead = nbConvertStdout.read(nbConvertBuffer)) != -1) {
            nbConvertOutput.write(nbConvertBuffer, 0, nbConvertBytesRead);
        }
        String pythonScript = nbConvertOutput.toString();

        // Launch the Python interpreter as a subprocess
        ProcessBuilder pythonBuilder = new ProcessBuilder("python");
        Process pythonProcess = pythonBuilder.start();

        // Get the standard input stream of the Python interpreter
        OutputStream pythonStdin = pythonProcess.getOutputStream();

        // Write the Python script to the standard input stream of the Python interpreter
        pythonStdin.write(pythonScript.getBytes());

        // Write the JSON data to the standard input stream of the Python interpreter
        pythonStdin.write(jsonResult.getBytes());

        // Close the standard input stream to indicate end of input
        pythonStdin.close();

        // Wait for the Python interpreter to finish
        int pythonExitCode = pythonProcess.waitFor();

        // Handle the exit code
        if (pythonExitCode == 0) {
            System.out.println("Sent to Python successfully");
        } else {
            System.out.println("Could not send to Python");
        }
    }

}
