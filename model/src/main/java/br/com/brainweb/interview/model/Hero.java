package br.com.brainweb.interview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(value = {"created_at", "updated_at"}, allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public class Hero implements Serializable {

    @Id
    @Column(name = "id")
    @Type(type="pg-uuid")
    private UUID uuid = UUID.randomUUID();

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    @Column
    private String race;

    @ManyToOne
    @JoinColumn(name = "power_stats_id")
    private PowerStats powerStats;

    @Column
    private boolean enabled = true;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;
}
