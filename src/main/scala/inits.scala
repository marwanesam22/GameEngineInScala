import javax.swing.ImageIcon
import scala.util.Random
import scala.annotation.tailrec
import scala.jdk.CollectionConverters.*
import scala.language.postfixOps
import scala.util.control.Breaks.break
import scala.util.control.*
//def init_chess() : Array[Array[String]] = {
//  var String: Array[Array[String]] = Array.ofDim[String](8, 8)
//  for (i <- 0 until 3; j <- 0 until 4) {
//    x(i)(j) = 0
//  }
//  return x
//}
def init_ticTacToe(): Array[Array[String]] = {
  val board: Array[Array[String]] = Array.ofDim[String](3, 3)
  for (i <- 0 until board.length; j <- 0 until board(0).length)
    board(i)(j) = null
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
  val board: Array[Array[String]] = Array.ofDim[String](8, 8)
  for (i <- 0 until board.length; j <- 0 until board(0).length)
    board(i)(j) = null
  board
}

//write a function that generate a valid sudoku table
def init_sudoku() : Array[Array[String]] = {
  var board: Array[Array[Int]] = Array.ofDim[Int](9, 9)
  val ans: Array[Array[String]] = Array.ofDim[String](9, 9)
  board=fill_values(board)
  for(i<-0 until ans.length){
    for(j<-0 until ans(0).length){
      if board(i)(j)!=0 then
      ans(i)(j)=(board(i)(j).toString+"N")
      else ans(i)(j)="-1"
    }
  }
  for (i <- 0 until ans.length) {
    for (j <- 0 until ans(0).length) {
      print(ans(i)(j) + " ")
    }
    println()
  }
  ans
}


//def init_Connect_4() : Array[Array[String]] = {
//  var x: Array[Array[String]] = Array.ofDim[String](8, 8)
//  for (i <- 0 until 8; j <- 0 until 8) {
//    x(i)(j) = 0
//  }
//  return x
//}
def Remove_KDigits(temp_mat : Array[Array[Int]] ) : Array[Array[Int]] = {
  var mat=temp_mat
  var count: Int = 30
  while (count != 0) {
    val cellId = random_generator(9 * 9) - 1
    // extract coordinates i and j
    val i = cellId / 9
    var j = cellId % 9
    if (j != 0) j = j - 1
    if (mat(i)(j) != 0) {
      count -= 1
      mat(i)(j) = 0
    }
  }
  mat
}
def fill_remaining(ti: Int, tj: Int, mat: Array[Array[Int]]): Array[Array[Int]] = {
  var mat1=mat
  var i: Int = ti
  var j: Int = tj

  while (i < 9) {
    while (j < 9) {
      if (mat1(i)(j) == 0) {
        var flag = false
        for (num <- 1 to 9) {
          if (check_If_Safe(i, j, num, mat1)) {
            mat(i)(j) = num
            val state = fill_remaining(i, j + 1, mat1)
            if (state != null) {
              mat1 = state
              flag = true
              return mat
            }
            mat1(i)(j) = 0
          }
        }
        if (!flag) return null
      }
      j += 1
    }
    j = 0
    i += 1
  }
  mat1
}
def unused_In_Col(j: Int, num: Int, mat: Array[Array[Int]]): Boolean = {
  for (i <- 0 until 9) {
    if (mat(i)(j) == num) then
      return false
  }

  true
}
def unused_In_Row(i: Int, num: Int,mat:Array[Array[Int]]): Boolean = {
  for (j <- 0 until 9) {
    if (mat(i)(j) == num) then
      return false
  }
  true
}
def check_If_Safe(i: Int, j: Int, num: Int,mat:Array[Array[Int]]): Boolean = unused_In_Row(i, num, mat) && unused_In_Col(j, num, mat) && unused_In_Box(i - i % 3, j - j % 3, num,mat)
def random_generator(num: Int): Int = Math.floor(Math.random * num + 1).toInt
def fill_box(row: Int, col: Int,temp_mat:Array[Array[Int]]): Array[Array[Int]] = {
  var mat=temp_mat
  var num = 0
  for (i <- 0 until 3) {
    for (j <- 0 until 3) {
      num = random_generator(9)
      while (!unused_In_Box(row, col, num, mat)) num = random_generator(9)
      mat(row + i)(col + j) = num
    }
  }
  mat
}
def unused_In_Box(rowStart: Int, colStart: Int, num: Int,mat:Array[Array[Int]]): Boolean = {
  for (i <- 0 until 3) {
    for (j <- 0 until 3) {
      if (mat(rowStart + i)(colStart + j) == num) return false
    }
  }
  true
}
def fill_diagonal(temp_mat:Array[Array[Int]]): Array[Array[Int]] = {
  var i = 0
  var mat = fill_box(i , i , temp_mat)
  i = i + 3
  while (i < 9) {
    mat=fill_box(i, i, mat)
    i = i + 3 // for diagonal box, start coordinates->i==j
  }
  mat
}
def fill_values(temp_mat : Array[Array[Int]] ): Array[Array[Int]] = {

  // Fill the diagonal of SRN x SRN matrices
  var mat = fill_diagonal(temp_mat)

  // Fill remaining blocks
  mat =fill_remaining(0 ,3,mat)

  // Remove Randomly K digits to make game
  mat = Remove_KDigits (mat)
  mat
}
