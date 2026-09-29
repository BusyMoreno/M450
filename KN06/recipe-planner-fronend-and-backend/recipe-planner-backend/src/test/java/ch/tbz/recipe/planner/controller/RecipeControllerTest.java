package ch.tbz.recipe.planner.controller;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.mapper.RecipeEntityMapper;
import ch.tbz.recipe.planner.repository.RecipeRepository;
import ch.tbz.recipe.planner.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc; //[cite: 1]

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private RecipeService service;

    @MockBean
    private RecipeEntityMapper mapper;

    // Behebt den UnsatisfiedDependencyException-Fehler für die init-Methode:
    @MockBean
    private RecipeRepository recipeRepository;

    @Test
    void getRecipes_shouldReturnListOfRecipesAndOk() throws Exception {
        UUID id = UUID.randomUUID();
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setName("Lasagne");

        when(service.getRecipes()).thenReturn(List.of(recipe));

        mockMvc.perform(get("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id.toString()))
                .andExpect(jsonPath("$[0].name").value("Lasagne"));

        verify(service).getRecipes();
    }

    @Test
    void getRecipe_withValidId_shouldReturnRecipeAndOk() throws Exception {
        UUID id = UUID.randomUUID();
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setName("Risotto");

        when(service.getRecipeById(id)).thenReturn(recipe);

        mockMvc.perform(get("/api/recipes/recipe/{recipeId}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Risotto"));

        verify(service).getRecipeById(id);
    }

   @Test
    void addRecipe_shouldReturnAddedRecipeAndOk() throws Exception {
        UUID id = UUID.randomUUID();
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipe.setName("Pancakes");

        when(service.addRecipe(any(Recipe.class))).thenReturn(recipe);

        mockMvc.perform(post("/api/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipe))) // Sendet den JSON-Body mit
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Pancakes"));

        verify(service).addRecipe(any(Recipe.class));
    }
}