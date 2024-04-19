package kaem0n.u5w3d5.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Event {
    @Setter(AccessLevel.NONE)
    private long id;
    private String title;
    private String description;
    private LocalDate date;
    private String place;
    private int maxCapacity;

    public Event(String title, String description, String place, LocalDate date, int maxCapacity) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.date = date;
        this.maxCapacity = maxCapacity;
    }
}
