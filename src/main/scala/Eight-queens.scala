import java.awt.{BasicStroke, BorderLayout, Color, Font, Graphics, Graphics2D, Image}
import javax.swing.{ImageIcon, JFrame, JPanel, WindowConstants}
import  java.awt.Window

def eight_queens_valid(row:Int,col:Int):Boolean ={
  if row >= 0 && row <= 7 && col >= 0 && col <= 7  then
    true

  else
    false
}

def Eight_queens_drawer(board:Array[Array[String]]): Unit = {
  val win = java.awt.Window.getWindows
  for (i <- 0 until win.length) {
    win(i).dispose()
  }
  val frame = new JFrame("Game_engine")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  val numbers = Array("1", "2", "3", "4", "5", "6", "7", "8")
  val letters = Array("a", "b", "c", "d", "e", "f", "g","h")
  val spacing_between_squares  = 80
  val size_of_board=640
  val starting_point_of_board_x = 70
  val starting_point_of_board_y = 60
  val color_1=new Color(199, 232, 202)
  val color_2=new Color(93, 156, 89)
  val panel = new JPanel() {
    override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)

      //Grid color and size
      for(i<-0 until board.length){
        for(j<-0 until board(0).length){
          if((i+j)%2==0)
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
      for( i<-0 to board.length ){
        g.drawLine(starting_point_of_board_x, starting_point_of_board_y + spacing_between_squares*i , starting_point_of_board_x+size_of_board, starting_point_of_board_y + spacing_between_squares*i)
      }
      //This for vertical lines
      for (i <- 0 to board(0).length) {
        g.drawLine(starting_point_of_board_x + spacing_between_squares*i, starting_point_of_board_y, starting_point_of_board_x + spacing_between_squares*i, starting_point_of_board_y+size_of_board)
      }

      for(i<-0 until board.length){
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(numbers(i), starting_point_of_board_x-30, starting_point_of_board_y+50 + spacing_between_squares*i)
      }

      for(i<-0 until board(0).length){
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(letters(i), starting_point_of_board_x+30 + spacing_between_squares*i, 730)
      }

      for(i<-0 until board.length){
        for(j<-0 until board(0).length){
          g.drawImage(piece_to_image(board(i)(j)), starting_point_of_board_x + 5 + 80 * j, starting_point_of_board_y + 5 + 80 * i, 70, 70, null)
        }
      }



    }
  }
  frame.setSize(800, 800)
  frame.add(panel)
  frame.setAlwaysOnTop (true);
  frame.setResizable(false)
  frame.setVisible(true)
}
def Eight_queens_controller(state : (Int, Array[Array[String]]), move : String):(Boolean, Array[Array[String]])={
  var board =state._2
if(move.length!=3) then{
  (false, board)
}
else {
  val ch = (move.charAt(0)-'1')
  val ch2 =(move.charAt(1)-'a')
  val delete=move.charAt(2)
  var count=0
  val fill=if delete=='+' then "q" else if delete=='-' then null else return (false,board)

  if(!eight_queens_valid(ch,ch2))then{
     (false,board)
  }
  else{
  var i=ch+1
  var j=ch2+1
  if (delete == '+' && (board(ch)(ch2) == "Q" || board(ch)(ch2)=="q")) then
    return (false, board)

  if (delete == '-' && board(ch)(ch2) != "Q") then
    return (false, board)
  board(ch)(ch2) = if delete=='+' then "Q" else null

  while(i < board.length && j < board(0).length){
    board(i)(j)=fill
    i+=1
    j+=1
  }
  i=ch-1
  j=ch2-1
  while (i >= 0 && j >= 0) {
    board(i)(j) = fill
    i -= 1
    j -= 1
  }
  i=ch+1
  j=ch2-1
  while (i < board.length && j >= 0) {
    board(i)(j) = fill
    i += 1
    j -= 1
  }
  i = ch - 1
  j = ch2 + 1
  while (i >=0 && j < board(0).length) {
    board(i)(j) = fill
    i -= 1
    j += 1
  }
  i=ch-1
  while(i>=0){
    board(i)(ch2)=fill
    i-=1
  }
  i=ch+1
  while (i < board.length) {
    board(i)(ch2) = fill
    i += 1
  }
  j=ch2-1
  while (j >= 0) {
    board(ch)(j) = fill
    j -= 1
  }
  j = ch2 + 1
  while (j < board(0).length) {
    board(ch)(j) = fill
    j += 1
  }
  (true,board)
  }
}

}
