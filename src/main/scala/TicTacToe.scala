import java.awt.{BasicStroke, BorderLayout, Color, Font, Graphics, Graphics2D, Image}
import javax.swing.{ImageIcon, JFrame, JPanel, WindowConstants}


def tic_tac_toe_drawer(board:Array[Array[String]]): Unit = {
  val frame = new JFrame("Game_engine")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  val numbers = Array("3", "2", "1")
  val letters = Array("a", "b", "c")

  val panel = new JPanel() {
    override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)

      //Grid color and size
      g.setColor(new Color(199, 232, 202))
      g.fillRect(250, 250, 300, 300)


      g.setColor(new Color(93, 156, 89))
      val g2d = g.asInstanceOf[Graphics2D]
      g2d.setStroke(new BasicStroke(5))
      //this for horizontal lines
      for( i<-0 to 3 ){
        g.drawLine(250, 250 + 100*i , 550, 250 + 100*i)
      }
      //This for vertical lines
      for (i <- 0 to 3) {
        g.drawLine(250 + 100*i, 250, 250 + 100*i, 550)
      }

      for(i<-0 to 2){
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(numbers(i), 220, 305 + 100*i)
      }

      for(i<-0 to 2){
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(letters(i), 280 + 110*i, 580)
      }

      for(i<-0 to 2){
        for(j<-0 to 2){
          g.drawImage(piece_to_image(board(i)(j)), 250+5 + 100*j, 250+5 + 100*i, 90, 90, null)
        }
      }



    }
  }

  frame.add(panel)
  frame.setSize(800, 800)
  frame.setResizable(false)
  frame.setLocationRelativeTo(null)
  frame.setVisible(true)
}

def tic_tac_toe_controller(state : (Int, Array[Array[String]]), move : String) : (Boolean, Array[Array[String]]) = {
  var turn = state._1
  var board = state._2;

  val row : Int = move.charAt(0) match{
    case '1' => 2
    case '2' => 1
    case '3' => 0
    case _ => -1
  }
  println(s"Row is $row")

  val col : Int = move.charAt(1) match{
    case 'a' => 0
    case 'b' => 1
    case 'c' => 2
    case _ => -1
  }
  println(s"Col is $col")

  if( row < 0 || row > 2 || col < 0 || col > 2 || board(row)(col) != null){
    (false, board)
  }
  else{
    board(row)(col) = if turn == 1 then "X" else "O";
    (true, board)
  }


}

