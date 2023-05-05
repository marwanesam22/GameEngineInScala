import java.awt.Image
import javax.swing.ImageIcon

def piece_to_image(piece:String):Image = piece match {
  case "X" => new ImageIcon("src\\main\\scala\\pictures\\X.png").getImage()
  case "O" => new ImageIcon("src\\main\\scala\\pictures\\O.png").getImage()
  case "Q" => new ImageIcon("src\\main\\scala\\pictures\\Queen.png").getImage()
  case "a" => new ImageIcon("src\\main\\scala\\pictures\\a.png").getImage()
  case "b" => new ImageIcon("src\\main\\scala\\pictures\\b.png").getImage()
  case "c" => new ImageIcon("src\\main\\scala\\pictures\\c.png").getImage()
  case "1" => new ImageIcon("src\\main\\scala\\pictures\\1.png").getImage()
  case "2" => new ImageIcon("src\\main\\scala\\pictures\\2.png").getImage()
  case "3" => new ImageIcon("src\\main\\scala\\pictures\\3.png").getImage()
  case  _  => new ImageIcon("").getImage
}