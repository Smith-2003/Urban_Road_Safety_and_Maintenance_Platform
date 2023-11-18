package yuguerten.net.bean;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Employe {

    private int Matricule;
    private String Nom;
    private String Prenom;
    private String specialite;
    private Date dateEmb;
    private String sexe;

    public Employe(int matricule, String nom, String prenom, String specialite, Date dateEmb, String sexe) {
        this.Matricule = matricule;
        this.Nom = nom;
        this.Prenom = prenom;
        this.specialite = specialite;
        this.dateEmb = dateEmb;
        this.sexe = sexe;
    }
    public Employe(){}

    public int getMatricule() {
        return Matricule;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getSpecialite() {
        return  specialite;
    }

    public Date getDateEmb() {
        return  dateEmb;
    }

    public String getSexe() {
        return sexe;
    }


    public void setMatricule(int matricule) {
        Matricule = matricule;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setDateEmb(Date dateEmb) {
        this.dateEmb = dateEmb;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "le numéro de matricule est : "+Matricule;
    }
}
