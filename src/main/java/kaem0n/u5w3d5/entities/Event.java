package kaem0n.u5w3d5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User organizer;
    private String place;
    @Column(name = "max_capacity")
    private int maxCapacity;
    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Ticket> tickets;

    public Event(String title, String description, String place, LocalDate date, int maxCapacity, User organizer) {
        this.title = title;
        this.description = description;
        this.place = place;
        this.date = date;
        this.maxCapacity = maxCapacity;
        this.organizer = organizer;
    }
}
