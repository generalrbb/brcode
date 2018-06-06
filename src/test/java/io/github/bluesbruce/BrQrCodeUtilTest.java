package io.github.bluesbruce;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
    BrQrCodeUtil.brEncode("123456789012", BarcodeFormat.EAN_13, new File("d:/temp/EAN-13.jpg"));
    BrQrCodeUtil.brEncode("1234567890128", BarcodeFormat.EAN_13, new File("d:/temp/EAN-13-1.jpg"), 150, 50);
    BrQrCodeUtil.brEncode("12345670", BarcodeFormat.EAN_8, new File("d:/temp/EAN-8.jpg"));
  }

  @Test
  @Ignore
  public void brEncodeUpc() throws IOException, WriterException {
    BrQrCodeUtil.brEncode("12345678901", BarcodeFormat.UPC_A, new File("d:/temp/UPC-A.jpg"));
    BrQrCodeUtil.brEncode("12345670", BarcodeFormat.UPC_E, new File("d:/temp/UPC-E.jpg"));
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
    String qrStr = BrQrCodeUtil.decode(new File("d:/temp/EAN-8.jpg"));
    System.out.println("条形码解码:" + qrStr);
  }
}