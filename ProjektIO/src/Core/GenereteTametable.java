package Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Core.Objects.*;
import Model.Enumes.SubjectTypes;

/**
 * Created by User on 2016-12-08.
 */
public  class GenereteTametable {

    static String error;
    public static String generuj(List<Group> listGroup, List<Room> listRoom, List<Teacher> listTeacher)
    {
        error="";
        for (Group group : listGroup)
        {
            for(Teacher teacher : listTeacher) {
                if(group.getType() == teacher.getSubject().getType())
                {
                    Classes nowe = new Classes();
                    nowe.setGroup(group);
                    nowe.setTeacher(teacher);
                    toTametable(group,teacher,nowe,listGroup,listRoom,listTeacher);
                }
            }
        }
        if(error.equals(""))
        {
            error="Plan został wygenerowany poprawnie";
        }
        return error;
    }

    private static void toTametable(Group group, Teacher teacher, Classes nowe,List<Group> listGroup, List<Room> listRoom, List<Teacher> listTeacher)
    {
       int[] i = findMaxPreferencje(teacher.getPreferences(),teacher);
       Room room=findRoom(listRoom,teacher.getSubject().getType(),i);
       nowe.setRoom(room);
       try {
           if (teacher.getTimetable().getTimetable()[i[0]][i[1]].isEmpty() == true &&
                   room.getTimetable().getTimetable()[i[0]][i[1]].isEmpty() == true &&
                   group.getTimetable().getTimetable()[i[0]][i[1]].isEmpty() == true) {
               setInTametable(teacher, group, room, nowe, i[0], i[1]);
               putInAll(group, teacher, nowe, listGroup, listTeacher, i[0], i[1]);
               teacher.getPreferences()[i[0]][i[1]] = -1;
           } else {
               for (int j = 0; j < 5; j++) {
                   for (int k = 0; k < 7; k++) {
                       if (teacher.getTimetable().getTimetable()[j][k].isEmpty() == true &&
                               room.getTimetable().getTimetable()[j][k].isEmpty() == true &&
                               group.getTimetable().getTimetable()[j][k].isEmpty() == true) {
                           setInTametable(teacher, group, room, nowe, j, k);
                           putInAll(group, teacher, nowe, listGroup, listTeacher, j, k);
                           i[0] = j;
                           i[1] = k;
                           teacher.getPreferences()[i[0]][i[1]] = -1;
                           return;
                       }
                   }
               }
           }
       }
       catch (Exception e)
       {
           error+="Nie znaleziono sali dla zajec grupy" + group.getName() +"z prowadzacym: "+ teacher.getfName() +" "+
                   teacher.getsName()+"\n";

       }
    }

    private static void putInAll(Group group, Teacher teacher, Classes nowe,List<Group> listGroup,List<Teacher> listTeacher,int i,int j){
        for(Teacher t : listTeacher)
        {
            if(t.getId()==teacher.getId())
            {
                t.getTimetable().getTimetable()[i][j]=nowe;
                t.getTimetable().getTimetable()[i][j].setEmpty(false);
            }
        }

        if(group.getType()==SubjectTypes.lecture)
        {
            for (Group g : listGroup)
            {
                g.getTimetable().getTimetable()[i][j]=nowe;
                g.getTimetable().getTimetable()[i][j].setEmpty(false);
            }
        }
    }

    private static void setInTametable(Teacher teacher,Group group, Room room, Classes nowe,int i,int j)
    {
        teacher.getTimetable().getTimetable()[i][j] = nowe;
        teacher.getTimetable().getTimetable()[i][j].setEmpty(false);
        group.getTimetable().getTimetable()[i][j] = nowe;
        group.getTimetable().getTimetable()[i][j].setEmpty(false);
        room.getTimetable().getTimetable()[i][j] = nowe;
        room.getTimetable().getTimetable()[i][j].setEmpty(false);
    }



    private static Room findRoom(List<Room> listRooms, SubjectTypes type,int [] pref)
    {

        List<Room> rooms = new ArrayList<Room>();
        for (Room room: listRooms) {
            if(room.getType()== type)
            {
               rooms.add(room);
            }
        }
        for (Room room: rooms) {
                if(room.getTimetable().getTimetable()[pref[0]][pref[1]].isEmpty())
                {
                    return room;
                }
        }
        for(Room room: rooms) {
                for(int i=0;i<5;i++)
                {
                    for(int j=0;i<7;j++){
                        if(room.getTimetable().getTimetable()[i][j].isEmpty())
                        {
                            pref[0]=i;
                            pref[1]=j;
                            return room;
                        }
                    }
                }
        }
        return null;
    }



    private static int[] findMaxPreferencje(int[][] tab,Teacher teacher)
    {
        try {
            int max = tab[0][0];
            int idx = 0;
            int idy = 0;
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab[i].length; j++) {
                    if (tab[i][j] > max) {
                        max = tab[i][j];
                        idx = i;
                        idy = j;
                    }
                }
            }
            int[] id = new int[2];
            id[0] = idx;
            id[1] = idy;
            return id;
        }
        catch (Exception e)
        {
            int [][] pref = new int [5][7];
            teacher.setPreferences(pref);
            int[] id = new int[2];
            return id;
        }
    }

}
