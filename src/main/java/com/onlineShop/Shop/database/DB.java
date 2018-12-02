package com.onlineShop.Shop.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import com.onlineShop.Shop.products.*;

public class DB {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void connect(){
        int attemptsNumber =0;
        while(attemptsNumber<3)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                File password= new File(this.getClass().getResource("/pass.txt").getFile());
                BufferedReader scn = new BufferedReader(new FileReader(password));
                String pass = scn.readLine();
                scn.close();
                conn =
                        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/mgujda",
                                "mgujda",pass);
                break;

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                attemptsNumber++;
            }catch(Exception e){
                e.printStackTrace();
                attemptsNumber++;
            }
    }

    public List<Product> getAllProducts(){
        List<Product> productList = new LinkedList<>();
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            rs = stmt.executeQuery("SELECT * FROM produkty");

            productList = printProductsQuery();
        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            dropConnection();
        }
    return  productList;
    }

    public LinkedList<Product> getPorductsByCategory(String category){
        LinkedList<Product> productList = new LinkedList<>();
        try {
            connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM produkty WHERE kategoria = ?");
            ps.setObject(1,category);
            rs= ps.executeQuery();
            productList = printProductsQuery();
        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            dropConnection();
        }
        return  productList;
    }

    private void dropConnection() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) { } // ignore
            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) { } // ignore

            stmt = null;
        }
    }

    public void selectByProducer(String producer){
        try {
            connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM produkty WHERE producent = ?");
            ps.setObject(1,producer);
            
            rs = ps.executeQuery();

            printProductsQuery();
        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            dropConnection();
        }
    }
    public void addProduct(String idproduktu,String model, String producent, String opis, String kategoria, Integer sztuk, Double cena){
        connect();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO produkty(idproduktu,model, producent, opis, kategoria, sztuk, cena) VALUES(?,?,?,?,?,?,?)");
            ps.setObject(1, idproduktu);
            ps.setObject(2, model);
            ps.setObject(3, producent);
            ps.setObject(4, opis);
            ps.setObject(5,kategoria);
            ps.setObject(6,sztuk);
            ps.setObject(7,cena);
            ps.executeUpdate();
        } catch(SQLException e){

        } finally {
            dropConnection();
        }
    }

    private LinkedList<Product> printProductsQuery() throws SQLException {
        LinkedList<Product> productList = new LinkedList<>();
        while(rs.next()){
            productList.add(new Product(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getDouble(7)
            ));
        }
       return productList;
    }
}

