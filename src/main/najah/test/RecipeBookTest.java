package main.najah.test;

import main.najah.code.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Execution(value = ExecutionMode.CONCURRENT)
@DisplayName("Recipe Book Tests")
public class RecipeBookTest {
    RecipeBook book;

   
    @BeforeEach
    void setup() {
        book = new RecipeBook();
    } 

    @Test
    @DisplayName("Test adding a recipe successfully")
    void testAddRecipe() {
        Recipe r = new Recipe();
        r.setName("Espresso");
        assertTrue(book.addRecipe(r));
    }


    @Test
    @DisplayName("Test adding duplicate recipe fails")
    void testAddDuplicateRecipe() {
    	 Recipe r = new Recipe();
         r.setName("Latte");
         book.addRecipe(r);
         assertFalse(book.addRecipe(r));
    }

    
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("Test deleting a recipe by index")
    void testDeleteRecipe(int index) {
    	 Recipe r = new Recipe();
         r.setName("Mocha");
         book.addRecipe(r);
         String name = book.deleteRecipe(index);
         assertEquals("Mocha", name);
    }

    @Test
    @DisplayName("Test editing a recipe")
    void testEditRecipe() {
	    Recipe oldRecipe = new Recipe();
        oldRecipe.setName("Latte");
        book.addRecipe(oldRecipe);

        Recipe newRecipe = new Recipe();
        newRecipe.setName("Cappuccino");

        String oldName = book.editRecipe(0, newRecipe);
        assertAll(
            () -> assertEquals("Latte", oldName),
            () -> assertEquals("Cappuccino", book.getRecipes()[0].getName())
        );
    }
    
}
