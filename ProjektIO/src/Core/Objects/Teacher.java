package Core.Objects;

/**
 * Created by micp on 01.12.16.
 */
public class Teacher {
    public int Id;
    public String fName;
    public String sName;
    public Subject subject;
    public int [][] preferences;
    public Timetable timetable;
    public boolean p;

    public Teacher(){}

    public Teacher(int id, String fName, String sName) {
        Id = id;
        this.fName = fName;
        this.sName = sName;
        this.timetable=new Timetable();
        p=false;
    }

    public Teacher(int id, String fName, String sName, Subject subject, int[][] preferences )
    {
        this.Id=id;
        this.fName=fName;
        this.sName = sName;
        this.subject=subject;
        this.preferences = preferences;
        this.timetable=new Timetable();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int[][] getPreferences() {
        return preferences;
    }

    public void setPreferences(int[][] preferences) {
        this.preferences = preferences;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setP(boolean p)
    {
        this.p=p;
    }

    public boolean isP() {
        return p;
    }

    public boolean equal(Teacher t )
    {
        if(this.fName.equals(t.fName) && this.sName.equals(t.sName))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
