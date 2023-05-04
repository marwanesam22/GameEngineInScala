import java.awt.Image
import javax.swing.ImageIcon

import java.awt.image.BufferedImage
import java.awt.Graphics2D

def rescaleImageIcon(icon: ImageIcon, width: Int, height: Int): ImageIcon = {
  val bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
  val graphics2D = bufferedImage.createGraphics()
  graphics2D.drawImage(icon.getImage, 0, 0, width, height, null)
  graphics2D.dispose()
  new ImageIcon(bufferedImage)
}

def piece_to_image(piece:String):ImageIcon = piece match {
  case "X" => new ImageIcon("src\\main\\scala\\pictures\\X_test.png")
  case "O" => new ImageIcon("src\\main\\scala\\pictures\\O.png")
  case "Circle"=>new ImageIcon("src\\main\\scala\\pictures\\Circle.png")
//  case "a" => new ImageIcon("src\\main\\scala\\pictures\\a.png").getImage()
//  case "b" => new ImageIcon("src\\main\\scala\\pictures\\b.png").getImage()
//  case "c" => new ImageIcon("src\\main\\scala\\pictures\\c.png").getImage()
//  case "1" => new ImageIcon("src\\main\\scala\\pictures\\1.png").getImage()
//  case "2" => new ImageIcon("src\\main\\scala\\pictures\\2.png").getImage()
//  case "3" => new ImageIcon("src\\main\\scala\\pictures\\3.png").getImage()
  case  _  => new ImageIcon("")
}