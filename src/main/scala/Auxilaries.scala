import java.awt.Image
import javax.swing.ImageIcon
val path ="C:\\Users\\madyelzainy\\OneDrive\\Documents\\New folder (2)\\GameEngineInScala\\src\\main\\scala\\pictures\\"
def piece_to_image(piece: String): Image = {
  new ImageIcon(path+piece+".png").getImage()
}