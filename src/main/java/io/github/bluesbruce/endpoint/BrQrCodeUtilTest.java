package io.github.bluesbruce.endpoint;

import com.google.zxing.NotFoundException;
import io.github.bluesbruce.util.BrQrCodeUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 二维码测试类
 * @author BBF
 */
@RestController
public class BrQrCodeUtilTest {

  @PostMapping("/br/decode")
  public String brDecode(@RequestParam("file") MultipartFile multiFile) throws NotFoundException, IOException {
    String fileName = multiFile.getOriginalFilename();
    // 获取文件后缀
    String prefix = fileName.substring(fileName.lastIndexOf("."));
    // 若须要防止生成的临时文件重复,能够在文件名后添加随机码

    try {
      File file = File.createTempFile(fileName, prefix);
      multiFile.transferTo(file);
      String qrStr = BrQrCodeUtil.decode(file);
      return qrStr;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

}
