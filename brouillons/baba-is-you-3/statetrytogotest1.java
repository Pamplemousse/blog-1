class StateTryToGoTest {

  private static final class LevelToTestTryToGo extends Level {

    private final List<int[]> states = new ArrayList<>();

    LevelToTestTryToGo(
        int width,
        int height,
        @NotNull int[] content) {
      super(width, height, content);
    }

    @Override
    void addState(@NotNull int[] content) {
      states.add(content);
    }
  }

  /**
   * Simple cases are tested with a level of 2x1
   * Baba is in the first position and tries to go left
   */
  void checkMoveSimple(
      int[] content,
      boolean result,
      @NotNull int[][] possibleNextMoves) {
    LevelToTestTryToGo level = new LevelToTestTryToGo(
        2,
        1,
        content);
    State state = new State(level, content);
    assertEquals(
        result,
        state.tryToGo(0, Direction.RIGHT));
    assertEquals(possibleNextMoves.length, level.states.size());
    for (int i = 0; i < possibleNextMoves.length; i++) {
      assertArrayEquals(
          possibleNextMoves[i],
          level.states.get(i));
    }
  }

  @Test
  void testMoveEmpty() {
    checkMoveSimple(
        new int[]{Tiles.BABA, Tiles.EMPTY},
        false,
        new int[][]{new int[]{Tiles.EMPTY, Tiles.BABA}}
    );
  }

  @Test
  void testMoveWall() {
    checkMoveSimple(
        new int[]{Tiles.BABA, Tiles.WALL},
        false,
        new int[0][]
    );
  }

  @Test
  void testMoveFlag() {
    checkMoveSimple(
        new int[]{Tiles.BABA, Tiles.FLAG},
        true,
        new int[0][]
    );
  }
}