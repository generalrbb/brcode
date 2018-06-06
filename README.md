# brcode
条形码的组件网上有很多，除了``zxing``之外，别的都是N年不维护了。而``zxing``只生成纯条码。

## 项目介绍
基于``zxing``，实现条形码带数字输出。参照标准输出格式，在``zxing``生成的纯条码图片的基础上做了调整，加入文本内容。


编码格式 | 说明 | 效果展示
---- | ---- | ----
EAN-13 | 十二位数字 + 一位校验码 | ![](./docs/EAN-13.jpg)
EAN-8 | 七位数字 + 一位校验码 | ![](./docs/EAN-8.jpg)
CODE-39 | 字符和数字，不定长 | ![](./docs/CODE-39.jpg)
CODE-128 | ASCII0-127，不定长 |![](./docs/CODE-128.jpg)
UPC-A | 十一位数字 + 一位校验码 | ![](./docs/UPC-A.jpg)
UPC-E | 七位数字 + 一位校验码 | ![](./docs/UPC-E.jpg)
QR Code | 二维码 | ![](./docs/qrcode.jpg)

## 使用说明
主要是对上面六种条码的格式进行了调整，其他的条码格式直接在底部居中显示文本。

以EAN-13为例
````java
BrQrCodeUtil.brEncode(条码内容, BarcodeFormat.EAN_13, 文件对象);

// 如果条码内容是12位数字，则自动计算出第十三位。
// 如果是13位数字，会进行校验，失败抛出异常。
````
主要支持
``com.google.zxing.BarcodeFormat``
- EAN_8
- EAN_13
- UPC_A
- UPC_E
- CODE_39
- CODE_128