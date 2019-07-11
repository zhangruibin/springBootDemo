# SSL DV证书生成

 ## 使用Git clone仓库
$ git clone https://github.com/letsencrypt/letsencrypt
$ cd letsencrypt
$ ./certbot-auto --help all


 ## 获取签名：
openssl req -new -newkey rsa:2048 -sha256 -nodes -out example_com.csr -keyout example_com.key -subj "/C=CN/ST=BeiJing/L=Beijing/O=BixinTec Inc./OU=Web Security/CN=zhangruibin.com"


 ## 生成两个文件：
-rw-r--r-- 1 root root 1029 Jul  8 04:05 example_com.csr
-rw-r--r-- 1 root root 1708 Jul  8 04:05 example_com.key

 ## 根据域名生成证书
./letsencrypt-auto certonly --standalone --email itbin@foxmail.com -d 92cnb.com -d www.92cnb.com
./letsencrypt-auto certonly --standalone --email itbin@foxmail.com -d zhangruibin.com -d www.zhangruibin.com

 ## 证书输出的文件
 Congratulations! Your certificate and chain have been saved at:
   /etc/letsencrypt/live/92cnb.com/fullchain.pem
   Your key file has been saved at:
   /etc/letsencrypt/live/92cnb.com/privkey.pem
   
  Congratulations! Your certificate and chain have been saved at:
   /etc/letsencrypt/live/zhangruibin.com/fullchain.pem
   Your key file has been saved at:
   /etc/letsencrypt/live/zhangruibin.com/privkey.pem
