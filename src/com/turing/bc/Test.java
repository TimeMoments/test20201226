package com.turing.bc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		  //1.先用编码器和解码器对中文按照UTF-8进行编码和解码
	      //对中文按照UTF-8进行编码
		String encode = URLEncoder.encode("测试", "UTF-8");
      System.out.println(encode);
      //对中文按照UTF-8进行解码
      String decode = URLDecoder.decode("%E6%B5%8B%E8%AF%95", "UTF-8");
      System.out.println(decode);

      //2.再用编码器和解码器对中文按照GBK进行编码和解码
      //对中文按照GBK进行编码
		  String encode2 = URLEncoder.encode("测试", "GBK");
      System.out.println(encode2);
      //对中文按照GBK进行解码
      String decode2 = URLDecoder.decode("%B2%E2%CA%D4", "GBK");
      System.out.println(decode2);
      
      //3.如果编码的字符集和解码的字符集不一样就会出现乱码
      
      String decode3 = URLDecoder.decode("%E6%B5%8B%E8%AF%95", "GBK");
      System.out.println("不同的字符集就不导致这样的结果："+decode3);

	}

}
