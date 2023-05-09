import java.awt.{BasicStroke, BorderLayout, Color, Font, Graphics, Graphics2D, Image}
import javax.swing.{ImageIcon, JFrame, JPanel, WindowConstants}
import java.awt.Window
import scala.math.*


def checkers_drawer(board: Array[Array[String]]): Unit = {
  val win = java.awt.Window.getWindows
  for (i <- 0 until win.length) {
    win(i).dispose()
  }
  val frame = new JFrame("Game_engine")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  val numbers = Array("1", "2", "3", "4", "5", "6", "7", "8")
  val letters = Array("a", "b", "c", "d", "e", "f", "g", "h")
  val spacing_between_squares = 80
  val size_of_board = 640
  val starting_point_of_board_x = 70
  val starting_point_of_board_y = 60
  val color_1 = new Color(176, 218, 255)
  val color_2 = new Color(87, 108, 188)
  val panel = new JPanel() {
    override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)

      //Grid color and size
      for (i <- 0 until 8) {
        for (j <- 0 until 8) {
          if ((i + j) % 2 == 0)
            g.setColor(color_1)
          else
            g.setColor(color_2)
          g.fillRect(starting_point_of_board_x + i * 80, starting_point_of_board_y + j * 80, spacing_between_squares, spacing_between_squares)
        }
      }


      g.setColor(Color.BLACK)
      val g2d = g.asInstanceOf[Graphics2D]
      g2d.setStroke(new BasicStroke(3))
      //this for horizontal lines
      for (i <- 0 to board.length) {
        g.drawLine(starting_point_of_board_x, starting_point_of_board_y + spacing_between_squares * i, starting_point_of_board_x + size_of_board, starting_point_of_board_y + spacing_between_squares * i)
      }
      //This for vertical lines
      for (i <- 0 to board(0).length) {
        g.drawLine(starting_point_of_board_x + spacing_between_squares * i, starting_point_of_board_y, starting_point_of_board_x + spacing_between_squares * i, starting_point_of_board_y + size_of_board)
      }

      for (i <- 0 until 8) {
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(numbers(i), starting_point_of_board_x - 30, starting_point_of_board_y + 50 + spacing_between_squares * i)
      }

      for (i <- 0 until 8) {
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(letters(i), starting_point_of_board_x + 30 + spacing_between_squares * i, 730)
      }

      for (i <- 0 until 8) {
        for (j <- 0 until 8) {
          g.drawImage(piece_to_image(board(i)(j)), starting_point_of_board_x + 5 + 80 * j, starting_point_of_board_y + 5 + 80 * i, 70, 70, null)
        }
      }
    }
  }
  frame.add(panel)
  frame.setSize(800, 800)
  frame.setResizable(false)
  frame.setAlwaysOnTop(true);
  frame.setLocationRelativeTo(null)
  frame.setVisible(true)
}

def user_capture_valid_piece(selected_piece: String, turn: Int): Boolean = {
  if (selected_piece == null)
    false
  else if (turn == 1) {
    selected_piece.contains("black")
  }
  else {
    selected_piece.contains("red")
  }
}

def piece_can_move(to: (Int, Int), turn: Int, board: Array[Array[String]]): Boolean = {
  val data_in_to_position = board(to._1)(to._2)
  data_in_to_position == null
}

def valid_position(position: (Int, Int)): Boolean = {
  if (position._1 < 0 || position._1 >= 8 || position._2 < 0 || position._2 >= 8) {
    return false;
  }
  true;
}

def diagonal_move(from: (Int, Int), to: (Int, Int)): Boolean = {
  abs(from._1 - to._1) >= 1 && abs(from._2 - to._2) >= 1
}

def should_promote(to: (Int, Int), turn: Int): Boolean = {
  if (turn == 1) {
    if (to._1 == 0) {
      return true
    }
  }
  else {
    if (to._1 == 7) {
      return true
    }
  }
  return false
}

def move_may_eat(from: (Int, Int), to: (Int, Int)): Boolean = {
  abs(from._1 - to._1) == 2 && abs(from._2 - to._2) == 2
}

def can_exactly_eat(from: (Int, Int), to: (Int, Int), turn: Int, board: Array[Array[String]]): Boolean = {
  val piece_in_middle = board((from._1 + to._1) / 2)((from._2 + to._2) / 2)
  if (board(to._1)(to._2) != null || piece_in_middle == null)
    return false
  if (turn == 1) {
    piece_in_middle.contains("red")
  } else {
    piece_in_middle.contains("black")
  }
}

def good_move_for_the_selected_piece(selected_piece: String, from: (Int, Int), to: (Int, Int)): Boolean = {
  selected_piece match {
    case "black_basic" => if (to._1 < from._1) true else false
    case "red_basic" => if (to._1 > from._1) true else false
    case _ => true
  }
}

def checkers_controller(state: (Int, Array[Array[String]]), move: String): (Boolean, Array[Array[String]]) = {

  val turn = state._1
  val board = state._2
  val complete_move = move.split(" ")
  try {
    val from = (complete_move(0).charAt(0) - '1', complete_move(0).charAt(1) - 'a')
    val to = (complete_move(1).charAt(0) - '1', complete_move(1).charAt(1) - 'a')
    //check from is in grid
    if (!valid_position(from)) {
      println("Not Valid position to move form")
      return (false, board)
    }

    //check to is in grid
    if (!valid_position(to)) {
      println("Not Valid position to move to")
      return (false, board)
    }

    //check diagonal move by 1 or by 2 both of them is ok
    if (!diagonal_move(from, to)) {
      println("That's not a diagonal move")
      return (false, board)
    }

    var selected_piece = board(from._1)(from._2)

    //capture its piece
    if (!user_capture_valid_piece(selected_piece, turn)) {
      println("That's not your piece")
      return (false, board)
    }

    //check piece move right like => black_basic: up, red_basic: down
    if (!good_move_for_the_selected_piece(selected_piece, from, to)) {
      if (turn == 1) {
        println("Your piece isn't promoted, please go up not down")
      } else {
        println("Your piece isn't promoted, please go down not up")
      }
      return (false, board)
    }

    //user entered abs(2-diff) between indices
    if (move_may_eat(from, to)) {
      if (can_exactly_eat(from, to, turn, board)) {
        val middle_indices: (Int, Int) = ((from._1 + to._1) / 2, (from._2 + to._2) / 2)
        board(from._1)(from._2) = null;
        board(middle_indices._1)(middle_indices._2) = null
        if (should_promote(to, turn)) {
          selected_piece = selected_piece.replace("basic", "promoted")
        }
        board(to._1)(to._2) = selected_piece;
        return (true, board)
      } else {
        println("Can't move 2 diagonals in the same move")
        return (false, board)
      }
    }

    //check the to position doesn't have the same colour
    if (!piece_can_move(to, turn, board)) {
      println("Can't move over your piece")
      return (false, board)
    } else {
      board(from._1)(from._2) = null
      if (should_promote(to, turn)) {
        selected_piece = selected_piece.replace("basic", "promoted")
      }
      board(to._1)(to._2) = selected_piece;
      return (true, board)
    }
  } catch {
    case ste: Exception => return (false, board)
  }

}