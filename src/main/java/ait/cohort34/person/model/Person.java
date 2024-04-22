package ait.cohort34.person.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    Integer id;             // int

    @Setter
    String name;            // varChar

    LocalDate birthDate;    // Date

    @Setter
//            @Embedded
    Address address;        // new Table
}
