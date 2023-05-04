import java.awt.{Font, Graphics, Image}
import java.io.IOException
import scala.io.StdIn.*
import javax.swing.{ImageIcon, JFrame, JPanel, WindowConstants}
import scala.util.control.Breaks.*


def take_input() : Array[Array[String]] = {
  var initial_board : Array[Array[String]] = null

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
        var game_choice = readInt()
        game_choice match {
          //        case 1 => initial_board = init_chess()
          //          break()
          case 2 => initial_board = init_ticTacToe()
            break()
          //        case 3 => initial_board = init_checkers()
          //          break()
          //        case 4 => initial_board = init_Connect_4()
          //          break()
          //        case 5 => initial_board = init_sudoku()
          //          break()
          //        case 6 => initial_board = init_8_queens()
          //          break()
          case _ => println("Enter valid Input")
        }

      catch
        case ioe: IOException => println("Got an IOException.")
        case nfe: NumberFormatException => println("Got a NumberFormatException.")



    }
  }
 initial_board
}

@main
def main(): Unit = {

  var initial_board = take_input();
  tic_tac_toe_drawer(initial_board)
  //controller (state, move) : bool, newState
  //drawer(state) : m4 by return ay 7aga
  //




}


