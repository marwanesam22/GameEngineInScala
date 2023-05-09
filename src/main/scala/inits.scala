import javax.swing.ImageIcon

//def init_chess() : Array[Array[String]] = {
//  var String: Array[Array[String]] = Array.ofDim[String](8, 8)
//  for (i <- 0 until 3; j <- 0 until 4) {
//    x(i)(j) = 0
//  }
//  return x
//}
def init_ticTacToe(): Array[Array[String]] = {
  var board: Array[Array[String]] = Array.ofDim[String](3, 3)
  for (i <- 0 until 3; j <- 0 until 3) {
    board(i)(j) = null
  }
  board
}
def init_checkers(): Array[Array[String]] = {
  var board: Array[Array[String]] = Array.ofDim[String](8, 8)
  for (i <- 0 until 8; j <- 0 until 8) {
    board(i)(j) = null
  }

  for (i <- 0 until 3; j <- 0 until 8) {
    if ((i + j) % 2 != 0) {
      board(i)(j) = "red_basic"
    }
  }

  for (i <- 7 to 5 by -1; j <- 0 until 8) {
    if ((i + j) % 2 != 0) {
      board(i)(j) = "black_basic"
    }
  }
  board
}
def init_8_queens(): Array[Array[String]] = {
  var board: Array[Array[String]] = Array.ofDim[String](8, 8)
  for (i <- 0 until board.length; j <- 0 until board(0).length)
    board(i)(j) = null

  board
}
//def init_sudoku() : Array[Array[String]] = {
//  var x: Array[Array[String]] = Array.ofDim[String](8, 8)
//  for (i <- 0 until 3; j <- 0 until 4) {
//    x(i)(j) = 0
//  }
//  return x
//}
def init_connect_4(): Array[Array[String]] = {
  var board: Array[Array[String]] = Array.ofDim[String](6, 7)
  for (i <- 0 until 6; j <- 0 until 7) {
    board(i)(j) = "gray_circle"
  }
  board
}