package Core.Objects;

import Core.Engine;
import Model.Core.IEngine;
import Model.Enumes.SubjectTypes;
import Model.Enumes.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by User on 2016-12-14.
 */
public class DataLoader {



    public static  List<Group> loadGroups()
    {
        IEngine engine = new Engine();
        List<Group> groups = new ArrayList<Group>();
        List<Map<String, Object>>  mapList=engine.LoadData(Types.gropu);
        List<Object> tmp = new ArrayList<Object>();
        for(Map<String,Object> i:mapList){
            tmp.addAll((i.values()));
        }
        for(int i=0;i<tmp.size();i+=3) {
            switch (tmp.get(i+2).toString()) {
                case "lecture":
                    groups.add(new Group(Integer.parseInt(tmp.get(i+1).toString()), tmp.get(i+0).toString(), SubjectTypes.lecture));
                    break;
                case "lab":
                    groups.add(new Group(Integer.parseInt(tmp.get(i+1).toString()), tmp.get(i+0).toString(), SubjectTypes.lab));
                    break;
                case "auditorium":
                    groups.add(new Group(Integer.parseInt(tmp.get(i+1).toString()), tmp.get(i+0).toString(), SubjectTypes.auditorium));
                    break;

                case "project":
                    groups.add(new Group(Integer.parseInt(tmp.get(i+1).toString()), tmp.get(i+0).toString(), SubjectTypes.project));
                    break;
            }
        }
        return groups;
    }

    public static  List<Teacher> loadTeachers(List<Map<String, Object>> preferences)
    {
        IEngine engine = new Engine();
        List<Teacher> teachers = new ArrayList<Teacher>();
        List<Map<String, Object>>  mapList=engine.LoadData(Types.teacher);
        List<Object> tmp = new ArrayList<Object>();
        for(Map<String,Object> i:mapList){
            tmp.addAll((i.values()));
        }
        for(int i=0;i<tmp.size();i+=3) {
            teachers.add(new Teacher(Integer.parseInt(tmp.get(i+2).toString()),tmp.get(i+0).toString(),tmp.get(i+1).toString()));
        }

        List<Subject> subjects = new ArrayList<Subject>();
        tmp.clear();
        mapList=engine.LoadData(Types.subject);
        for(Map<String,Object> i:mapList){
            tmp.addAll((i.values()));
        }

        for(int i=0;i<tmp.size();i+=3) {
            switch (tmp.get(i+2).toString()) {
                case "lecture":
                    subjects.add(new Subject(Integer.parseInt(tmp.get(i+1).toString()),tmp.get(i+0).toString(), SubjectTypes.lecture));
                    break;
                case "lab":
                    subjects.add(new Subject(Integer.parseInt(tmp.get(i+1).toString()),tmp.get(i+0).toString(), SubjectTypes.lab));
                    break;
                case "auditorium":
                    subjects.add(new Subject(Integer.parseInt(tmp.get(i+1).toString()),tmp.get(i+0).toString(), SubjectTypes.auditorium));
                    break;

                case "project":
                    subjects.add(new Subject(Integer.parseInt(tmp.get(i+1).toString()),tmp.get(i+0).toString(), SubjectTypes.project));
                    break;
            }
        }

        for(int i=0;i<teachers.size();i++)
        {
            teachers.get(i).setSubject(subjects.get(i));
        }

        for(int i=0;i<teachers.size();i++)
        {

            for(int j=0;j<teachers.size();j++)
            {
                if(teachers.get(i).equal(teachers.get(j)))
                {
                    teachers.get(j).setId(teachers.get(i).getId());
                }
            }
        }
        setTeachersPreferences(teachers,preferences);
        return teachers;
    }



    static void setTeachersPreferences(List<Teacher> teachers,List<Map<String, Object>> preferences)
    {
        for (Map<String, Object> map : preferences)
        {
            int[][] p = new int[5][7];
            for (Map.Entry<String, Object> entry : map.entrySet())
            {
                String key = entry.getKey();
                Object value = entry.getValue();
                try {
                    switch (key)
                    {

                        case "poniedziałek": {
                            for (int i = 0; i < 7; i++)
                            {
                                p[0][i] = (value.toString().charAt(i) - '0');
                            }
                        }
                        break;
                        case "wtorek": {
                            for (int i = 0; i < 7; i++)
                            {
                                p[1][i] = (value.toString().charAt(i) - '0');
                            }
                        }
                        break;
                        case "sroda": {
                            for (int i = 0; i < 7; i++)
                            {
                                p[2][i] = (value.toString().charAt(i) - '0');
                            }
                        }
                        break;
                        case "czwartek": {
                            for (int i = 0; i < 7; i++)
                            {
                                p[3][i] = (value.toString().charAt(i) - '0');
                            }
                        }
                        break;
                        case "piatek": {
                            for (int i = 0; i < 7; i++)
                            {
                                p[4][i] = (value.toString().charAt(i) - '0');
                            }
                        }
                        break;
                        case "Name": {
                            Teacher t = findTeacher(teachers, value.toString());
                            t.setPreferences(p);
                        }

                    }
                }
                catch (Exception e)
                {

                }
            }
        }

    }

    static Teacher findTeacher(List<Teacher> teachers,String value)
    {
        for(int i=0;i<teachers.size();i++)
        {
            String name = teachers.get(i).getsName()+" "+teachers.get(i).getfName();
            String kay = value.substring(3,value.length());
            if(name.equals(kay) && !teachers.get(i).isP())
            {
                teachers.get(i).setP(true);
                return teachers.get(i);
            }
        }
        return new Teacher();
    }


    public static  List<Room> loadRooms()
    {
        IEngine engine = new Engine();
        List<Room> room = new ArrayList<Room>();
        List<Map<String, Object>>  mapList=engine.LoadData(Types.room);
        List<Object> tmp = new ArrayList<Object>();
        for(Map<String,Object> i:mapList){
            tmp.addAll((i.values()));
        }
        for(int i=0;i<tmp.size();i+=3) {
            switch (tmp.get(i+2).toString()) {
                case "lecture":
                    room.add(new Room(Integer.parseInt(tmp.get(i+1).toString()),tmp.get(i+0).toString(), SubjectTypes.lecture));
                    break;
                case "lab":
                    room.add(new Room(Integer.parseInt(tmp.get(i+1).toString()),tmp.get(i+0).toString(), SubjectTypes.lab));
                    break;
                case "auditorium":
                    room.add(new Room(Integer.parseInt(tmp.get(i+1).toString()),tmp.get(i+0).toString(), SubjectTypes.auditorium));
                    break;

                case "project":
                    room.add(new Room(Integer.parseInt(tmp.get(i+1).toString()),tmp.get(i+0).toString(), SubjectTypes.project));
                    break;
            }
        }

        return room;
    }

}
