package yuguerten.net.service;

import yuguerten.net.bean.Employe;
import yuguerten.net.connexion.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeService {
    public boolean create(Employe e) {
        try {
            Connection cnx = Connexion.getConnection();
            String sql = "INSERT INTO employee (Matricule, Nom, Prénom, Spécialité, DateEmbauche, Sexe) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setInt(1, e.getMatricule());
            ps.setString(2, e.getNom());
            ps.setString(3, e.getPrenom());
            ps.setString(4, e.getSpecialite());
            ps.setDate(5, new Date(e.getDateEmb().getTime()));
            ps.setString(6, e.getSexe());
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public boolean delete(Employe e) {
        try {

            Connection cnx = Connexion.getConnection();

            String sql = "DELETE FROM employee WHERE Matricule = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);

            ps.setInt(1, e.getMatricule());
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException ex) {

            ex.printStackTrace();
            return false;
        }
    }
    public boolean update(Employe e) {
        try {

            Connection cnx = Connexion.getConnection();

            String sql = "UPDATE employee SET Nom = ?, Prénom = ?, Spécialité = ?, DateEmbauche = ?, Sexe = ? WHERE Matricule = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);

            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getSpecialite());
            ps.setDate(4, new Date(e.getDateEmb().getTime()));
            ps.setString(5, e.getSexe());
            ps.setInt(6, e.getMatricule());

            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException ex) {

            ex.printStackTrace();
            return false;
        }
    }
    public Employe findByMatricule(int matricule) {
        try {

            Connection cnx = Connexion.getConnection();

            String sql = "SELECT * FROM employee WHERE Matricule = ?";
            PreparedStatement ps = cnx.prepareStatement(sql);

            ps.setInt(1, matricule);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employe e = new Employe();
                e.setMatricule(rs.getInt("Matricule"));
                e.setNom(rs.getString("Nom"));
                e.setPrenom(rs.getString("Prénom"));
                e.setSpecialite(rs.getString("Spécialité"));
                e.setDateEmb(rs.getDate("DateEmbauche"));
                e.setSexe(rs.getString("Sexe"));
                return e;
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException ex) {

            ex.printStackTrace();
            return null;
        }
    }
    public List<Employe> findAll() {
        try {
            Connection cnx = Connexion.getConnection();

            String sql = "SELECT * FROM employee";
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(sql);

            List<Employe> list = new ArrayList<>();
            while (rs.next()) {
                Employe e = new Employe();
                e.setMatricule(rs.getInt("Matricule"));
                e.setNom(rs.getString("Nom"));
                e.setPrenom(rs.getString("Prénom"));
                e.setSpecialite(rs.getString("Spécialité"));
                e.setDateEmb(rs.getDate("DateEmbauche"));
                e.setSexe(rs.getString("Sexe"));
                list.add(e);
            }
            return list;
        } catch (SQLException | ClassNotFoundException ex) {

            ex.printStackTrace();
            return null;
        }
    }
    public List<Employe> findByCritere(String specialite, String nom, int matricule) {
        try {
            Connection cnx = Connexion.getConnection();

            String sql = "SELECT * FROM employee WHERE 1=1";
            if (specialite != null && !specialite.isEmpty()) {
                sql += " AND Spécialité = '" + specialite + "'";
            }
            if (nom != null && !nom.isEmpty()) {
                sql += " AND Nom LIKE '%" + nom + "%'";
            }
            if (matricule > 0) {
                sql += " AND Matricule = " + matricule;
            }
            Statement st = cnx.createStatement();

            ResultSet rs = st.executeQuery(sql);

            List<Employe> list = new ArrayList<>();
            while (rs.next()) {
                Employe e = new Employe();
                e.setMatricule(rs.getInt("Matricule"));
                e.setNom(rs.getString("Nom"));
                e.setPrenom(rs.getString("Prénom"));
                e.setSpecialite(rs.getString("Spécialité"));
                e.setDateEmb(rs.getDate("DateEmbauche"));
                e.setSexe(rs.getString("Sexe"));
                list.add(e);
            }
            return list;
        } catch (SQLException ex) {

            ex.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Employe> findBetweenDate(Date d1, Date d2) {
        try {
            Connection cnx = Connexion.getConnection();

            String sql = "SELECT * FROM employee WHERE DateEmbauche BETWEEN ? AND ?";
            PreparedStatement ps = cnx.prepareStatement(sql);
            ps.setDate(1, d1);
            ps.setDate(2, d2);
            ResultSet rs = ps.executeQuery();
            List<Employe> list = new ArrayList<>();
            while (rs.next()) {
                Employe e = new Employe();
                e.setMatricule(rs.getInt("Matricule"));
                e.setNom(rs.getString("Nom"));
                e.setPrenom(rs.getString("Prénom"));
                e.setSpecialite(rs.getString("Spécialité"));
                e.setDateEmb(rs.getDate("DateEmbauche"));
                e.setSexe(rs.getString("Sexe"));
                list.add(e);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }





}
