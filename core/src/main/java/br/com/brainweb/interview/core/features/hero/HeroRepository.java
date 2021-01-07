package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.HeroCompare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HeroRepository extends JpaRepository<Hero, UUID> {

    Optional<Hero> findByName(String name);

    @Query(value = "select hero.id, ps.agility, ps.dexterity, ps.intelligence, ps.strength\n" +
            "from interview_service.hero hero\n" +
            "join interview_service.power_stats ps on ps.id = hero.power_stats_id\n" +
            "where hero.id in (\n" +
            "    :firstHeroUuid,\n" +
            "    :secondHeroUuid\n" +
            ")", nativeQuery = true)
    List<HeroCompare> findHeroes(UUID firstHeroUuid, UUID secondHeroUuid);
}
