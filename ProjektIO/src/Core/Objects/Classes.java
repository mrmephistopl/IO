package Core.Objects;

/**
 * Created by User on 2016-12-08.
 */
public class Classes {

    public boolean empty;
    public Room room;
    public Group group;
    public Teacher teacher;
    public Subject subject;

    public Classes()
    {
        empty=true;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public boolean isEmpty() {
        return empty;
    }

    public Group getGroup() {
        return group;
    }

    public Room getRoom() {
        return room;
    }

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
