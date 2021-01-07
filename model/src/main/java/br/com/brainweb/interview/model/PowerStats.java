package br.com.brainweb.interview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "power_stats")
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties(value = {"created_at", "updated_at"}, allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public class PowerStats implements Serializable {

    @Id
    @Column(name = "id")
    @Type(type="pg-uuid")
    private UUID uuid = UUID.randomUUID();

    @NotNull
    @Column
    private short strength;

    @NotNull
    @Column
    private short agility;

    @NotNull
    @Column
    private short dexterity;

    @NotNull
    @Column
    private short intelligence;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

//    @OneToOne(mappedBy = "powerStatsId", cascade = CascadeType.ALL, optional = false)
//    @OneToMany(mappedBy = "powerStats")
//    private List<Hero> heroes = new ArrayList<>();
}
