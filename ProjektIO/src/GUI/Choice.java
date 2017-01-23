package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Core.IEngine;
import Model.Enumes.Types;
import org.codehaus.jackson.map.ObjectMapper;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import java.util.ArrayList;
import java.io.PrintWriter;

/**
 *
 * @author Michał Stęplewski
 */
public class Choice extends JFrame {
    public static final int height = 270+25;
    public static final int width = 785+5;
    public JLayeredPane lp;
    List<Map<String, Object>> list;
    List<Map<String, Object>> tmp=new ArrayList<Map<String, Object>>();
    public JComboBox listaT;
    public IEngine engine;
    public JComboBox[] listaS;
    public JLabel[] dayName;
    public JCheckBox[] check;
    public String[] dni={"poniedziałek", "wtorek", "sroda","czwartek","piatek"};
    public String[] godziny={
            "08:00-9:30",
            "09:45-11:15",
            "11:30-13:00",
            "13:15-14:45",
            "15:00-16:30",
            "16:45-18:15",
            "18:30-20:00"};
    public JButton przyciskZapisz;
    public JButton przyciskPorzuc;
    HandlerClass handler;

    Choice(IEngine engine, List<Map<String, Object>> preferences){
        super("Preferencje");
        //pobieranie szerokosci/wysokosci ekranu użytkownika
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        // Ustawienia ramki
        setBounds(screenWidth/2 - width/2, (screenHeight/2-height/2), width, height);
        setSize(width,height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        lp = getLayeredPane();
        list=preferences;
        copyList(tmp,preferences);

        this.engine=engine;
        listaT=new JComboBox();
        listaT.setBounds(340,20, 125, 20);
        List<Map<String, Object>> localData = engine.LoadData(Types.teacher);
        int index=localData.size();
        for(;;) {
            index--;
            if (index==-1)
                break;
            listaT.addItem("T: "+(String) localData.get(index).get("sName")+" "+(String) localData.get(index).get("fName"));

        }
        localData = engine.LoadData(Types.gropu);
        index=localData.size()-1;
        for(;;) {
            index--;
            if (index==-1)
                break;
            listaT.addItem((String)localData.get(index).get("name"));

        }
        lp.add(listaT);


        // Dodawanie checkboxow
        int days=5;
        int hours=7;
        listaS=new JComboBox[35];
        check=new JCheckBox[35];
        dayName=new JLabel[5];
        for (int j=0; j<days; j++){
            dayName[j]=new JLabel(dni[j]);
            dayName[j].setBounds(25+150*j,50, 100, 20);
            lp.add(dayName[j]);
            for (int i=0; i<hours; i++){
                check[j*7+i]=new JCheckBox(godziny[i]);
                check[j*7+i].setBounds(10+150*j,70+20*i, 100, 20);
                lp.add(check[j*7+i]);
                /////////////////////
                listaS[j*7+i]=new JComboBox();
                listaS[j*7+i].setBounds(110+150*j,70+20*i, 50, 20);
                localData = engine.LoadData(Types.room);
                index=localData.size();
                listaS[j*7+i].addItem("-1");//-1 oznacza brak preferencji co do sali
                for(;;) {
                    index--;
                    if (index==-1)
                        break;
                    listaS[j*7+i].addItem((String) localData.get(index).get("name"));

                }
                lp.add(listaS[j*7+i]);
            }
        }

        przyciskZapisz=new JButton("Zapisz");
        przyciskZapisz.setBounds(450,220, 120, 30);
        lp.add(przyciskZapisz);

        przyciskPorzuc=new JButton("Porzuć zmiany");
        przyciskPorzuc.setBounds(200,220, 120, 30);
        lp.add(przyciskPorzuc);

        handler = new HandlerClass();
        listaT.addActionListener(handler);
        przyciskZapisz.addActionListener(handler);
        przyciskPorzuc.addActionListener(handler);
        for(int i=0; i<35; i++){
            check[i].addActionListener(handler);
            listaS[i].addActionListener(handler);
        }
        generateList();
    }
    private class HandlerClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            if (event.getSource()==listaT){
                changeUser();
            }
            else if (event.getSource()==przyciskZapisz){
                saveFile("JSONdataWybor.jd");

                dispose();
            }
            else if (event.getSource()==przyciskPorzuc){
                copyList(list,tmp);
                dispose();
            }
            else{
                update();
            }
        }
    }
    private void update(){
        for (Map<String, Object> mapT : list) {
            if (mapT.containsValue(listaT.getSelectedItem().toString()))
            {
                int wage;
                if (listaT.getSelectedItem().toString().charAt(0)=='T')
                    wage=2;
                else
                    wage=1;

                String prefers;
                String sale;
                for(int i=0;i<5;i++) {
                    prefers="";
                    sale="";
                    for(int j=0; j<7;j++){
                        if(check[i*7+j].isSelected()) {
                            prefers += wage;
                            sale+=listaS[i*7+j].getSelectedItem().toString()+" ";
                        }
                        else {
                            prefers += 0;
                            sale+="-1 ";
                        }
                    }
                    mapT.put("Sale "+dni[i], sale);
                    mapT.put(dni[i],prefers);
                }
            }
        }

    }
    void saveFile(String name){
        update();
        try{
            PrintWriter writer = new PrintWriter(name, "UTF-8");
            String json = new ObjectMapper().writeValueAsString(list);
            writer.println(json);
            writer.close();
        } catch (IOException e) {
            // do something
        }
    }
    void generateList(){
        Map<String, Object> map;
        List<Map<String, Object>> localData = engine.LoadData(Types.teacher);

        String prefers;
        String sale;

        int index=localData.size();
        for(;;) {

            index--;
            if (index == -1)
                break;
            map = new HashMap<>();
            map.put("Name", "T: " + (String) localData.get(index).get("sName") + " " + (String) localData.get(index).get("fName"));

            for(int i=0;i<5;i++) {
                prefers="";
                sale="";
                for(int j=0; j<7;j++){
                    if(check[i*7+j].isSelected()) {
                        prefers += 0;
                        sale += "-1";
                    }
                }
                map.put("Sale "+dni[i], sale);
                map.put(dni[i],prefers);
            }
            list.add(map);
        }

        localData = engine.LoadData(Types.gropu);
        index=localData.size()-1;
        for(;;) {
            index--;
            if (index == -1)
                break;
            map = new HashMap<>();
            map.put("Name",(String)localData.get(index).get("name"));

            for(int i=0;i<5;i++) {
                prefers="";
                sale="";
                for(int j=0; j<7;j++){
                    if(check[i*7+j].isSelected()) {
                        prefers += 0;
                        sale += -1;
                    }
                }
                map.put("Sale "+dni[i], sale);
                map.put(dni[i],prefers);
            }
            list.add(map);
        }
    }
    void changeUser(){
        for(int i=0; i<35; i++){
            check[i].setSelected(false);
            listaS[i].setSelectedIndex(0);
        }
    }
    void copyList(List<Map<String, Object>> w,List<Map<String, Object>> q){
        w.clear();
        for (Map<String, Object> mapT : q) {
            Map<String, Object> map = new HashMap<>();
            for (Map.Entry<String, Object> entry : mapT.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                map.put(key, value);
            }
            w.add(map);
        }
    }

}
