package ait.cohort34.person.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
public class Person {
    @Id
    Integer id;     // int

    @Setter
    String name;    // varChar

    LocalDate birthDate;    // Date

    @Setter
            @Embedded
    Address address;    // new Table
}
