package Core;

import Core.Objects.Group;
import Core.Objects.Room;
import Core.Objects.Subject;
import Core.Objects.Teacher;
import Model.Core.IDataController;
import Model.Core.IEngine;
import Model.Enumes.SubjectTypes;
import Model.Enumes.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Model.Enumes.Types.*;

/**
 * Created by micp on 01.12.16.
 */
public class Engine implements IEngine {

    private DataFile data;
    IDataController dataController;


    public Engine(){
        dataController = new JSONController("JSONData.jd");
        data = dataController.LoadAllData();
    }

    @Override
    public void SaveData(Types type, Map<String, Object> form) {

        //new object
        if ((int)form.get("id") == -1 ){

            switch (type){
                case gropu:
                    Group groupk = new Group(GetActualId(gropu),(String)form.get("name"),(SubjectTypes)form.get("type"));
                    data.groups.add(groupk);
                    break;
                case room:
                    Room roomk = new Room(GetActualId(room),(String)form.get("name"),(SubjectTypes)form.get("type"));
                    data.rooms.add(roomk);
                    break;
                case subject:
                    Subject subjectk = new Subject(GetActualId(subject),(String)form.get("name"),(SubjectTypes)form.get("type"));
                    data.subjects.add(subjectk);
                    break;
                case teacher:
                    Teacher teacherk = new Teacher(GetActualId(teacher),(String)form.get("sName"),(String)form.get("fName"));
                    data.teachers.add(teacherk);
                    break;
            }

        }else{//edit object

            switch (type){
                case gropu:
                    Group group = data.groups.stream().filter(p->p.Id == (int)form.get("id")).findFirst().orElse(null);
                    data.groups.remove(group);
                    group.name = (String)form.get("name");
                    group.type = (SubjectTypes)form.get("type");
                    data.groups.add(group);
                    break;
                case room:
                    Room room = data.rooms.stream().filter(p->p.Id == (int)form.get("id")).findFirst().orElse(null);
                    data.rooms.remove(room);
                    room.name = (String)form.get("name");
                    room.type = (SubjectTypes)form.get("type");
                    data.rooms.add(room);
                    break;
                case subject:
                    Subject subject = data.subjects.stream().filter(p->p.Id == (int)form.get("id")).findFirst().orElse(null);
                    data.subjects.remove(subject);
                    subject.name = (String)form.get("name");
                    subject.type = (SubjectTypes)form.get("type");
                    data.subjects.add(subject);
                    break;
                case teacher:
                    Teacher teacher = data.teachers.stream().filter(p->p.Id == (int)form.get("id")).findFirst().orElse(null);
                    data.teachers.remove(teacher);
                    teacher.sName = (String)form.get("sName");
                    teacher.fName = (String)form.get("fName");
                    data.teachers.add(teacher);
                    break;
            }


        }

        dataController.SaveData(data);
    }

    public int GetActualId(Types type){
        int res = 0;
        int count = 0;
        boolean reFlag = false;
        switch (type){
            case gropu:
                do{
                    res = data.groups.size() + count++;
                    reFlag = false;
                    for (Group g :data.groups) {
                        if (g.Id == res){
                            reFlag = true;
                            break;
                        }
                    }
                }while(reFlag);

                break;
            case room:
                do{
                    res = data.rooms.size() + count++;
                    reFlag = false;
                    for (Room g :data.rooms) {
                        if (g.Id == res){
                            reFlag = true;
                            break;
                        }
                    }
                }while(reFlag);
                break;
            case subject:
                do{
                    res = data.subjects.size() + count++;
                    reFlag = false;
                    for (Subject g :data.subjects) {
                        if (g.Id == res){
                            reFlag = true;
                            break;
                        }
                    }
                }while(reFlag);
                break;
            case teacher:
                do{
                    res = data.teachers.size() + count++;
                    reFlag = false;
                    for (Teacher g :data.teachers) {
                        if (g.Id == res){
                            reFlag = true;
                            break;
                        }
                    }
                }while(reFlag);
                break;
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> LoadData(Types type) {
        //konwersja zwrotna
        List<Map<String, Object>> result = new ArrayList<>();

        switch (type){
            case gropu:
                for (Group group:data.groups) {
                    Map<String, Object> map= new HashMap<>();
                    map.put("name",group.name);
                    map.put("type",group.type);
                    map.put("id",group.Id);

                    result.add(map);
                }
                break;
            case room:
                for (Room room:data.rooms) {
                    Map<String, Object> map= new HashMap<>();
                    map.put("name",room.name);
                    map.put("type",room.type);
                    map.put("id",room.Id);

                    result.add(map);
                }
                break;
            case subject:
                for (Subject subject:data.subjects) {
                    Map<String, Object> map= new HashMap<>();
                    map.put("name",subject.name);
                    map.put("type",subject.type);
                    map.put("id",subject.Id);

                    result.add(map);
                }
                break;
            case teacher:
                for (Teacher teacher:data.teachers) {
                    Map<String, Object> map= new HashMap<>();
                    map.put("sName",teacher.sName);
                    map.put("fName",teacher.fName);
                    map.put("id",teacher.Id);

                    result.add(map);
                }
                break;
        }
        return result;
    }

    @Override
    public void RemoveObject(Types type, int id) {
        switch (type){
            case gropu:
                Group group = data.groups.stream().filter(p->p.Id == id).findFirst().orElse(null);
                if(group != null){
                    data.groups.remove(group);
                }
                break;
            case room:
                Room room = data.rooms.stream().filter(p->p.Id == id).findFirst().orElse(null);
                if(room != null){
                    data.rooms.remove(room);
                }
                break;
            case subject:
                Subject subject = data.subjects.stream().filter(p->p.Id == id).findFirst().orElse(null);
                if(subject != null){
                    data.subjects.remove(subject);
                }
                break;
            case teacher:
                Teacher teacher = data.teachers.stream().filter(p->p.Id == id).findFirst().orElse(null);
                if(teacher != null){
                    data.teachers.remove(teacher);
                }
                break;
        }
        dataController.SaveData(data);
    }


}
