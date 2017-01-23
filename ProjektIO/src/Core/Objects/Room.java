package Core.Objects;

import Model.Enumes.SubjectTypes;

/**
 * Created by micp on 01.12.16.
 */
public class Room {
    public String name;
    public SubjectTypes type;
    public int Id;
    public Timetable timetable;
    public Room(){}

    public Room(int Id,String name,SubjectTypes type)
    {
        this.Id=Id;
        this.name=name;
        this.type=type;
        this.timetable=new Timetable();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectTypes getType() {
        return type;
    }

    public void setType(SubjectTypes type) {
        this.type = type;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }
}
