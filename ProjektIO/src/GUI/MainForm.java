package GUI;

import Core.Engine;
import Core.GenereteTametable;
import Core.Objects.*;
import Model.Core.IEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by micp on 01.12.16.
 */
public class MainForm {
    private JPanel generalPanel;
    private JButton generalConfigurationButton;
    private JButton generateButton;
    private JButton edytujPreferencjeButton;
    private IEngine engine;
    List<Map<String, Object>> preferences = new ArrayList<Map<String, Object>>();//lista preferencji w hashmapach

    public MainForm(IEngine engine) {

        this.engine = engine;
        generalConfigurationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PanelForm panelForm = new PanelForm(engine);
                panelForm.initialize();
            }
        });
        //preferencje
        edytujPreferencjeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Choice preferencje=new Choice(engine,preferences);
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                List<Group> g= DataLoader.loadGroups();
                List<Room> r = DataLoader.loadRooms();
                try {
                    List<Teacher> t = DataLoader.loadTeachers(preferences);
                    JOptionPane.showMessageDialog(new JFrame(), GenereteTametable.generuj(g,r,t) );
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Brak przedmiotu! \n" +
                            "Przedmioty odpowiadaja prowadzacy w kolejnosci wpisywania\n" +
                    "upewnij sie ze dane zostaly wprowadzone poprawnie!");
                }

            }
        });
    }

    public static void main(String[] args) {
        IEngine engine = new Engine();
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm(engine).generalPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}
