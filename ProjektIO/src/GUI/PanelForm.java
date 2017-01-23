package GUI;

import Core.Objects.Group;
import Model.Core.IEngine;
import Model.Enumes.SubjectTypes;
import Model.Enumes.Types;
import Model.GUI.IPanelForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * Created by micp on 01.12.16.
 */
public class PanelForm implements IPanelForm {
    private JPanel generalPanel;
    private JButton groupButton;
    private JButton roomButton;
    private JButton subjectButton;
    private JButton teacherButton;
    private IEngine engine;
    private IPanelForm myPanel;


    public void initialize() {
        JFrame frame = new JFrame("PanelForm");
        frame.setContentPane(this.generalPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PanelForm(IEngine engine) {
        this.engine = engine;
        this.myPanel = this;

        groupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GroupForm groupForm = new GroupForm();
                groupForm.InitializeForm(myPanel,engine.LoadData(Types.gropu));
                groupForm.ShowForm();
            }
        });
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RoomForm roomForm = new RoomForm();
                roomForm.InitializeForm(myPanel,engine.LoadData(Types.room));
                roomForm.ShowForm();
            }
        });
        subjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SubjectForm subjectForm = new SubjectForm();
                subjectForm.InitializeForm(myPanel,engine.LoadData(Types.subject));
                subjectForm.ShowForm();
            }
        });
        teacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TeacherForm teacherForm = new TeacherForm();
                teacherForm.InitializeForm(myPanel,engine.LoadData(Types.teacher));
                teacherForm.ShowForm();
            }
        });

    }

    @Override
    public void SendFormData(Map<String, Object> data) {
        engine.SaveData((Types) data.get("Type"),data);
    }

    @Override
    public void RemoveFormData(int id, Types type) {
        engine.RemoveObject(type,id);
    }

    @Override
    public List<Map<String, Object>> GetData(Types type) {
        switch (type){
            case gropu:
                return engine.LoadData(Types.gropu);
            case room:
                return engine.LoadData(Types.room);
            case subject:
                return engine.LoadData(Types.subject);
            case teacher:
                return engine.LoadData(Types.teacher);
        }
        return null;
    }


}
