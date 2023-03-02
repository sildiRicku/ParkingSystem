package com.example.training.period;

import com.example.training.rule.Rule;
import jakarta.persistence.*;

@Entity
@Table(name = "period")
public class Period {
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_periodId")
    @SequenceGenerator(name="SEQ_perioId", sequenceName = "SEQ_periodId", allocationSize=1)
    private PeriodId periodId;
    @OneToOne(mappedBy = "period")
    private Rule rule;

}
