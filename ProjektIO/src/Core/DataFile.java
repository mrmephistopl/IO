package Core;

import Core.Objects.Group;
import Core.Objects.Room;
import Core.Objects.Subject;
import Core.Objects.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micp on 01.12.16.
 */
public class DataFile {
    public List<Room> rooms;
    public List<Group> groups;
    public List<Subject> subjects;
    public List<Teacher> teachers;

    public DataFile(){
        rooms = new ArrayList<>();
        groups =new ArrayList<>();
        subjects =new ArrayList<>();
        teachers=new ArrayList<>();
    }

}
