import java.awt.Image
import javax.swing.ImageIcon

def piece_to_image(piece: String): Image = piece match {

  case "X" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\X.png").getImage()
  case "O" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\O.png").getImage()
  case "Q" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\Queen.png").getImage()
  case "a" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\a.png").getImage()
  case "b" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\b.png").getImage()
  case "c" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\c.png").getImage()
  case "1" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\1.png").getImage()
  case "2" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\2.png").getImage()
  case "3" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\3.png").getImage()
  case "gray_circle" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\gray_circle.png").getImage()
  case "red_circle" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\red_circle.png").getImage()
  case "yellow_circle" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\yellow_circle.png").getImage()
  case "red_basic" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\red_basic.png").getImage()
  case "red_promoted" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\red_promoted.png").getImage()
  case "black_basic" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\black_basic.png").getImage()
  case "black_promoted" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\black_promoted.png").getImage()
  case "Brook" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\BlackRook.png").getImage()
  case "Wrook" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\WhiteRook.png").getImage()
  case "Bbishop" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\BlackBishop.png").getImage()
  case "Wbishop" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\WhiteBishop.png").getImage()
  case "Bking" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\BlackKing.png").getImage()
  case "Wking" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\WhiteKing.png").getImage()
  case "Bpawn" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\BlackPawn.png").getImage()
  case "Wpawn" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\WhitePawn.png").getImage()
  case "B1pawn" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\BlackPawn.png").getImage()
  case "W1pawn" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\WhitePawn.png").getImage()
  case "Bqueen" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\BlackQueen.png").getImage()
  case "Wqueen" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\WhiteQueen.png").getImage()
  case "Bknight" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\BlackKnight.png").getImage()
  case "Wknight" => new ImageIcon("E:\\Semester 4\\Paradigms\\GameEngineInScala\\src\\main\\scala\\pictures\\WhiteKnight.png").getImage()
  case _ => new ImageIcon("").getImage
}