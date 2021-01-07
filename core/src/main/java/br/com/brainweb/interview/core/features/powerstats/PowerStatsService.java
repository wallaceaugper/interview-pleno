package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerStatsService {

    @Autowired
    PowerStatsRepository powerStatsRepository;

    public PowerStats create(PowerStats powerStats) {
        return this.powerStatsRepository.save(powerStats);
    }
}
