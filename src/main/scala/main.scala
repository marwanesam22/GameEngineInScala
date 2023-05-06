import java.awt.{Font, Graphics, Image}

import java.io.IOException
import scala.io.StdIn.*
import javax.swing.{ImageIcon, JFrame, JPanel, WindowConstants}
import scala.util.control.Breaks.*


def take_input() : (Array[Array[String]], Int) = {
  var initial_board : Array[Array[String]] = null
  var game_choice : Int = 0

  breakable {
    while (true) {
      println(
        """choose Game
          |1. chess
          |2. Tic-Tac-Toe
          |3. checkers
          |4. Connect-4
          |5. Sudoku
          |6. 8-Queens
          |""".stripMargin)
      try
        game_choice = readInt()
        game_choice match {
          //        case 1 => initial_board = init_chess()
          //          break()
          case 2 => initial_board = init_ticTacToe()
            break()
          //        case 3 => initial_board = init_checkers()
          //          break()
          //        case 4 => initial_board = init_Connect_4()
          //          break()
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

@main
def main(): Unit = {

  var input = take_input();
  var initial_board = input._1
  var game = input._2
//  game_engine(initial_board, eight_queens_drawer, eight_queens_controller)
  game match{
    case 2 => game_engine(initial_board, tic_tac_toe_drawer, tic_tac_toe_controller)
    case 5 => game_engine(initial_board, Sudoku_drawer, Sudoku_controller)
    case 6 => game_engine(initial_board, Eight_queens_drawer, Eight_queens_controller)
    case _ => println("NO")
  }
//  tic_tac_toe_drawer(initial_board)
  //controller (state, move) : bool, newState
  //drawer(state) : m4 by return ay 7aga
  //




}


