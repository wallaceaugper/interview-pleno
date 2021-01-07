package br.com.brainweb.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class HeroCompare {

    private String uuid;
    private String agility;
    private String dexterity;
    private String intelligence;
    private String strength;
}
