package kaem0n.u5w3d5.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private long id;
    private String title;
    private String description;
    private LocalDate date;
    private String place;
    @Column(name = "max_capacity")
    private int maxCapacity;
    @OneToMany(mappedBy = "event")
    private List<Ticket> tickets;

    public Event(String title, String description, String place, LocalDate date, int maxCapacity) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.date = date;
        this.maxCapacity = maxCapacity;
    }
}
