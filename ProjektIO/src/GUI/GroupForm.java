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
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by micp on 01.12.16.
 */
public class GroupForm implements IForm{


    private JPanel generalPanel;
    private JList listGroup;
    private JTextField nameField;
    private JComboBox typeComboBox;
    private JButton saveButton;
    private JButton exitButton;
    private JButton addButton;
    private JButton removeButton;

    private IPanelForm generalForm;
    private List<Map<String, Object>> localData;
    private JFrame frame;

    public GroupForm() {
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
        listGroup.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int index = listGroup.getSelectedIndex();
                if(index == -1)
                    return;

                nameField.setText((String) localData.get(index).get("name"));
                typeComboBox.setSelectedItem(localData.get(index).get("type"));
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddButton();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RemoveFormButton();
            }
        });
    }
    private void AddButton(){
        String name = nameField.getText();
        SubjectTypes type = (SubjectTypes) typeComboBox.getSelectedItem();

        Map<String, Object> sendData;


        sendData = new HashMap<>();
        sendData.put("name",name);
        sendData.put("type",type);
        sendData.put("id",-1);

        sendData.put("Type", Types.gropu);
        generalForm.SendFormData(sendData);

        Init(generalForm.GetData(Types.gropu));
    }

    private void RemoveFormButton(){
        int selIndex = listGroup.getSelectedIndex();
        if(selIndex!= -1){
            Map<String, Object> sendDat= localData.get(selIndex);
            int obId = (int)sendDat.get("id");
            generalForm.RemoveFormData(obId,Types.gropu);

            Init(generalForm.GetData(Types.gropu));
        }
    }

    private void SaveFormButton(){
        int selIndex = listGroup.getSelectedIndex();
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
        sendData.put("Type", Types.gropu);
        generalForm.SendFormData(sendData);

        Init(generalForm.GetData(Types.gropu));
    }
    private void ExitFormButton(){
        frame.dispose();
    }

    @Override
    public void ShowForm() {
        frame = new JFrame("GroupForm");
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

        listGroup.setListData(names);
        typeComboBox.setModel(new DefaultComboBoxModel<>(SubjectTypes.values()));

    }
}
