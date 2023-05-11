import java.awt.{BasicStroke, BorderLayout, Color, Font, Graphics, Graphics2D, Image}
import javax.swing.{ImageIcon, JFrame, JPanel, WindowConstants}


def tic_tac_toe_drawer(board: Array[Array[String]]): Unit = {
  val win = java.awt.Window.getWindows
  for (i <- 0 until win.length) {
    win(i).dispose()
  }
  val frame = new JFrame("Game_engine")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  val numbers = Array("1", "2", "3")
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
      for (i <- 0 to 3) {
        g.drawLine(250, 250 + 100 * i, 550, 250 + 100 * i)
      }
      //This for vertical lines
      for (i <- 0 to 3) {
        g.drawLine(250 + 100 * i, 250, 250 + 100 * i, 550)
      }

      for (i <- 0 to 2) {
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(numbers(i), 220, 305 + 100 * i)
      }

      for (i <- 0 to 2) {
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(letters(i), 280 + 110 * i, 580)
      }

      for (i <- 0 to 2) {
        for (j <- 0 to 2) {
          g.drawImage(piece_to_image(board(i)(j)), 250 + 5 + 100 * j, 250 + 5 + 100 * i, 90, 90, null)
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

def tic_tac_toe_controller(state: (Int, Array[Array[String]]), move: String): (Boolean, Array[Array[String]]) = {
  val turn = state._1
  val board = state._2;
  try {
    val row: Int = move.charAt(0) - '1'
    val col: Int = move.charAt(1) - 'a'

    if (row < 0 || row > 2 || col < 0 || col > 2 || board(row)(col) != null) {
      println("There is another letter here")
      (false, board)
    }
    else {
      board(row)(col) = if turn == 1 then "X" else "O";
      (true, board)
    }
  } catch {
    case ste: Exception => return (false, board)
  }
}
