package yuguerten.net.ui;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Recherche {
    private JPanel rootPanel;
    private JDateChooser DateDebut;
    private JDateChooser DateFin;
    private JPanel ReceherchePanel;
    private JTable searchResult;
    public JPanel getRootPanel() {
        return rootPanel;
    }
    public Recherche(){
        createTable();
    }
    private void createUIComponents() {
        DateDebut = new JDateChooser();
        DateFin = new JDateChooser();
        DateDebut.setDateFormatString("dd/MM/yyyy");
        DateFin.setDateFormatString("dd/MM/yyyy");
    }

    private void createTable(){
        searchResult.setModel(new DefaultTableModel(
                null,
                new String []{"Matricule", "Prenom", "Nom", "Spécialite", "Sexe"}
        ));

    }
}
