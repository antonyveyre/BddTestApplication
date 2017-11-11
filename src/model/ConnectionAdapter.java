/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/**
 *
 * @author user
 */
public class ConnectionAdapter extends TypeAdapter{

  @Override
  public void write(JsonWriter out, Object value) throws IOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public MyConnection read(JsonReader in) throws IOException {

    MyConnection myConn = new MyConnection();
     in.beginObject();
    while (in.hasNext()) {
      switch (in.nextName()) {
      case "url":
        myConn.setUrl(in.nextString());
        break;
      case "user":
        myConn.setUser(in.nextString());
        break;
      case "password":
        myConn.setPassword(in.nextString());
        break;
      }
    }
    in.endObject();
    
    return myConn;
  }
  
}
