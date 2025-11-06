package nl.tudelft.jpacman.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

/**
 * Test suite to confirm that {@link Unit}s correctly (de)occupy squares.
 *
 * @author Phanphum Prathumsuwan
 *
 */

class BoardTest {

    /**
     * Do we get the expected output for testing squareAt_ReturnsSquare_OnValidBoard
     */

    @Test
    void squareAt_ReturnsSquare_OnValidBoard() {
        // 1×1 grid with a real square
        Square[][] grid = new Square[][] { { new BasicSquare() } };
        Board board = new Board(grid);

        Square s = board.squareAt(0, 0);

        assertThat(s).isNotNull();
        assertThat(board.getWidth()).isEqualTo(1);
        assertThat(board.getHeight()).isEqualTo(1);
    }

    /**
     * Do we get the expected output for testing constructor_Fails_OnNullSquare
     */
    @Test
    void constructor_Fails_OnNullSquare() {
        // 1×1 grid but the only cell is null
        Square[][] grid = new Square[][] { { null } };

        // The Board constructor asserts invariant() and throws AssertionError.
        assertThatThrownBy(() -> new Board(grid))
            .isInstanceOf(AssertionError.class)
            .hasMessageContaining("Initial grid cannot contain null squares");
    }
}
