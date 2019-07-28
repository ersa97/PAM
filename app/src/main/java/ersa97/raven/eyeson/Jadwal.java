package ersa97.raven.eyeson;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class Jadwal {

    private String id;
    @ServerTimestamp
    private Date Time;
    private String Subject;
    private String Teacher;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public Jadwal(String id, Date time, String subject, String teacher) {
        this.id = id;
        Time = time;
        Subject = subject;
        Teacher = teacher;
    }



    public Jadwal(){

    }

}
