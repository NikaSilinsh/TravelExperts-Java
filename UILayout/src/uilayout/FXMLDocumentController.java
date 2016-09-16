/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uilayout;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author 464336
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button btnExit;
    @FXML
    private ListView<?> pkgProduct;
    @FXML
    private Button btnAdd;
    @FXML
    private ListView<?> allProduct;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnEdit;
    @FXML
    private TextField pkgName;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private TextArea pkgDescription;
    @FXML
    private TextField basePrice;
    @FXML
    private TextField agtCommission;
    @FXML
    private ComboBox cboPkgId;
    
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private ArrayList<Package> listPackages;
    
    
    private void handleButtonAction(ActionEvent event) {
 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            cboPkgId.getItems().clear();
            cboPkgId.setItems(getPkgIds());
            listPackages = loadPackages();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }    

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    private ArrayList<Package> loadPackages() {
        ArrayList<Package> packages = new ArrayList<Package>();
        try {
            connectDB();
            PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM PACKAGES");
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next())
            {
                Package pkg = new Package();
                pkg.setPackageId(rs2.getString("PackageId"));
                pkg.setPkgName(rs2.getString("PkgName"));
                pkg.setPkgStartDate(rs2.getString("PkgStartDate"));
                pkg.setPkgEndDate(rs2.getString("PkgEndDate"));
                pkg.setPkgDesc(rs2.getString("PkgDesc"));
                pkg.setPkgBasePrice(rs2.getString("PkgBasePrice"));
                pkg.setPkgAgencyCommission(rs2.getString("PkgAgencyCommission"));
                packages.add(pkg);
                
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }
    
    private void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
    }

    private ObservableList<String> getPkgIds() {
        List<String> list = new ArrayList<String>();
        try {
            connectDB();
            rs = stmt.executeQuery("SELECT PACKAGEID FROM PACKAGES");
            while (rs.next())
            {
                list.add(rs.getString("PackageId"));
            }
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> packages = FXCollections.observableList(list);
        return packages; 
    }

    @FXML
    private void selectPkgId(ActionEvent event) {
        String selectedID = (String) cboPkgId.getValue();
        for (Package pkg : listPackages)
        {
            String temp = pkg.getPackageId();
            if (temp.equals(selectedID)) {
                pkgName.setText(pkg.getPkgName());
                pkgDescription.setText(pkg.getPkgDesc());
                
                // Loads the start and end dates
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                formatter = formatter.withLocale(Locale.CANADA);
                LocalDate date = LocalDate.parse(pkg.getPkgStartDate(), formatter);
                startDate.setValue(date);
                date = LocalDate.parse(pkg.getPkgEndDate(), formatter);
                endDate.setValue(date);
                
                // Loads the base price and commission
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                Double price = Double.parseDouble(pkg.getPkgBasePrice());
                Double commission = Double.parseDouble(pkg.getPkgAgencyCommission());
                basePrice.setText(nf.format(price)); 
                agtCommission.setText(nf.format(commission));
            }
        }
    }
    
}
