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

/**
 *
 * @author Sarah Ferguson
 */
public class ProductSupplierDB {
    
    public static ArrayList<ProductSupplier> loadPkgProducts(String pkgId) throws ClassNotFoundException {
        ArrayList<ProductSupplier> pkgProdList = new ArrayList<ProductSupplier>();
        try {
            Connection conn = TravelExpertsDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select ProdName, SupName " + 
                                    "from Packages_Products_Suppliers pps " +
                                    "join Products_Suppliers ps " + 
                                    "on pps.ProductSupplierId = ps.ProductSupplierId " +
                                    "join Suppliers s " +
                                    "on ps.SupplierId = s.SupplierId " +
                                    "join Products p " +
                                    "on ps.ProductId = p.ProductId " +
                                    "where PackageId = " + pkgId + ";");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ProductSupplier prodSup = new ProductSupplier();
                prodSup.setProdName(rs.getString("ProdName"));
                prodSup.setSupName(rs.getString("SupName"));
                pkgProdList.add(prodSup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pkgProdList;
    }
    
    public static ArrayList<ProductSupplier> getAllProducts() throws ClassNotFoundException {
        ArrayList<ProductSupplier> pkgProdList = new ArrayList<ProductSupplier>();
        try {
            Connection conn = TravelExpertsDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select ProdName, SupName " +
                                   "from Products_Suppliers ps " +
                                   "join Suppliers s " +
                                   "on ps.SupplierId = s.SupplierId " +
                                   "join Products p " +
                                   "on ps.ProductId = ps.ProductId;");
            while (rs.next())
            {
                ProductSupplier prodSup = new ProductSupplier();
                prodSup.setProdName(rs.getString("ProdName"));
                prodSup.setSupName(rs.getString("SupName"));
                pkgProdList.add(prodSup);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pkgProdList;
    }
    
}
