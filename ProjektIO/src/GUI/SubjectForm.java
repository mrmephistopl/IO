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
public class SubjectForm implements IForm {
    private JPanel panel1;
    private JList listSubject;
    private JTextField nameField;
    private JComboBox typeComboBox;
    private JButton exitButton;
    private JButton saveButton;
    private JButton addButton;
    private JButton removeButton;

    private IPanelForm generalForm;
    private List<Map<String, Object>> localData;
    private JFrame frame;


    public SubjectForm() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddButton();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ExitFormButton();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SaveFormButton();
            }
        });
        listSubject.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int index = listSubject.getSelectedIndex();
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
        int selIndex = listSubject.getSelectedIndex();
        if(selIndex!= -1){
            Map<String, Object> sendDat= localData.get(selIndex);
            int obId = (int)sendDat.get("id");
            generalForm.RemoveFormData(obId,Types.subject);

            Init(generalForm.GetData(Types.subject));
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

        sendData.put("Type", Types.subject);
        generalForm.SendFormData(sendData);

        Init(generalForm.GetData(Types.subject));
    }

    private void SaveFormButton(){
        int selIndex = listSubject.getSelectedIndex();
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
        sendData.put("Type", Types.subject);
        generalForm.SendFormData(sendData);

        Init(generalForm.GetData(Types.subject));
    }
    private void ExitFormButton(){
        frame.dispose();
    }

    @Override
    public void ShowForm() {
        frame = new JFrame("SubjectForm");
        frame.setContentPane(this.panel1);
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

        listSubject.setListData(names);
        typeComboBox.setModel(new DefaultComboBoxModel<>(SubjectTypes.values()));

    }


}
