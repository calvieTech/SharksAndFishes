/* Ocean.java */

import ocean_animals.Fish;
import ocean_animals.Shark;

/**
 *  The Ocean class defines an object that models an ocean full of sharks and
 *  fish.  Descriptions of the methods you must implement appear below.  They
 *  include a constructor of the form
 *
 *      public Ocean(int i, int j, int starveTime);
 *
 *  that creates an empty ocean having width i and height j, in which sharks
 *  starve after starveTime timesteps.
 *
 *  See the README file accompanying this project for additional details.
 */

public class Ocean {

  static class NeptuneCells {
    Object content;
    int content_id;
    boolean empty;
    int x, y;


    protected NeptuneCells(int xLoc, int yLoc) {
      content = null;
      empty = true;
      content_id = 0;
      x = xLoc;
      y = yLoc;
    }

    protected NeptuneCells(Object obj, int contentID, int xLoc, int yLoc) {
      if(obj == null) {
        content = null;
        empty = true;
        content_id = contentID;
      } else {
        content = obj;
        empty = contentID == EMPTY ? true : false;
      }
      x = xLoc;
      y = yLoc;
    }

    protected NeptuneCells(Object obj, int contentID, boolean isEmpty, int xLoc, int yLoc) {
      content = obj;
      empty = isEmpty;
      x = xLoc;
      y = yLoc;
    }

  }

  /**
   *  Do not rename these constants.  WARNING:  if you change the numbers, you
   *  will need to recompile Test4.java.  Failure to do so will give you a very
   *  hard-to-find bug.
   */

  public final static int EMPTY = 0;
  public final static int SHARK = 1;
  public final static int FISH = 2;

  /**
   *  Define any variables associated with an Ocean object here.  These
   *  variables MUST be private.
   */

  // neptune is the ocean
  // NeptuneCells are objects representing the cells of the ocean
  private NeptuneCells[][] neptune;
  private int width, height;
  private int sharksTimeStepsWithoutFoot;




  /**
   *  The following methods are required for Part I.
   */

  /**
   *  Ocean() is a constructor that creates an empty ocean having width i and
   *  height j, in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public Ocean(int i, int j, int starveTime) {
    // Your solution here.
    neptune = new NeptuneCells[i][j];
    width = i;
    height = j;
    sharksTimeStepsWithoutFoot = starveTime;
    for(int indexX = 0; indexX < i; ++indexX) {
      for(int indexY = 0; indexY < j; ++indexY) {
        neptune[indexX][indexY] = new NeptuneCells(null, EMPTY, i, j);
      }
    }
  }

  /**
   *  width() returns the width of an Ocean object.
   *  @return the width of the ocean.
   */

  public int width() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   *  height() returns the height of an Ocean object.
   *  @return the height of the ocean.
   */

  public int height() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   *  starveTime() returns the number of timesteps sharks survive without food.
   *  @return the number of timesteps sharks survive without food.
   */

