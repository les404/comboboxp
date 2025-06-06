package com.example.comboboxp;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {
    @FXML
    private Text welcomeText;

    @FXML private ComboBox<String> combs;

    private Connection con;
    private PreparedStatement smt;
    private ResultSet rs;
    @FXML

    public void initialize() {
        connectionDb();
        setText();
    }

    public Connection connectionDb() {
        String url = "jdbc:mysql://localhost:3306/user_schema";
        String user = "root";
        String password = "1234";


        try{
            con = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to the database successfully");

            smt = con.prepareStatement("select * from new_table");
            rs = smt.executeQuery();

            while(rs.next()) {
               combs.getItems().add(rs.getString("username"));
            }


        }catch(Exception e){
            System.out.println("Failed to connect to database");
            e.printStackTrace();
        }

        return con;
    }

    public void setText() {
        combs.setOnAction(e -> {
            welcomeText.setText(combs.getValue());
        });
    }












}