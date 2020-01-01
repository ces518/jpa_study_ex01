package hello;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 01/01/2020
 * Time: 11:02 오후
 **/
@Embeddable
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public boolean isWork () {
        LocalDateTime nowDate = LocalDateTime.now();
        return startDate.isBefore(nowDate) && endDate.isAfter(nowDate);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
