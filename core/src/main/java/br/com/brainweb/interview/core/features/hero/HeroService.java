package br.com.brainweb.interview.core.features.hero;


import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.HeroCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HeroService {

    @Autowired
    HeroRepository heroRepository;

    @Autowired
    HeroRepo heroRepo;

    @Autowired
    PowerStatsService powerStatsService;

    @Autowired
    PowerStatsRepository powerStatsRepository;

    public List<Hero> getAll() {
        return this.heroRepository.findAll();
    }

    public Hero create(Hero hero) {
        this.powerStatsService.create(hero.getPowerStats());
        return this.heroRepo.save(hero);
    }

    public ResponseEntity<Hero> getById(UUID uuid) {
        return this.heroRepo.findById(uuid)
                .map(hero -> ResponseEntity.ok().body(hero))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Hero> getByName(String name) {
        return this.heroRepository.findByName(name)
                .map(hero -> ResponseEntity.ok().body(hero))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Hero> update(Hero requestHero, UUID heroUuid) {
        return this.heroRepository.findById(heroUuid)
                .map(hero -> {
                    hero.setName(requestHero.getName());
                    hero.setRace(requestHero.getRace());
                    Hero updatedHero = this.heroRepository.save(hero);
                    return ResponseEntity.ok().body(updatedHero);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> delete(UUID heroUuid) {
        return this.heroRepository.findById(heroUuid)
                .map(hero -> {
                    this.heroRepository.delete(hero);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<HeroCompare> compare(UUID firstUuid, UUID secondUuid) {
        Hero firstHero = this.heroRepo.findById(firstUuid).get();
        Hero secondHero = this.heroRepo.findById(secondUuid).get();

        if (firstHero == null || secondHero == null) {
            return ResponseEntity.notFound().build();
        }

        HeroCompare compare = new HeroCompare();

        compare.setAgility(this.compareStats(Integer.valueOf(firstHero.getPowerStats().getAgility()), Integer.valueOf(secondHero.getPowerStats().getAgility()), firstHero.getName(), secondHero.getName()));
        compare.setDexterity(this.compareStats(Integer.valueOf(firstHero.getPowerStats().getDexterity()), Integer.valueOf(secondHero.getPowerStats().getDexterity()), firstHero.getName(), secondHero.getName()));
        compare.setIntelligence(this.compareStats(Integer.valueOf(firstHero.getPowerStats().getIntelligence()), Integer.valueOf(secondHero.getPowerStats().getIntelligence()), firstHero.getName(), secondHero.getName()));
        compare.setStrength(this.compareStats(Integer.valueOf(firstHero.getPowerStats().getStrength()), Integer.valueOf(secondHero.getPowerStats().getStrength()), firstHero.getName(), secondHero.getName()));

        return ResponseEntity.ok(compare);
    }

    private String compareStats(Integer firstStat, Integer secondStat, String firstHeroName, String secondHeroName) {
        if (firstStat.compareTo(secondStat) < 0) {
            return firstHeroName + " < " + secondHeroName;
        } else if (firstStat.compareTo(secondStat) == 0) {
            return firstHeroName + " = " + secondHeroName;
        } else {
            return firstHeroName + " > " + secondHeroName;
        }
    }
}