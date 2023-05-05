package io.github.bluesbruce;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import io.github.bluesbruce.util.BrQrCodeUtil;
import io.github.bluesbruce.util.ImageUtil;
import org.junit.Ignore;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 二维码测试类
 * @author BBF
 */
public class BrQrCodeUtilTest {
  private static final File QR_CODE_FILE = new File("d:/temp/qrcode.jpg");

  @Test
  @Ignore
  public void qrEncode() throws IOException, WriterException {
    BrQrCodeUtil.qrEncode("hello,中国", QR_CODE_FILE, 200, 200);
    System.out.println("QrCode - success");
  }

  @Test
  @Ignore
  public void checkUpcEan() throws FormatException {
    // 计算校验码，如果是UPC算法，需要在前面补一个0
    String upca = "0" + "1234567";
    int ck = BrQrCodeUtil.getUpcEanChecksum(upca);
    System.out.println("upca：" + upca.substring(1) + ck);
    // 计算EAN13校验码
    String ean13 = "123456789012";
    int sk = BrQrCodeUtil.getUpcEanChecksum(ean13);
    System.out.println("upca：" + ean13 + sk);
  }

  @Test
  @Ignore
  public void brEncodeEan() throws IOException, WriterException {
    //自动补充校验码
//    BrQrCodeUtil.brEncode("123456789012", BarcodeFormat.EAN_13, new File("d:/temp/EAN-13.jpg"));
   BrQrCodeUtil.brEncode("1234567890128", BarcodeFormat.EAN_13, new File("/Users/sjs/Desktop/test.png"), 150, 50);
//    BrQrCodeUtil.brEncode("12345670", BarcodeFormat.EAN_8, new File("d:/temp/EAN-8.jpg"));
  }

  @Test
  @Ignore
  public void brEncodeUpc() throws IOException, WriterException {
    BrQrCodeUtil.brEncode("12363815801", BarcodeFormat.UPC_A, new File("/Users/sjs/Desktop/test.png"));
    //BrQrCodeUtil.brEncode("12345670", BarcodeFormat.UPC_E, new File("d:/temp/UPC-E.jpg"));
  }

  @Test
  @Ignore
  public void brEncodeCode() throws IOException, WriterException {
    BrQrCodeUtil.brEncode("*123456*", BarcodeFormat.CODE_39, new File("d:/temp/CODE-39.jpg"));
    BrQrCodeUtil.brEncode("12-34 Code-128; V25b", BarcodeFormat.CODE_128, new File("d:/temp/CODE-128.jpg"));
  }

  @Test
  @Ignore
  public void qrDecode() throws IOException, NotFoundException {
    String qrStr = BrQrCodeUtil.decode(QR_CODE_FILE);
    System.out.println("二维码解码:" + qrStr);
  }

  @Test
  @Ignore
  public void brDecode() throws IOException, NotFoundException {
    String qrStr = BrQrCodeUtil.decode(new File("/Users/sjs/Desktop/test.png"));
    System.out.println("条形码解码:" + qrStr);
  }

  @Test
  @Ignore
  public void pdf417Decode() throws IOException, NotFoundException, WriterException {
    File file = new File("d:/temp/pdf417.jpg");
    String str = "hello，中国";
    BrQrCodeUtil.pdf417Encode(str, file, 300, 150);
    // 解码
    String deStr = BrQrCodeUtil.decode(file);
    System.out.println("PDF417解码:" + deStr);
  }

  @Test
  public void yasuo() {
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(new File("/Users/sjs/Desktop/test.png"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    BufferedImage bufferedImage = null;
    try {
      bufferedImage = ImageIO.read(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    int newHeight =1;
    int newWidth =150;
    BufferedImage image = new BufferedImage(newWidth,newHeight, BufferedImage.TYPE_INT_BGR);
    Graphics garphics = image.createGraphics();
    garphics.drawImage(bufferedImage, 0, 0, newWidth, newHeight, null);
    try {
      ImageUtil.write(image,new File("/Users/sjs/Desktop/test2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
