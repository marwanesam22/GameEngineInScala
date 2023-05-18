import java.awt.{BasicStroke, Color, Font, Graphics, Graphics2D}
import java.io.FileWriter
import javax.swing.{JFrame, JPanel, WindowConstants}
import org.jpl7.*

import scala.io.Source

def sudoku_valid(i: Int, j: Int): Boolean = {
  i >= 0 && i < 9 && j >= 0 && j < 9
}
def Sudoku_drawer(board: Array[Array[String]]): Unit = {
  val frame = new JFrame("Game_engine")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  val numbers = Array("1", "2", "3", "4", "5", "6", "7", "8", "9")
  val letters = Array("a", "b", "c", "d", "e", "f", "g", "h", "i")
  val spacing_between_squares = 50
  val size_of_board = 450
  val starting_point_of_board_x = 160
  val starting_point_of_board_y = 130
  val color_1 = new Color(199, 232, 202)

  val panel = new JPanel() {
    override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)

      //Grid color and size
      for (i <- 0 until board.length) {
        for (j <- 0 until board(0).length) {
          g.setColor(color_1)
          g.fillRect(starting_point_of_board_x + i * spacing_between_squares, starting_point_of_board_y + j * spacing_between_squares, spacing_between_squares, spacing_between_squares)
        }
      }


      g.setColor(Color.BLACK)
      val g2d = g.asInstanceOf[Graphics2D]
      g2d.setStroke(new BasicStroke(1))

      //this for horizontal lines
      for (i <- 0 to board.length) {
        if (i % 3 == 0) g2d.setStroke(new BasicStroke(3))
        g.drawLine(starting_point_of_board_x, starting_point_of_board_y + spacing_between_squares * i, starting_point_of_board_x + size_of_board, starting_point_of_board_y + spacing_between_squares * i)
        if (i % 3 == 0) g2d.setStroke(new BasicStroke(1))
      }
      //This for vertical lines
      for (i <- 0 to board(0).length) {
        if (i % 3 == 0) g2d.setStroke(new BasicStroke(3))
        g.drawLine(starting_point_of_board_x + spacing_between_squares * i, starting_point_of_board_y, starting_point_of_board_x + spacing_between_squares * i, starting_point_of_board_y + size_of_board)
        if (i % 3 == 0) g2d.setStroke(new BasicStroke(1))
      }
      for (i <- 0 until board.length) {
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(numbers(i), starting_point_of_board_x - 30, starting_point_of_board_y + 35 + spacing_between_squares * i)
      }

      for (i <- 0 until board(0).length) {
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(letters(i), starting_point_of_board_x + 17 + spacing_between_squares * i, starting_point_of_board_y + 30 + spacing_between_squares * board(0).length)
      }

      for (i <- 0 until board.length) {
        for (j <- 0 until board(0).length) {
          g.setColor(Color.BLACK)
          g.setFont(new Font("Serif", Font.BOLD, 30))
          if (board(i)(j) != "-1") {
            g.drawString(board(i)(j).substring(0, 1), starting_point_of_board_x + 20 + spacing_between_squares * j, starting_point_of_board_y + 35 + spacing_between_squares * i)
          }
        }
      }

    }
  }
  frame.add(panel)
  frame.setSize(800, 800)
  frame.setResizable(false)
  frame.setLocationRelativeTo(null)
  frame.setAlwaysOnTop(true)
  frame.setVisible(true)
}

def Sudoku_controller(state: (Int, Array[Array[String]]), move: String): (Boolean, Array[Array[String]]) = {
  var board = state._2
  if(move == "Solve"){
    val ret = solve_using_prolog(board)
    if(ret._1){
      board = ret._2
      return (true, board)
    }else{
      println("This Sudoku is not solvable")
      return (false, board)
    }
  }
  if (move.length != 3) then return (false, board)
  else {
    val row = (move.charAt(0) - '1')
    val col = (move.charAt(1) - 'a')

    var number = if (move.charAt(2) == '0') then -1 else (move.charAt(2) - '0')

    if (number < (-1) || number > 9) {
      return (false, board)
    }
    if (sudoku_valid(row, col)) then {
      if (board(row)(col) == "0" && number == -1) {
        return (false, board)
      }

      if (number != -1) {
        if (board(row)(col) != "-1") return (false, board)


        for (i <- 0 until board.length)
          if board(i)(col).substring(0, 1) == number.toString then return (false, board)

        for (i <- 0 until board(0).length)
          if board(row)(i).substring(0, 1) == number.toString then return (false, board)
        val box_start_x = row / 3
        val box_start_y = col / 3
        for (i <- box_start_x * 3 until (box_start_x * 3 + 3)) {
          for (j <- box_start_y * 3 until (box_start_y * 3 + 3))
            if board(i)(j).substring(0, 1) == number.toString then {
              return (false, board)
            }
        }
        board(row)(col) = number.toString
      }
      else {
        if board(row)(col).length > 1 then return (false, board)
        if board(row)(col) == "-1" then return (false, board)
        board(row)(col) = "-1"
      }
      (true, board)
    }
    else
      (false, board)
  }
}