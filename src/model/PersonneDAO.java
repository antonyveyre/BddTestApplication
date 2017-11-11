/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PersonneDAO {

  private Connection conn;
  private Personne p;
  Statement stmt;

  public PersonneDAO(Connection _conn) {
    this.conn = _conn;
    try {
      this.conn.setAutoCommit(true);
    } catch (SQLException ex) {
      Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ArrayList<Personne> getPersonnes() {
    ArrayList<Personne> ap = new ArrayList<>();
    try {
  
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM APP.PERSONNE");

      while (rs.next()) {
        Personne p = new Personne();
        p.setNom(rs.getString("NOM"));
        p.setAge(rs.getInt("age"));
        p.setGenrem(rs.getBoolean("genrem"));
        p.setPrenom(rs.getString("prenom"));
        ap.add(p);
      }
      closeConnection();
    } catch (SQLException ex) {
      Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    return ap;

  }
  
  
  public void dellById(int idd){
      try {
      conn.createStatement().execute("DELETE FROM APP.PERSONNE WHERE ID="+idd);
    } catch (SQLException ex) {
      Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    closeConnection();
  }

  public void clear() {
    try {
      conn.createStatement().execute("DELETE FROM APP.PERSONNE");
    } catch (SQLException ex) {
      Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    closeConnection();
  }

  private void closeConnection() {
    if (null != conn) {
      try {
        if(null != stmt)
          stmt.close();
        conn.close();
      } catch (SQLException ex) {
        Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  public Personne findById(int idf) {
    Personne p = null;
    try {
      
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM APP.PERSONNE WHERE ID = "+idf);
      while (rs.next()) {
        p = new Personne(
          rs.getInt("id"),
          rs.getString("prenom"),
          rs.getString("nom"),
          rs.getBoolean("genrem"),
          rs.getInt("age")
        );

      }

    } catch (SQLException ex) {
      Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

    return p;

  }

  public void createTable() throws SQLException {
    conn.createStatement().execute(""
      + "CREATE TABLE PERSONNE\n"
      + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
      + "prenom VARCHAR(20) NOT NULL,\n"
      + "nom VARCHAR(30),\n"
      + "genrem boolean,\n"
      + "age INTEGER,\n"
      + "CONSTRAINT primary_key PRIMARY KEY (id)\n"
      + ") ;");

  }

  public void insert(Personne p) throws SQLException {
    conn.createStatement().execute("INSERT INTO APP.PERSONNE (NOM,PRENOM,AGE,GENREM) VALUES(\'" + p.getNom() + "\',\'" + p.getPrenom() + "\'," + p.getAge() + "," + p.getGenrem() + ")");

    conn.commit();
  }

  public ArrayList<Personne> getBetween(int min, int max) {
    ArrayList<Personne> ap = new ArrayList<>();
    try {
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM APP.PERSONNE WHERE AGE BETWEEN " + min + " AND " + max);

      while (rs.next()) {
        Personne p = new Personne();
        p.setNom(rs.getString("NOM"));
        p.setAge(rs.getInt("age"));
        p.setGenrem(rs.getBoolean("genrem"));
        p.setPrenom(rs.getString("prenom"));
        ap.add(p);
      }
      stmt.close();
      closeConnection();

    } catch (SQLException ex) {
      Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return ap;
  }

}
