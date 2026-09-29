package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import org.assertj.core.api.SoftAssertions; //
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

class RecipeEntityMapperTest {

    // Falls MapStruct verwendet wird:
    private final RecipeEntityMapper mapper = Mappers.getMapper(RecipeEntityMapper.class);

    @Test
    void testEntityToDomain() {
        RecipeEntity entity = new RecipeEntity();
        entity.setId(UUID.randomUUID());
        entity.setName("Pizza Margherita");
        entity.setDescription("Tomaten, Mozzarella, Basilikum");

        Recipe domain = mapper.entityToDomain(entity); // Methodennamen in RecipeEntityMapper.java abgleichen

        SoftAssertions softly = new SoftAssertions(); //
        softly.assertThat(domain).isNotNull();
        softly.assertThat(domain.getId()).isEqualTo(entity.getId());
        softly.assertThat(domain.getName()).isEqualTo(entity.getName());
        softly.assertThat(domain.getDescription()).isEqualTo(entity.getDescription());
        softly.assertAll(); // Prüft alle Felder gebündelt[cite: 1]
    }
}