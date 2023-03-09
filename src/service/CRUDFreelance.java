/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mysql.jdbc.StringUtils;

import dao.IServicesFreelance;
import entity.Freelance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utils.MyConnectionDB;

/**
 *
 * @author emnaa
 */
public class CRUDFreelance implements IServicesFreelance {

    Statement ste;
    Connection conn = MyConnectionDB.getInstance().getConnection();

    @Override
    public void addFreelance(Freelance f) {
        try {
            ste = conn.createStatement();
            // public Freelance(int id_F, int BO_id, String BO_email, String category_F, String description, float budget, boolean state_F) {

            String req = "Insert into freelance values('" + f.getId_F() + "','" + f.getBO_id() + "'),'" + f.getBO_email() + "','" + f.getCategory_F() + "','" + f.getDescription() + "','" + f.getBudget() + "','" + f.isState_F() + "''),'";
            ste.executeUpdate(req);
            System.out.println("Freelance added");
        } catch (SQLException ex) {
            System.out.println("Could not insert Freelance!!!!");
        }
    }

    @Override
    public void addFreelance2(Freelance f) {
        try {
            String req = "INSERT INTO freelance (`idBO`, `emailBO`,`category_F`,`description`,`budget`,`state`,`urlLogo`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setInt(1, f.getBO_id());
            ps.setString(2, f.getBO_email());
            ps.setString(3, f.getCategory_F());
            ps.setString(4, f.getDescription());
            ps.setFloat(5, f.getBudget());
            ps.setBoolean(6, f.isState_F());
            ps.setString(7, f.getUrlLogo());
            ps.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Offer Posted");
            alert.setHeaderText("Thanks for your contribution");
            alert.setContentText("Your offer has been successfully posted.");
            alert.showAndWait();
            System.out.println("Freelance added");
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Could not post the offer");
            alert.setHeaderText("An error has occurred! ");
            alert.setContentText("Please try again! ");
            alert.showAndWait();
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void modifyFreelance(Freelance f) {
        int state = 0;
        if (f.isState_F() == true) {
            state = 1;
        }
        try {
            String req = "UPDATE `freelance` SET `idBO` = '" + f.getBO_id() + "', `emailBO` = '" + f.getBO_email() + "',`category_F` = '" + f.getCategory_F() + "',`description` = '" + f.getDescription() + "',`budget` = '" + f.getBudget() + "',`state` = '" + state + "' WHERE `freelance`.`idFreelance` = " + f.getId_F();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Freelance updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteFreelance(Freelance f) {
        try {
            String req = "DELETE FROM `freelance` WHERE idFreelance = " + f.getId_F();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Freelance offer deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Freelance> displayFreelance() {
        List<Freelance> offers = new ArrayList<Freelance>();
        try {
            String req = "SELECT * FROM `freelance`";
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {
                Freelance resultFreelance = new Freelance(result.getInt("idFreelance"), result.getInt("idBO"), result.getString("emailBO"), result.getString("category_F"), result.getString("description"), result.getFloat("budget"), result.getBoolean("state"));
                offers.add(resultFreelance);
            }
            System.out.println(offers);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return offers;
    }

    @Override
    public ObservableList<Freelance> displayFreelancee() {
        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM freelance";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(request3);
            while (rs.next()) {
                Freelance f = new Freelance();
                f.setId_F(rs.getInt("idFreelance"));
                f.setBO_id(rs.getInt("idBO"));
                f.setBO_email(rs.getString("emailBO"));
                f.setCategory_F(rs.getString("category_F"));
                f.setDescription(rs.getString("description"));
                f.setBudget(rs.getFloat("budget"));
                f.setState_F(rs.getBoolean("state"));
                f.setUrlLogo(rs.getString("urlLogo"));
                myList.add(f);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public ObservableList<Freelance> displayNewFreelancee() {
        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM freelance ORDER BY AddDate DESC LIMIT 4";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(request3);
            while (rs.next()) {
                Freelance f = new Freelance();
                f.setId_F(rs.getInt("idFreelance"));
                f.setBO_id(rs.getInt("idBO"));
                f.setBO_email(rs.getString("emailBO"));
                f.setCategory_F(rs.getString("category_F"));
                f.setDescription(rs.getString("description"));
                f.setBudget(rs.getFloat("budget"));
                f.setState_F(rs.getBoolean("state"));
                f.setUrlLogo(rs.getString("urlLogo"));
                myList.add(f);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public ObservableList<Freelance> displayMyFreelancee(int id) {

        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM freelance where idBO = ?";

            PreparedStatement st = conn.prepareStatement(request3);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Freelance f = new Freelance();
                f.setId_F(rs.getInt("idFreelance"));
                f.setBO_id(rs.getInt("idBO"));
                f.setBO_email(rs.getString("emailBO"));
                f.setCategory_F(rs.getString("category_F"));
                f.setDescription(rs.getString("description"));
                f.setBudget(rs.getFloat("budget"));
                f.setState_F(rs.getBoolean("state"));
                f.setUrlLogo(rs.getString("urlLogo"));
                myList.add(f);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public Freelance reaserchById(Freelance f) {
        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM freelance where idFreelance = ?";

            PreparedStatement st = conn.prepareStatement(request3);
            st.setInt(1, f.getId_F());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Freelance fr = new Freelance();
                fr.setId_F(rs.getInt("idFreelance"));
                fr.setBO_id(rs.getInt("idBO"));
                fr.setBO_email(rs.getString("emailBO"));
                fr.setCategory_F(rs.getString("category_F"));
                fr.setDescription(rs.getString("description"));
                fr.setBudget(rs.getFloat("budget"));
                fr.setState_F(rs.getBoolean("state"));
                myList.add(fr);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList.get(0);
    }

    @Override
    public ObservableList<Freelance> advancedreaserch(String search) {
        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM freelance where description LIKE ? OR category_F LIKE ? OR emailBO LIKE ?";

            PreparedStatement st = conn.prepareStatement(request3);
            st.setString(1, "%" + search + "%");
            st.setString(2, "%" + search + "%");
            st.setString(3, "%" + search + "%");
            ResultSet rs = st.executeQuery();
            List<Integer> offerIds = new ArrayList<>();
            while (rs.next()) {
                Freelance f = new Freelance();
                f.setId_F(rs.getInt("idFreelance"));
                f.setBO_id(rs.getInt("idBO"));
                f.setBO_email(rs.getString("emailBO"));
                f.setCategory_F(rs.getString("category_F"));
                f.setDescription(rs.getString("description"));
                f.setBudget(rs.getFloat("budget"));
                f.setState_F(rs.getBoolean("state"));
                f.setUrlLogo(rs.getString("urlLogo"));
                myList.add(f);

                offerIds.add(rs.getInt("idFreelance"));
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public ObservableList<String> getAllCategories() {
        ObservableList<String> categories = FXCollections.observableArrayList();

        try {
            String request3 = "SELECT DISTINCT category_F FROM freelance";
            PreparedStatement st = conn.prepareStatement(request3);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                categories.add(rs.getString("category_F"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return categories;
    }

    @Override
    public ObservableList<Freelance> filterByCategory(String cat) {
        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM freelance where category_F = ?";

            PreparedStatement st = conn.prepareStatement(request3);
            st.setString(1, cat);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Freelance fr = new Freelance();
                fr.setId_F(rs.getInt("idFreelance"));
                fr.setBO_id(rs.getInt("idBO"));
                fr.setBO_email(rs.getString("emailBO"));
                fr.setCategory_F(rs.getString("category_F"));
                fr.setDescription(rs.getString("description"));
                fr.setBudget(rs.getFloat("budget"));
                fr.setState_F(rs.getBoolean("state"));
                fr.setUrlLogo(rs.getString("urlLogo"));
                myList.add(fr);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public Freelance reaserchById(int id) {
        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM freelance where idFreelance = ?";

            PreparedStatement st = conn.prepareStatement(request3);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Freelance fr = new Freelance();
                fr.setId_F(rs.getInt("idFreelance"));
                fr.setBO_id(rs.getInt("idBO"));
                fr.setBO_email(rs.getString("emailBO"));
                fr.setCategory_F(rs.getString("category_F"));
                fr.setDescription(rs.getString("description"));
                fr.setBudget(rs.getFloat("budget"));
                fr.setState_F(rs.getBoolean("state"));
                myList.add(fr);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList.get(0);
    }

    @Override
    public ObservableList<Freelance> sortByDemand() {
        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM freelance ORDER BY nbApplicants DESC";

            PreparedStatement st = conn.prepareStatement(request3);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Freelance f = new Freelance();
                f.setId_F(rs.getInt("idFreelance"));
                f.setBO_id(rs.getInt("idBO"));
                f.setBO_email(rs.getString("emailBO"));
                f.setCategory_F(rs.getString("category_F"));
                f.setDescription(rs.getString("description"));
                f.setBudget(rs.getFloat("budget"));
                f.setState_F(rs.getBoolean("state"));
                f.setUrlLogo(rs.getString("urlLogo"));
                myList.add(f);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

    public ObservableList<Freelance> simpleSearch(String search, int idFreelancer) {
        ObservableList<Freelance> myList = FXCollections.observableArrayList();
        try {
            String request3 = "SELECT * FROM freelance where category_F LIKE ? OR description LIKE ?";

            PreparedStatement st = conn.prepareStatement(request3);
            st.setString(1, "%" + search + "%");
            st.setString(2, "%" + search + "%");
            ResultSet rs = st.executeQuery();

            List<Integer> offerIds = new ArrayList<>();
            while (rs.next()) {
                Freelance f = new Freelance();
                f.setId_F(rs.getInt("idFreelance"));
                f.setBO_id(rs.getInt("idBO"));
                f.setBO_email(rs.getString("emailBO"));
                f.setCategory_F(rs.getString("category_F"));
                f.setDescription(rs.getString("description"));
                f.setBudget(rs.getFloat("budget"));
                f.setState_F(rs.getBoolean("state"));
                f.setUrlLogo(rs.getString("urlLogo"));
                myList.add(f);

                offerIds.add(rs.getInt("idFreelance"));
            }
            rs.close();
            st.close();

            // Insert a new row into the search history table
            String insertQuery = "INSERT INTO historysearch (ID_user, search, resultCount, resultIDs) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertQuery);
            insertStatement.setInt(1, idFreelancer);
            insertStatement.setString(2, search);
            insertStatement.setInt(3, offerIds.size());
            StringBuilder idsBuilder = new StringBuilder();
            for (int i = 0; i < offerIds.size(); i++) {
                idsBuilder.append(offerIds.get(i));
                if (i != offerIds.size() - 1) {
                    idsBuilder.append(", ");
                }
            }
            String offerIdsStr = idsBuilder.toString();
            insertStatement.setString(4, offerIdsStr);
            insertStatement.executeUpdate();
            insertStatement.close();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
    }

}
