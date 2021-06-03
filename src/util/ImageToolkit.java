package util; 
import java.awt.Image;
import java.awt.image.BufferedImage;  
import java.awt.image.RenderedImage;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import javax.imageio.ImageIO;  
 
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
 
public class ImageToolkit {  
	
	Image img;
 
    public ImageToolkit(String imgsrc) throws IOException {  
    	File srcfile = new File(imgsrc);
        this.img = ImageIO.read(srcfile); 
    }  
    
    public int getHeight(){    	       
    	return img.getHeight(null);
    }
    
    public int getWidth(){
    	return img.getWidth(null);
    }
    
    
 
    /** 
     * 圖像縮放 jpg格式 
     *  
     * @param imgsrc:原圖片文件路徑 
     * @param imgdist:生成的縮略圖片文件路徑 
     * @param widthdist:生成圖片的寬度 
     * @param heightdist:生成圖片的高度 
     */ 
    public void reduceImg(String imgdist, int widthdist, int heightdist) {  
        try { 
            BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist, BufferedImage.TYPE_INT_RGB);  
            /* 
             * Image.SCALE_SMOOTH 的縮略算法  生成縮略圖片的平滑度的 
             * 優先級比速度高 生成的圖片質量比較好 但速度慢 
             */ 
            tag.getGraphics().drawImage(img.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);  
            img.flush();
            FileOutputStream out = new FileOutputStream(imgdist);  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag);  
            out.close();  
 
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
      
    public void reduceImgGif(String imgdist, int widthdist, int heightdist) {  
        try {  
             
 
            BufferedImage tag = new BufferedImage((int) widthdist,  
                    (int) heightdist, BufferedImage.TYPE_INT_RGB);  
            /* 
             * Image.SCALE_SMOOTH 的縮略算法  生成縮略圖片的平滑度的 
             * 優先級比速度高 生成的圖片質量比較好 但速度慢 
             */ 
            tag.getGraphics().drawImage(img.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0, null);  
              
//          FileOutputStream out = new FileOutputStream(imgdist);  
//          JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
//          encoder.encode(tag);  
//          out.close();  
            ImageIO.write(tag, "gif", new File(imgdist));  
 
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
      
    /** 
     * 根據圖片路徑 讀取圖片文件 
     * @param fileName 
     * @return 
     */ 
    public static BufferedImage readImage(String fileName) {  
        BufferedImage bi = null;  
        try {  
            bi = ImageIO.read(new File(fileName));  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
        }  
        return bi;  
    }  
 
    /** 
     * 生成新的圖片文件 
     * @param im 
     * @param formatName 
     * @param fileName 
     * @return 
     */ 
    public static boolean writeImage(RenderedImage im, String formatName,  
            String fileName) {  
        boolean result = false;  
        try {  
            result = ImageIO.write(im, formatName, new File(fileName));  
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
        }  
        return result;  
    }  
 
    /** 
     * 轉換圖片格式 到 jpg 
     * @param im 
     * @param fileName 
     * @return 
     */ 
    public static boolean writeJPEGImage(RenderedImage im, String fileName) {  
        return writeImage(im, "JPEG", fileName);  
    }  
 
    /** 
     * 轉換圖片格式 到 gif  不知到好用不 
     * @param im 
     * @param fileName 
     * @return 
     */ 
    public static boolean writeGIFImage(RenderedImage im, String fileName) {  
        return writeImage(im, "GIF", fileName);  
    }  
 
    public static boolean writePNGImage(RenderedImage im, String fileName) {  
        return writeImage(im, "PNG", fileName);  
    }  
 
    public static boolean writeBMPImage(RenderedImage im, String fileName) {  
        return writeImage(im, "BMP", fileName);  
    }  
}  