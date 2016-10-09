import java.util.Calendar;

/**
 * Created by kamil on 08.10.16.
 */
public class Ticket {

    Calendar calendar;
    int id;

    Ticket(int id, Calendar calendar){
        this.id = id;
        this.calendar = calendar;
    }
}
