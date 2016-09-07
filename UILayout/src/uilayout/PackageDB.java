/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uilayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 464336
 */
public class PackageDB {
    
    public static ArrayList<Package> loadPackages() throws ClassNotFoundException {
        ArrayList<Package> packages = new ArrayList<Package>();
        try {
            Connection conn = TravelExpertsDB.getConnection();
            //stmt = conn.createStatement();
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
    
    public static ObservableList<String> getPkgIds() throws ClassNotFoundException  {
        List<String> list = new ArrayList<String>();
        try {
            Connection conn = TravelExpertsDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PACKAGEID FROM PACKAGES");
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
    
}
