package GUI;

import Core.Objects.Subject;
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
public class TeacherForm implements IForm{
    private JPanel panel1;
    private JList listTeacher;
    private JButton saveButton;
    private JButton exitButton;
    private JTextField fNameTextField;
    private JTextField sNameTextField;
    private JButton addButton;
    private JButton removeButton;

    private IPanelForm generalForm;
    private List<Map<String, Object>> localData;
    private JFrame frame;

    public TeacherForm() {
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
        listTeacher.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int index = listTeacher.getSelectedIndex();
                if(index == -1)
                    return;

                fNameTextField.setText((String) localData.get(index).get("sName"));
                sNameTextField.setText((String) localData.get(index).get("fName"));
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
        int selIndex = listTeacher.getSelectedIndex();
        if(selIndex!= -1){
            Map<String, Object> sendDat= localData.get(selIndex);
            int obId = (int)sendDat.get("id");
            generalForm.RemoveFormData(obId,Types.teacher);

            Init(generalForm.GetData(Types.teacher));
        }
    }
    private void AddButton(){
        String sName = sNameTextField.getText();
        String fName = fNameTextField.getText();


        Map<String, Object> sendData;


        sendData = new HashMap<>();
        sendData.put("sName",sName);
        sendData.put("fName",fName);
        sendData.put("id",-1);

        sendData.put("Type", Types.teacher);
        generalForm.SendFormData(sendData);

        Init(generalForm.GetData(Types.teacher));
    }

    private void SaveFormButton(){
        int selIndex = listTeacher.getSelectedIndex();

        String sName = sNameTextField.getText();
        String fName = fNameTextField.getText();


        Map<String, Object> sendData;

        if(selIndex == -1){
            sendData = new HashMap<>();
            sendData.put("sName",sName);
            sendData.put("fName",fName);
            sendData.put("id",selIndex);
        }else{
            sendData = localData.get(selIndex);
            sendData.put("sName",sName);
            sendData.put("fName",fName);
        }
        sendData.put("Type", Types.teacher);
        generalForm.SendFormData(sendData);

        Init(generalForm.GetData(Types.teacher));
    }
    private void ExitFormButton(){
        frame.dispose();
    }

    @Override
    public void ShowForm() {
        frame = new JFrame("TeacherForm");
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
            names[k] = (String) data.get(k).get("fName") +" "+ (String) data.get(k).get("sName");
        }

        listTeacher.setListData(names);

    }

}
