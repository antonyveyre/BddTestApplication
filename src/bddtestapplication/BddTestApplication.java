/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bddtestapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.Personne;
import model.PersonneDAO;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConnectionAdapter;
import model.MyConnection;

/**
 *
 * @author user
 */
public class BddTestApplication {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    try {

      JsonReader reader;

      final GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.registerTypeAdapter(MyConnection.class, new ConnectionAdapter());
      gsonBuilder.setPrettyPrinting();

      final Gson gson = gsonBuilder.create();

      reader = gson.newJsonReader((Reader) new FileReader("/home/user/workspace/BddTestApplication/src/bddtestapplication/config.json"));

//    final MyConnection parsedConnection = gson.fromJson(reader, MyConnection.class);
      MyConnection[] wrapper = gson.fromJson(reader, MyConnection[].class);
      ArrayList<MyConnection> am = new ArrayList<>();
      int i = 0;
      while (i < wrapper.length) {
        am.add(wrapper[i]);
        i++;
      }

      ArrayList<Connection> ac = new ArrayList<>();

      am.forEach((w) -> {
        try {
          if (w.getUser().isEmpty()) {
            ac.add(DriverManager.getConnection(w.getUrl()));
          } else {
            ac.add(DriverManager.getConnection(w.getUrl(), w.getUser(), w.getPassword()));
          }

        } catch (SQLException ex) {
          System.out.println("---------------------------");
          System.out.println(ex.getMessage());
          System.out.println("---------------------------");
          Logger.getLogger(BddTestApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
      });

      ac.forEach((c) -> {

        System.out.println(c);

        ArrayList<Personne> ap = new PersonneDAO(c).getPersonnes();
        ap.forEach((p) -> {
          System.out.println(" nom :" + p.getNom());

        });
        System.out.println("----------------");
      });

    } catch (FileNotFoundException ex) {
      Logger.getLogger(BddTestApplication.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
