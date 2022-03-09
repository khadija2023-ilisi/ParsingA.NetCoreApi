package com.example.parsingmyjson;

import java.util.ArrayList;
import java.util.List;

public class Etudiant<string> {
    public int id ;
    public string nom ;
    public string prenom;
    public string image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public string getNom() {
        return nom;
    }

    public void setNom(string nom) {
        this.nom = nom;
    }

    public string getPrenom() {
        return prenom;
    }

    public void setPrenom(string prenom) {
        this.prenom = prenom;
    }

    public string getImage() {
        return image;
    }

    public void setImage(string image) {
        this.image = image;
    }

    public List<Etudiant> getAmis() {
        return amis;
    }

    public void setAmis(List<Etudiant> amis) {
        this.amis = amis;
    }

    public List<Etudiant> amis ;
    public Etudiant(int i, string n, string m,string image)
    {
        this.nom = n;
        this.prenom = m;
        this.image=image;
        amis = new ArrayList<Etudiant>();
    }
    public Etudiant() { }

    public void AddAmis(Etudiant e)
    {
        this.amis.add(e);
    }
    public string print()
    {
        return (string) (this.nom + " " + this.prenom + " "+this.image);
    }
}
