package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.HeroCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hero")
public class HeroController {

    @Autowired
    HeroService heroService;

    @GetMapping("/all")
    public List<Hero> getAll() {
        return this.heroService.getAll();
    }

    @PostMapping("/create")
    public Hero create(@Valid @RequestBody Hero hero) {
        return this.heroService.create(hero);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Hero> getById(@PathVariable(value = "uuid") UUID uuid) {
        return this.heroService.getById(uuid);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Hero> getByName(@PathVariable(value = "name")  String name) {
        return this.heroService.getByName(name);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hero> update(@PathVariable(value = "id") UUID heroUuid, @Valid @RequestBody Hero requestHero) {
       return this.heroService.update(requestHero, heroUuid);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID heroUuid) {
        return this.heroService.delete(heroUuid);
    }

    @GetMapping("compare/{uuid}/{uuid2}")
    public ResponseEntity<HeroCompare> compare(@PathVariable(value = "uuid") UUID firstUuid, @PathVariable(value = "uuid2") UUID secondUuid) {
        return this.heroService.compare(firstUuid, secondUuid);
    }
}
