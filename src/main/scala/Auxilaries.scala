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
  case "Brook" => new ImageIcon("src\\main\\scala\\pictures\\BlackRook.png").getImage()
  case "Wrook" => new ImageIcon("src\\main\\scala\\pictures\\WhiteRook.png").getImage()
  case "Bbishop" => new ImageIcon("src\\main\\scala\\pictures\\BlackBishop.png").getImage()
  case "Wbishop" => new ImageIcon("src\\main\\scala\\pictures\\WhiteBishop.png").getImage()
  case "Bking" => new ImageIcon("src\\main\\scala\\pictures\\BlackKing.png").getImage()
  case "Wking" => new ImageIcon("src\\main\\scala\\pictures\\WhiteKing.png").getImage()
  case "Bpawn" => new ImageIcon("src\\main\\scala\\pictures\\BlackPawn.png").getImage()
  case "Wpawn" => new ImageIcon("src\\main\\scala\\pictures\\WhitePawn.png").getImage()
  case "B1pawn" => new ImageIcon("src\\main\\scala\\pictures\\BlackPawn.png").getImage()
  case "W1pawn" => new ImageIcon("src\\main\\scala\\pictures\\WhitePawn.png").getImage()
  case "Bqueen" => new ImageIcon("src\\main\\scala\\pictures\\BlackQueen.png").getImage()
  case "Wqueen" => new ImageIcon("src\\main\\scala\\pictures\\WhiteQueen.png").getImage()
  case "Bknight" => new ImageIcon("src\\main\\scala\\pictures\\BlackKnight.png").getImage()
  case "Wknight" => new ImageIcon("src\\main\\scala\\pictures\\WhiteKnight.png").getImage()
  case  _  => new ImageIcon("").getImage
}