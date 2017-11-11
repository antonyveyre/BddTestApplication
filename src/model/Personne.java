/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author user
 */

public class Personne implements Serializable {

  private static final long serialVersionUID = 1L;
  private Integer id;
  private String prenom;
  private String nom;
  private Boolean genrem;
  private Integer age;

  public Personne() {
  }

  public Personne(Integer id) {
    this.id = id;
  }

  public Personne(int id,String prenom, String nom, Boolean genrem, Integer age) {
    this.id = id;
    this.prenom = prenom;
    this.nom = nom;
    this.genrem = genrem;
    this.age = age;
  }

  public Personne(String prenom, String nom, Boolean genrem, Integer age) {
    this.prenom = prenom;
    this.nom = nom;
    this.genrem = genrem;
    this.age = age;
  }

  
  
  public Personne(Integer id, String prenom) {
    this.id = id;
    this.prenom = prenom;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Boolean getGenrem() {
    return genrem;
  }

  public void setGenrem(Boolean genrem) {
    this.genrem = genrem;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Personne)) {
      return false;
    }
    Personne other = (Personne) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
    {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "model.Personne age " + this.age + " nom : " + this.nom + " prenom : " + this.prenom;
  }
  
}
