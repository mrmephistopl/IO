package Core.Objects;

import Model.Enumes.SubjectTypes;

/**
 * Created by micp on 01.12.16.
 */
public class Subject {
    public String name;
    public SubjectTypes type;
    public int Id;

    public Subject(){}
    public Subject(int Id,String Name,SubjectTypes type)
    {
        this.Id=Id;
        this.name =Name;
        this.type=type;
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
}
