package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Jeroen Roosen 
 *
 */
class OccupantTest {

    /**
     * The unit under test.
     */
    private Unit unit;

    /**
     * Resets the unit under test.
     */
    @BeforeEach
    void setUp() {
        unit = new BasicUnit();
    }

    /**
     * Asserts that a unit has no square to start with.
     */
    @Test
    void noStartSquare() {
        assertThat(unit.hasSquare()).isFalse();
        // getSquare() is illegal before occupying a square:
        assertThatThrownBy(() -> unit.getSquare())
            .isInstanceOf(AssertionError.class);

    }

    /**
     * Tests that the unit indeed has the target square as its base after
     * occupation.
     */
    @Test
    void testOccupy() {
        Square target = new BasicSquare();
        unit.occupy(target);

        // Unit now references target
        assertThat(unit.getSquare()).isEqualTo(target);
        // Square also knows this unit occupies it
        assertThat(target.getOccupants()).contains(unit);
    }

    /**
     * Test that the unit indeed has the target square as its base after
     * double occupation.
     */
    @Test
    void testReoccupy() {
        Square first = new BasicSquare();
        Square second = new BasicSquare();

        unit.occupy(first);
        assertThat(first.getOccupants()).contains(unit);

        unit.occupy(second);

        // Old square should no longer contain the unit
        assertThat(first.getOccupants()).doesNotContain(unit);
        // New square should now contain the unit
        assertThat(second.getOccupants()).contains(unit);
        // Unit’s current square reference should update
        assertThat(unit.getSquare()).isEqualTo(second);

    }
}
