package GUI;

import Model.Enumes.SubjectTypes;
import Model.Enumes.Types;
import Model.GUI.IForm;
import Model.GUI.IPanelForm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by micp on 01.12.16.
 */
public class RoomForm implements IForm{


    private JPanel generalPanel;
    private JButton exitButton;
    private JButton saveButton;
    private JTextField nameField;
    private JComboBox typeComboBox;
    private JList listRoom;
    private JButton addButton;
    private JButton removeButton;

    private IPanelForm generalForm;
    private List<Map<String, Object>> localData;
    private JFrame frame;

    public RoomForm() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    AddButton();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SaveFormButton();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ExitFormButton();
            }
        });
        listRoom.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int index = listRoom.getSelectedIndex();
                if(index == -1)
                    return;

                nameField.setText((String) localData.get(index).get("name"));
                typeComboBox.setSelectedItem(localData.get(index).get("type"));
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RemoveFormButton();
            }
        });
    }
    private void RemoveFormButton(){
        int selIndex = listRoom.getSelectedIndex();
        if(selIndex!= -1){
            Map<String, Object> sendDat= localData.get(selIndex);
            int obId = (int)sendDat.get("id");
            generalForm.RemoveFormData(obId,Types.room);

            Init(generalForm.GetData(Types.room));
        }
    }

    private void AddButton(){
        String name = nameField.getText();
        SubjectTypes type = (SubjectTypes) typeComboBox.getSelectedItem();

        Map<String, Object> sendData;


        sendData = new HashMap<>();
        sendData.put("name",name);
        sendData.put("type",type);
        sendData.put("id",-1);

        sendData.put("Type", Types.room);
        generalForm.SendFormData(sendData);

        Init(generalForm.GetData(Types.room));
    }

    private void SaveFormButton(){
        int selIndex = listRoom.getSelectedIndex();
        String name = nameField.getText();
        SubjectTypes type = (SubjectTypes) typeComboBox.getSelectedItem();

        Map<String, Object> sendData;
        if(selIndex == -1){
            sendData = new HashMap<>();
            sendData.put("name",name);
            sendData.put("type",type);
            sendData.put("id",selIndex);
        }else{
            sendData = localData.get(selIndex);
            sendData.put("name",name);
            sendData.put("type",type);
        }
        sendData.put("Type", Types.room);
        generalForm.SendFormData(sendData);

        Init(generalForm.GetData(Types.room));
    }
    private void ExitFormButton(){
        frame.dispose();
    }

    @Override
    public void ShowForm() {
        frame = new JFrame("RoomForm");
        frame.setContentPane(this.generalPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void InitializeForm(IPanelForm generalForm,List<Map<String, Object>> data) {
        this.generalForm = generalForm;
        Init(data);
    }

    private void Init(List<Map<String, Object>> data){
        this.localData = data;

        String[] names = new String[data.size()];
        for (int k =0;k<data.size();k++) {
            names[k] = (String) data.get(k).get("name");
        }

        listRoom.setListData(names);
        typeComboBox.setModel(new DefaultComboBoxModel<>(SubjectTypes.values()));
    }
}
