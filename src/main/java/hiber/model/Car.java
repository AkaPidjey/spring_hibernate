package hiber.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @OneToOne (mappedBy = "car", cascade = CascadeType.ALL)
    private User user;

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }
}
