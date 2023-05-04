import java.awt.{BasicStroke, BorderLayout, Color, Font, Graphics, Graphics2D, Image}
import javax.swing.{ImageIcon, JFrame, JPanel, WindowConstants}
import  java.awt.Window


def eight_queens_drawer(board:Array[Array[String]]): Unit = {
  val win = java.awt.Window.getWindows
  for (i <- 0 until win.length) {
    win(i).dispose()
  }
  val frame = new JFrame("Game_engine")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  val numbers = Array("8", "7", "6", "5", "4", "3", "2", "1")
  val letters = Array("a", "b", "c", "d", "e", "f", "g")

  val panel = new JPanel() {
    override def paintComponent(g: Graphics): Unit = {
      super.paintComponent(g)

      //Grid color and size
      g.setColor(new Color(199, 232, 202))
      g.fillRect(250, 250, 400, 400)


      g.setColor(new Color(93, 156, 89))
      val g2d = g.asInstanceOf[Graphics2D]
      g2d.setStroke(new BasicStroke(5))
      //this for horizontal lines
      for( i<-0 to board.length ){
        g.drawLine(250, 250 + 50*i , 650, 250 + 50*i)
      }
      //This for vertical lines
      for (i <- 0 to board(0).length) {
        g.drawLine(250 + 50*i, 250, 250 + 50*i, 650)
      }

      for(i<-0 until board.length){
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(numbers(i), 220, 290 + 50*i)
      }

      for(i<-0 until board(0).length){
        g.setColor(Color.BLACK)
        g.setFont(new Font("Serif", Font.BOLD, 30))
        g.drawString(letters(i), 280 + 110*i, 580)
      }

//      for(i<-0 until board.length){
//        for(j<-0 until board(0).length){
//          g.drawImage(piece_to_image(board(i)(j)), 250+5 + 100*j, 250+5 + 100*i, 90, 90, null)
//        }
//      }



    }
  }

  frame.add(panel)
  frame.setAlwaysOnTop (true);
  frame.setResizable(false)
  frame.setVisible(true)
}