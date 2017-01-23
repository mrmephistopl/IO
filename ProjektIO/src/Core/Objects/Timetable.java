package Core.Objects;

/**
 * Created by User on 2016-12-08.
 */
public class Timetable {
    Classes [][] timetable;

    public Timetable() {
        timetable = new Classes[5][7];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                timetable[i][j] = new Classes();
            }
        }
    }


    public Classes[][] getTimetable() {
        return timetable;
    }
}
