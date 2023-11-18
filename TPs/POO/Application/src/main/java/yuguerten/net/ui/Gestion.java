package yuguerten.net.ui;

import com.toedter.calendar.JDateChooser;
import yuguerten.net.bean.Employe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gestion {
    private JPanel rootPanel;
    private JPanel InformationsPanel;
    private JPanel actionsRecherchePanel;
    private JPanel Actions;
    private JPanel Recherche;
    private JTextField MatriculeTxt;
    private JTextField PrenomTxt;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField NomTxt;
    private JComboBox SpecialitecomboBox;
    private JButton ajouterButton;
    private JButton supprimerButton;
    private JButton modifierButton;
    private JCheckBox matriculeCheckBox;
    private JTextField MatriculeTxt2;
    private JCheckBox specialiteCheckBox;
    private JComboBox SpecialitecomboBox2;
    private JCheckBox nomCheckBox;
    private JTextField NomTxt2;
    private JTable EmployeTable;
    private JLabel MatriculeJLabel;
    private JLabel DateJLabel;
    private JLabel PrenomJLabel;
    private JLabel SexeJLabel;
    private JLabel NomJLabel;
    private JLabel SpecialiteJLabel;
    private JScrollPane EmployeScrollPane;
    private JDateChooser JDateChooser1;
    private List<Employe> employeeList = new ArrayList<>();


    public JPanel getRootPanel() {
        return rootPanel;
    }

    public Gestion(){
        createTable();
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Matricule = Integer.parseInt(MatriculeTxt.getText());
                String Prenom = PrenomTxt.getText();
                String Nom = NomTxt.getText();
                String Specialite = SpecialitecomboBox.getSelectedItem().toString();
                String sexe = maleRadioButton.isSelected() ? "Male" : "Female";
                java.sql.Date sqldate = new java.sql.Date(JDateChooser1.getDate().getTime());
                Employe employee = new Employe(Matricule, Prenom, Nom, Specialite, sqldate,sexe);
                employeeList.add(employee);
                DefaultTableModel model = (DefaultTableModel) EmployeTable.getModel();
                model.addRow(new Object[]{Matricule, Prenom, Nom, Specialite, sqldate, sexe});
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) EmployeTable.getModel();
                int selectedRow = EmployeTable.getSelectedRow();
                if (selectedRow >= 0) {
                    model.removeRow(selectedRow);
                    employeeList.remove(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "Please select a row to delete.", "Delete Row", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = EmployeTable.getSelectedRow();

                if (selectedRow >= 0) {
                    int Matricule = (int) EmployeTable.getValueAt(selectedRow, 0);
                    String Prenom = (String) EmployeTable.getValueAt(selectedRow, 1);
                    String Nom = (String) EmployeTable.getValueAt(selectedRow, 2);
                    String Specialite = (String) EmployeTable.getValueAt(selectedRow, 3);
                    java.sql.Date sqldate = (java.sql.Date) EmployeTable.getValueAt(selectedRow, 4);
                    String sexe = (String) EmployeTable.getValueAt(selectedRow, 5);

                    MatriculeTxt.setText(String.valueOf(Matricule));
                    PrenomTxt.setText(Prenom);
                    NomTxt.setText(Nom);
                    SpecialitecomboBox.setSelectedItem(Specialite);
                    JDateChooser1.setDate(new Date(sqldate.getTime()));
                    if (sexe.equals("Male")) {
                        maleRadioButton.setSelected(true);
                    } else {
                        femaleRadioButton.setSelected(true);
                    }
                    int confirmation = JOptionPane.showConfirmDialog(rootPanel, "Voulez-vous enregistrer les modifications ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        DefaultTableModel model = (DefaultTableModel) EmployeTable.getModel();
                        model.setValueAt(Integer.parseInt(MatriculeTxt.getText()), selectedRow, 0);
                        model.setValueAt(PrenomTxt.getText(), selectedRow, 1);
                        model.setValueAt(NomTxt.getText(), selectedRow, 2);
                        model.setValueAt(SpecialitecomboBox.getSelectedItem(), selectedRow, 3);
                        model.setValueAt(new java.sql.Date(JDateChooser1.getDate().getTime()), selectedRow, 4);
                        model.setValueAt(maleRadioButton.isSelected() ? "Male" : "Female", selectedRow, 5);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPanel, "Veuillez sélectionner un employé à modifier.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void createUIComponents() {
        JDateChooser1 = new JDateChooser();
        JDateChooser1.setDateFormatString("dd/MM/yyyy");

    }
    private void createTable(){
        EmployeTable.setModel(new DefaultTableModel(
                null,
                new String []{"Matricule", "Prenom", "Nom", "Spécialite", "Date d'embauche", "Sexe"}
        ));

    }
}
