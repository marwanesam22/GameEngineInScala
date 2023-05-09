import java.awt.{BasicStroke, Color, Font, Graphics, Graphics2D}
import javax.swing.{JFrame, JPanel, WindowConstants}

def Chess_drawer(board: Array[Array[String]]): Unit = {
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
  val color_1 = new Color(199, 232, 202)
  val color_2 = new Color(93, 156, 89)
  val panel = new JPanel() {
    override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)

      //Grid color and size
      for (i <- 0 until board.length) {
        for (j <- 0 until board(0).length) {
          if ((i + j) % 2 == 0)
            g.setColor(color_1)
          else
            g.setColor(color_2)
          g.fillRect(starting_point_of_board_x + i * spacing_between_squares, starting_point_of_board_y + j * spacing_between_squares, spacing_between_squares, spacing_between_squares)
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

      for (i <- 0 until board.length) {
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(numbers(i), starting_point_of_board_x - 30, starting_point_of_board_y + 50 + spacing_between_squares * i)
      }

      for (i <- 0 until board(0).length) {
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(letters(i), starting_point_of_board_x + 30 + spacing_between_squares * i, 730)
      }

      for (i <- 0 until board.length) {
        for (j <- 0 until board(0).length) {
          g.drawImage(piece_to_image(board(i)(j)), starting_point_of_board_x + 5 + spacing_between_squares * j, starting_point_of_board_y + 5 + spacing_between_squares * i, 70, 70, null)
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

def Chess_controller(state: (Int, Array[Array[String]]), move: String): (Boolean, Array[Array[String]]) = {

  def Chess_valid(i: Int, j: Int): Boolean = i >= 0 && j >= 0 && i < 8 && j < 8

  def move_is_valid(pieceIndex: String, i: Int, j: Int, to_i: Int, to_j: Int, grid: Array[Array[String]]): (Boolean) = {
    var flag = false
    val piece = pieceIndex.substring(1, pieceIndex.length)
    var path_is_clear = true
    val dx = Math.abs(i - to_i)
    val dy = Math.abs(j - to_j)
    piece match {
      case "knight" => {
        val available = Array((i + 1, j + 2), (i + 1, j - 2), (i - 1, j + 2), (i - 1, j - 2), (i + 2, j + 1), (i + 2, j - 1), (i - 2, j + 1), (i - 2, j - 1))
        for (x <- available) {
          if (x._1 == to_i && x._2 == to_j) {
            flag = true
          }
        }
      }
      case "queen" => {
        if (dx == dy || dx == 0 || dy == 0) {
          val rowStep = if (to_i > i) 1 else if (to_i < i) -1 else 0
          val colStep = if (to_j > j) 1 else if (to_j < j) -1 else 0

          var row = i + rowStep
          var col = j + colStep

          while (row != to_i || col != to_j) {
            if (Chess_valid(row, col) && grid(row)(col) != "") {
              // There is a piece present at this position
              path_is_clear = false
            }
            row += rowStep
            col += colStep
          }
          if (path_is_clear) {
            flag = true
          }
        }
      }
      case "bishop" => {
        if (dx == dy) {
          val rowStep = if (to_i > i) 1 else if (to_i < i) -1 else 0
          val colStep = if (to_j > j) 1 else if (to_j < j) -1 else 0

          var row = i + rowStep
          var col = j + colStep

          while (row != to_i || col != to_j) {
            if (Chess_valid(row, col) && grid(row)(col) != "") {
              // There is a piece present at this position
              path_is_clear = false
            }
            row += rowStep
            col += colStep
          }
          if (path_is_clear) {
            flag = true
          }
        }
      }
      case "rook" => {
        if (dx == 0 || dy == 0) {
          val rowStep = if (to_i > i) 1 else if (to_i < i) -1 else 0
          val colStep = if (to_j > j) 1 else if (to_j < j) -1 else 0

          var row = i + rowStep
          var col = j + colStep

          while (row != to_i || col != to_j) {
            if (Chess_valid(row, col) && grid(row)(col) != "") {
              // There is a piece present at this position
              path_is_clear = false
            }
            row += rowStep
            col += colStep
          }
          if (path_is_clear) {
            flag = true
          }
        }
      }
      case "king" => {
        val available = Array((i + 1, j), (i - 1, j), (i, j + 1), (i, j - 1), (i + 1, j + 1), (i + 1, j - 1), (i - 1, j + 1), (i - 1, j - 1))
        for (x <- available) {
          if (x._1 == to_i && x._2 == to_j) {
            flag = true
          }
        }
      }
      case _ => { // pawn
        if (pieceIndex.charAt(0) == 'B') then {
          if ((to_i == i + 1 && to_j == j && grid(to_i)(to_j) == "")
            || (to_i == (i + 1) && to_j == (j + 1) && grid(to_i)(to_j) != "")
            || (to_i == (i + 1) && to_j == (j - 1) && grid(to_i)(to_j) != "")
            || (grid(i)(j).charAt(1) == '1' && to_i == i + 2 && to_j == j
            && grid(to_i)(to_j) == "" && grid(i + 1)(j) == "")) {
            flag = true
          }
        }
        else {
          if ((to_i == i - 1 && to_j == j && grid(to_i)(to_j) == "")
            || (to_i == (i - 1) && to_j == (j + 1) && grid(to_i)(to_j) != "")
            || (to_i == (i - 1) && to_j == (j - 1) && grid(to_i)(to_j) != "")
            || (grid(i)(j).charAt(1) == '1' && to_i == i - 2 && to_j == j
            && grid(to_i)(to_j) == "" && grid(i - 1)(j) == "")) {
            flag = true
          }
        }
      }
    }
    flag
  }

  val board = state._2
  val turn = state._1
  val current_color_turn = if turn == 1 then 'W' else 'B';
  if (move.length != 5) return (false, board)
  val row_piece = (move.charAt(0) - '1')
  val col_piece = (move.charAt(1) - 'a')
  val row_to = (move.charAt(3) - '1')
  val col_to = (move.charAt(4) - 'a')


  if (row_piece == row_to && col_piece == col_to)
    return (false, board)
  if (!Chess_valid(row_piece, col_piece) || !Chess_valid(row_to, col_to))
    return (false, board)
  if (board(row_piece)(col_piece) == "")
    return (false, board)
  if (board(row_to)(col_to) == "Bking" || board(row_to)(col_to) == "Wking")
    return (false, board)
  if ((board(row_to)(col_to) != "") && (board(row_to)(col_to).charAt(0) == board(row_piece)(col_piece).charAt(0)))
    return (false, board)
  if (board(row_piece)(col_piece).charAt(0) != current_color_turn)
    return (false, board)

  val change_grid = move_is_valid(board(row_piece)(col_piece), row_piece, col_piece, row_to, col_to, board)
  if (change_grid) {
    if (board(row_piece)(col_piece) == "B1pawn")
      board(row_piece)(col_piece) = "Bpawn"
    if (board(row_piece)(col_piece) == "W1pawn")
      board(row_piece)(col_piece) = "Wpawn"
    board(row_to)(col_to) = board(row_piece)(col_piece)
    board(row_piece)(col_piece) = ""
    if(board(row_to)(col_to) == "Bpawn" && row_to == 7){
      board(row_to)(col_to) = "Bqueen"
    }
    if(board(row_to)(col_to) == "Wpawn" && row_to == 0){
      board(row_to)(col_to) = "Wqueen"
    }
  }

  (change_grid, board)

}