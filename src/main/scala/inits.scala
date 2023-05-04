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
  for (i <- 0 until board.length; j <- 0 until board(0).length) {
    board(i)(j) = null
  }
  board
}
//def init_checkers() : Array[Array[String]] = {
//  var board: Array[Array[String]] = Array.ofDim[String](8, 8)
//  for (i <- 0 until 3; j <- 0 until 4) {
//    board(i)(j) = 0
//  }
//  return x
//}
def init_8_queens() : Array[Array[String]] = {
  var board: Array[Array[String]] = Array.ofDim[String](8, 7)
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
//def init_Connect_4() : Array[Array[String]] = {
//  var x: Array[Array[String]] = Array.ofDim[String](8, 8)
//  for (i <- 0 until 8; j <- 0 until 8) {
//    x(i)(j) = 0
//  }
//  return x
//}