package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import org.assertj.core.api.SoftAssertions; //[cite: 1]
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

class IngredientEntityMapperTest {

    private final IngredientEntityMapper mapper = Mappers.getMapper(IngredientEntityMapper.class);

    @Test
    void testEntityToDomain() {
        IngredientEntity entity = new IngredientEntity();
        entity.setId(UUID.randomUUID());
        entity.setName("Zucker");

        Ingredient domain = mapper.entityToDomain(entity); // Methodennamen in IngredientEntityMapper.java abgleichen

        SoftAssertions softly = new SoftAssertions(); //[cite: 1]
        softly.assertThat(domain).isNotNull();
        softly.assertThat(domain.getId()).isEqualTo(entity.getId());
        softly.assertThat(domain.getName()).isEqualTo(entity.getName());
        softly.assertAll(); //[cite: 1]
    }
}