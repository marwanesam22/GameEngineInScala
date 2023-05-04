import java.awt.image.BufferedImage
import java.awt.{BasicStroke, BorderLayout, Color, Dimension, Font, Graphics, Graphics2D, GridLayout, Image, Window}
import javax.swing.{BorderFactory, ImageIcon, JButton, JFrame, JLabel, JPanel, SwingConstants, WindowConstants}
import java.awt.{Graphics2D, RenderingHints}



def tic_tac_toe_drawer(board:Array[Array[String]]): Unit = {
  val win = java.awt.Window.getWindows
  for (i <- 0 until win.length) {
    win(i).dispose()
  }
    val frame = new JFrame("Game_engine")
  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  val numbers = Array("3", "2", "1")
  val letters = Array("a", "b", "c")
  frame.setLayout(new BorderLayout())
  val game_name=new JLabel("Tic-Tac-Toe")
//mn hena
  val panel = new JPanel(new GridLayout(3, 3))
  for (i <- 0 until board.length) {
    for (j <- 0 until board(0).length) {
      val cell = new JLabel()
      cell.setIcon(piece_to_image(board(i)(j)))
      cell.setPreferredSize(new Dimension(100, 100))
      cell.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(10, 10, 10, 10, Color.CYAN),
                cell.getBorder))

        cell.setHorizontalAlignment(SwingConstants.CENTER)
      panel.add(cell)
    }
  }
//l hena
/*
* - - -
* - X -
* - - -
* */
//  val panel = new JPanel() {
//
//    override def paintComponent(g: Graphics): Unit = {
//      super.paintComponent(g)
//
//      //Grid color and size
//      g.setColor(new Color(199, 232, 202))
//      g.fillRect(250, 250, 300, 300)
//
//
//      g.setColor(new Color(93, 156, 89))
//      val g2d = g.asInstanceOf[Graphics2D]
//      g2d.setStroke(new BasicStroke(5))
//      //this for horizontal lines
//      for( i<-0 to 3 ){
//        g.drawLine(0,  100*i , 300, 300 + 100*i)
//      }
//      //This for vertical lines
//      for (i <- 0 to 3) {
//        g.drawLine( 100*i, 0,   100*i, 300)
//      }
//
//      for(i<-0 to 2){
//        g.setColor(Color.BLACK)
//        g.setFont(new Font("Serif", Font.BOLD, 30))
//        g.drawString(numbers(i), 220, 305 + 100*i)
//      }
//
//      for(i<-0 to 2){
//        g.setColor(Color.BLACK)
//        g.setFont(new Font("Serif", Font.BOLD, 30))
//        g.drawString(letters(i), 280 + 110*i, 580)
//      }
//
//      for(i<-0 to 2){
//        for(j<-0 to 2){
//          g.drawImage(piece_to_image(board(i)(j)), 250+5 + 100*j, 250+5 + 100*i, 90, 90, null)
//        }
//      }
//
//    }
//  }]
  game_name.setHorizontalAlignment(SwingConstants.CENTER)

  frame.add(game_name)
  frame.add(panel)
//  frame.setSize(800, 800)
//  frame.setAlwaysOnTop (true)
//  frame.setLocationRelativeTo(null)
  frame.pack()
  frame.setAlwaysOnTop(true)
  frame.setVisible(true)
//  frame.setResizable(false)

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