  public int starveTime() {
    // Replace the following line with your solution.
    return sharksTimeStepsWithoutFoot;
  }

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here.
    if(neptune[x][y].empty) {
      Fish fishy = new Fish(x, y);
      NeptuneCells fishyCell = new NeptuneCells(fishy, FISH, false, x, y);
      neptune[x][y] = fishyCell;
      neptune[x][y].empty = false;
    }
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here.
    if(neptune[x][y].empty) {
      Shark sharky = new Shark(x, y);
      NeptuneCells sharkyCell = new NeptuneCells(sharky, SHARK, false, x, y);
      neptune[x][y] = sharkyCell;
      System.out.println("instanceof: " + neptune[x][y].toString() + " . ");
      neptune[x][y].empty = false;
    }
  }

  /**
   *  cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
   *  a fish, and SHARK if it contains a shark.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int cellContents(int x, int y) {
    // Replace the following line with your solution.
    if(neptune[x][y].content_id == FISH) {
      return Ocean.FISH;
    } else if(neptune[x][y].content_id == SHARK) {
      return Ocean.SHARK;
    }
    return Ocean.EMPTY;
  }


  public Object cellContentsObjects(int x, int y) {
    // Replace the following line with your solution.
    if(neptune[x][y].content_id == FISH && neptune[x][y].content instanceof Fish) {
      return neptune[x][y].content;
    } else if(neptune[x][y].content_id == SHARK && neptune[x][y].content instanceof Shark) {
      return neptune[x][y].content;
    }
    return neptune[x][y].content;
  }

  /**
   *  timeStep() performs a simulation timestep as described in README.
   *  The contents of the Ocean are completely determined by the contents at the previous
   *  timestep, according to the simple rules
   *  @return an ocean representing the elapse of one timestep.
   */

  public Ocean timeStep() {
    // Replace the following line with your solution.
    Ocean neptuneStart = new Ocean(width, height, 3);
    Ocean neptuneEnd = new Ocean(width, height, 3);
    int counter = 0;
    int neighbors = 0;

    for(int r = 0; r < neptuneStart.neptune.length; ++r) {
      int above, below; // rows that are considered above and below of row number r
      int left, right; // cols considered left and right of column number c
      above = r > 0 ? r-1 : neptuneStart.neptune.length - 1;
      below = r < neptuneStart.neptune.length - 1 ? r + 1 : 0;
      for(int c = 0; c < neptuneStart.neptune[r].length; ++c) {
        left = c > 0 ? c - 1 : neptuneStart.neptune[r].length - 1;
        right = c < neptuneStart.neptune[r].length - 1 ? c+1 : 0;

//        System.out.println("r: " + r + " and c: " + c);
//        System.out.println("above: " + above + " and below: " + below);
//        System.out.println("left: " + left + " and right: " + right);


        neighbors = neptuneStart.countUptoEightNeighbors(above, right, c, r, left, below);
        System.out.println("NEIGHBORS: " + neighbors);





      }
      System.out.println("-------");

    }


    return neptuneStart;
  }

  /**
   *
   *
   */
  public int countUptoEightNeighbors(int above, int right, int col, int row, int left, int below) {
    int neighbors = 0;

    if(neptune[above][left].content != null) {
      neighbors++;
    }

    if(neptune[above][col].content != null) {
      neighbors++;
    }

    if(neptune[above][right].content != null) {
      neighbors++;
    }

    if(neptune[row][left].content != null) {
      neighbors++;
    }

    if(neptune[row][right].content != null) {
      neighbors++;
    }

    if(neptune[below][left].content != null) {
      neighbors++;
    }

    if(neptune[below][col].content != null) {
      neighbors++;
    }

    if(neptune[below][right].content != null) {
      neighbors++;
    }

    return neighbors;
  }




  /**
   * Given a pair of integers, wrap() returns a valid position in the array neptune
   * by first adding the array indices by 1.  Then for x+1 we modulo i
   * and for y+1 we modulo j in an ixj ocean
   *
   */
  public int wrap(int coordinate) {
    return 0;
  }



  /**
   *  The following method is required for Part II.
   */

  /**
   *  addShark() (with three parameters) places a shark in cell (x, y) if the
   *  cell is empty.  The shark's hunger is represented by the third parameter.
   *  If the cell is already occupied, leave the cell as it is.  You will need
   *  this method to help convert run-length encodings to Oceans.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   *  @param feeding is an integer that indicates the shark's hunger.  You may
   *         encode it any way you want; for instance, "feeding" may be the
   *         last timestep the shark was fed, or the amount of time that has
   *         passed since the shark was last fed, or the amount of time left
   *         before the shark will starve.  It's up to you, but be consistent.
   */

  public void addShark(int x, int y, int feeding) {
    // Your solution here.
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  sharkFeeding() returns an integer that indicates the hunger of the shark
   *  in cell (x, y), using the same "feeding" representation as the parameter
   *  to addShark() described above.  If cell (x, y) does not contain a shark,
   *  then its return value is undefined--that is, anything you want.
   *  Normally, this method should not be called if cell (x, y) does not
   *  contain a shark.  You will need this method to help convert Oceans to
   *  run-length encodings.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int sharkFeeding(int x, int y) {
    // Replace the following line with your solution.
    return 0;
  }


}
