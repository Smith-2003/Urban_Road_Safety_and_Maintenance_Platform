package yuguerten.net;

import yuguerten.net.bean.Employe;
import yuguerten.net.connexion.Connexion;
import yuguerten.net.service.EmployeService;
import yuguerten.net.ui.Gestion;
import yuguerten.net.ui.Recherche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;

        try {
            date = dateFormat.parse("05/15/2019");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Employe e = new Employe(12,"ali","amine","java",date,"Male");
        EmployeService service = new EmployeService();
//        service.create(e);
        Employe e1 =  service.findByMatricule(12);
        System.out.println(e1);






    }
    private static void createGUI(){

        JFrame frame = new JFrame("Application de Gestion d'Employés");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        int frameWidth = 600;
        int frameHeight = 400;

        frame.setSize(frameWidth, frameHeight);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frameWidth) / 2;
        int y = (screenSize.height - frameHeight) / 2;

        frame.setLocation(x, y);

        JMenu gestionMenu = new JMenu("Gestion");
        menuBar.add(gestionMenu);

        JMenuItem gestionEmployesItem = new JMenuItem("Employe");
        JMenuItem exitItem = new JMenuItem("Exit");
        gestionMenu.add(gestionEmployesItem);
        gestionMenu.add(exitItem);
        menuBar.add(gestionMenu);

        JMenuItem rechercheItem = new JMenuItem("Recherche");
        menuBar.add(rechercheItem);

        gestionEmployesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lorsqu'on clique sur "Gestion des Employés"
                afficherGestionEmployes();
            }
        });

        rechercheItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherRechercherEmployes();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);


    }
    private static void afficherGestionEmployes(){
        Gestion ui = new Gestion();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    private static void afficherRechercherEmployes(){
        Recherche ui = new Recherche();
        JPanel root = ui.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}