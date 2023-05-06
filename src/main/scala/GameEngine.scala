import javax.swing.JFrame
import scala.io.StdIn.*

def game_engine(initial_board : Array[Array[String]],
                drawer : Array[Array[String]] => Unit,
                controller : ((Int, Array[Array[String]]), String) => (Boolean, Array[Array[String]])): Unit = {
  var board = initial_board;
  var turn = 1;
  while(true){
    drawer(board)
    println("""Please Enter a valid move for the game chosen""")
    var move = readLine();
    var ret = controller((turn, board), move)
    if(!ret._1){
      println("Invalid move");
    }else{
      board = ret._2;
      turn = if turn == 1 then 2 else 1;
    }
  }
}