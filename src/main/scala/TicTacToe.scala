import java.awt.{BorderLayout, Color, Graphics, Image}
import javax.swing.{ImageIcon, JFrame, JPanel, WindowConstants}

def piece_to_image(piece:String):Image= piece match {
  case "X" => new ImageIcon("src\\main\\scala\\pictures\\X.png").getImage()
  case "O" => new ImageIcon("src\\main\\scala\\pictures\\O.png").getImage()
  case _ => throw new IllegalArgumentException ("Invalid color")
}
def tic_tac_toe_drawer(board:Array[Array[String]]): Unit = {
  val frame = new JFrame("Game_engine")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

  val panel = new JPanel() {
    override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)
        // Draw the board

        for (i <- 0 until board.length) {
          g.drawLine(i * 100, 0, i * 100, 300) //vertical lines

          g.drawLine(0, i * 100, 300, i * 100) //horizontal lines
        }

      // Draw  white squares of the board
      for (i <- 0 until board.length) {
        for (j <- 0 until board(0).length) {
          g.setColor(Color.WHITE)
          g.fillRect(i * 100+10, j * 100+10, 85, 85)
        }
      }

    }
  }
  panel.setSize(500, 500)
  frame.setLayout(new BorderLayout())
  frame.add(panel,BorderLayout.CENTER)

  frame.setSize(1000, 1000)
  frame.setResizable(false)
  frame.setLocationRelativeTo(null)
  frame.setVisible(true)
}

