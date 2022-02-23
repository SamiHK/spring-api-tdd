package com.samiharoon.apitdd.persistence.domain;

import com.samiharoon.apitdd.persistence.dto.SampleDTO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.sql.Date;


@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "samples", schema = "public")
public class Sample {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, unique = true, nullable = false, name ="id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false, name ="phoneNumber")
    private Long phoneNumber;

    @Column(nullable = false, name ="firstName")
    private String firstName;

    @Column(nullable = false, name ="lastName")
    private String lastName;

    @Column(name = "date")
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-mm-dd", iso = DateTimeFormat.ISO.DATE)
    private Date date;

    public Sample() {}

    public SampleDTO toSampleDto(){
        return SampleDTO.builder()
                .id(this.id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .phoneNumber(this.phoneNumber)
                .date(this.date)
                .build();
    }
}
