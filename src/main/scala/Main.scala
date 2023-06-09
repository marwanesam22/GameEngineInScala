import java.awt.{Font, Graphics, Image}
import java.io.{FileWriter, IOException}
import scala.io.StdIn.*
import javax.swing.{ImageIcon, JFrame, JPanel, WindowConstants}
import scala.util.control.Breaks.*
import scala.io.Source


object Main {
  private def take_input(): (Array[Array[String]], Int) = {
    var initial_board: Array[Array[String]] = null
    var game_choice: Int = 0

    breakable {
      while (true) {
        println(
          """Choose Game
            |1. Chess
            |2. Tic-Tac-Toe
            |3. Checkers
            |4. Connect-4
            |5. Sudoku
            |6. 8-Queens
            |""".stripMargin)
        try
          game_choice = readInt()
          game_choice match {
            case 1 => initial_board = init_chess()
              break()
            case 2 => initial_board = init_ticTacToe()
              break()
            case 3 => initial_board = init_checkers()
              break()
            case 4 => initial_board = init_connect_4()
              break()
            case 5 => initial_board = init_sudoku()
              break()
            case 6 => initial_board = init_8_queens()
              break()
            case _ => println("Enter valid Input")
          }

        catch {
          case ioe: IOException => println("Got an IOException.")
          case nfe: NumberFormatException => println("Got a NumberFormatException.")
        }


      }
    }
    (initial_board, game_choice)
  }

  def start_playing(): Unit = {
    val win = java.awt.Window.getWindows
    for (i <- 0 until win.length) {
      win(i).dispose()
    }
    val input = take_input();
    val initial_board = input._1
    val game = input._2
    game match {
      case 1 => game_engine(initial_board, Chess_drawer, Chess_controller)
      case 2 => game_engine(initial_board, tic_tac_toe_drawer, tic_tac_toe_controller)
      case 3 => game_engine(initial_board, checkers_drawer, checkers_controller)
      case 4 => game_engine(initial_board, connect_4_drawer, connect_4_controller)
      case 5 => game_engine(initial_board, Sudoku_drawer, Sudoku_controller)
      case 6 => game_engine(initial_board, Eight_queens_drawer, Eight_queens_controller)
      case _ => println("NO")
    }
  }
  def main(args: Array[String]): Unit = {
    start_playing();
  }
}