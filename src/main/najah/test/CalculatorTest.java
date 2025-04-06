package main.najah.test;

import main.najah.code.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Calculator Tests")
public class CalculatorTest {

    Calculator calc;

    @BeforeAll
    static void initAll() {
        System.out.println("[BeforeAll] Initializing CalculatorTest suite...");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("[AfterAll] CalculatorTest suite complete.");
    }

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("✅ [BeforeEach] Setup complete.");
    } 

    @AfterEach
    void tearDown() {
        System.out.println("[AfterEach] Test finished.\n");
    }

    @Test
    @Order(1)
    @DisplayName("Test add with multiple numbers")
    void testAddition() {
        int result = calc.add(1, 2, 3);
        assertEquals(6, result);
    }

    @Order(2)
    @ParameterizedTest
    @CsvSource({"5,120", "0,1"})
    @DisplayName("Test factorial with valid inputs")
    void testFactorialValid(int input, int expected) {
        assertEquals(expected, calc.factorial(input));
    }

    @Test
    @Order(3)
    @DisplayName("Test factorial with negative input throws exception")
    void testInvalidFactorial() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-5));
    }

    @Test
    @Order(4)
    @DisplayName("Test divide with valid inputs")
    void testValidDivide() {
        int result = calc.divide(10, 2);
        assertAll(
            () -> assertEquals(5, result),
            () -> assertTrue(result > 0)
        );
    }

    @Test
    @Order(5)
    @DisplayName("Test divide by zero throws exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(1, 0));
     }
 
    @Order(6) 
    @Test
    @Disabled("Fix: Expected value was incorrect, update expected value to pass")
    @DisplayName("Disabled failing test")
    void testFailing() {
        assertEquals(100, calc.add(40, 50));
    }
    
    @Test
    @Order(7)
    @DisplayName("Test add with two numbers")
    void testAddTwoNumbers() {
        assertEquals(7, calc.add(3, 4));
    }

    @Test
    @Order(8)
    @DisplayName("Test add with no arguments returns 0")
    void testAddNoArguments() {
        assertEquals(0, calc.add());
    }

}