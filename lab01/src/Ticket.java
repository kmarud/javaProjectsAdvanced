import java.util.Calendar;

/**
 * Created by kamil on 08.10.16.
 */
public class Ticket {

    private Calendar calendar;
    private int id;

    Ticket(int id, Calendar calendar){
        this.id = id;
        this.calendar = calendar;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public int getId() {
        return id;
    }
}
