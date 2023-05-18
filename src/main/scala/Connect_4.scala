import java.awt.{BasicStroke, Color, Font, Graphics, Graphics2D, RenderingHints}
import javax.swing.{JFrame, JPanel, WindowConstants}


def connect_4_drawer(board: Array[Array[String]]): Unit = {
  
  val frame = new JFrame("Game_engine")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  val numbers = Array("1", "2", "3", "4", "5", "6", "7")

  val panel = new JPanel() {
    override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)

      //Grid color and size
      val g2d = g.asInstanceOf[Graphics2D]
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
      g2d.setColor(new Color(87, 108, 188))
      g2d.fillRoundRect(50, 100, 700, 600, 20, 20)

      for (i <- 0 to 6) {
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(numbers(i), 90 + 100 * i, 730)
      }

      for (i <- 0 to 5) {
        for (j <- 0 to 6) {
          g.drawImage(piece_to_image(board(i)(j)), 50 + 5 + 100 * j, 100 + 5 + 100 * i, 90, 90, null)
        }
      }
    }
  }
  frame.add(panel)
  frame.setSize(800, 800)
  frame.setResizable(false)
  frame.setAlwaysOnTop(true);
  frame.setLocationRelativeTo(null)
  frame.setVisible(true)
}

def connect_4_controller(state: (Int, Array[Array[String]]), move: String): (Boolean, Array[Array[String]]) = {
  var turn = state._1
  var board = state._2;
  var col = 0
  var row = 0
  try
    col = move.charAt(0) - '1';
    if (col < 0 || col > 6 || board(0)(col) != "gray_circle") {
      (false, board)
    }
    else {
      for (i <- 1 to 5) {
        if (board(i)(col) == "gray_circle") {
          row = i;
        }
      }
      board(row)(col) = if turn == 1 then "red_circle" else "yellow_circle";
      (true, board)
    }
  catch
    case ste: Exception => return (false, board)
}