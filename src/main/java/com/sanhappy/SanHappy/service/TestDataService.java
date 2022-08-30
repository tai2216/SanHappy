package com.sanhappy.SanHappy.service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sanhappy.SanHappy.dao.TestDataDao;
import com.sanhappy.SanHappy.model.TestData;

@Service
@Transactional
public class TestDataService {
	@Autowired
	private TestDataDao dao;
	
	/*
	 * PKCS5Padding / PKcs7 兩種padding方法皆可
	 * 
	 * @param content 69f23a1a98ca3d406692742ab7033f3b 16進制
	 * @param key 1538663015386630
	 * @return masget 2019
	 * */
	public static String decryptAES(String content, String key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
			//算法/模式/補碼方式
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			//解密後是16進制
			return new String(cipher.doFinal(parseHexStr2Byte(content)));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	
	/*
	 * 將16進制轉為二進制
	 * @param hexStr
	 * @return byte[]
	 * */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if(hexStr.length()<1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		
		for(int i= 0; i<hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2 ,i*2+1),16);
			int low = Integer.parseInt(hexStr.substring(i*2+1 ,i*2+2),16);
			result[i] = (byte) (high*16+low);
		
		}
		return result;
	}
	
	/*
	 * 可以將前端傳送的參數進行儲存並且查詢該table的所有資料返回
	 * 可以傳入一個Integer參數進行指定顯示的頁數 預設為最新一頁
	 * @param Integer,String,String,String
	 * @return Page<TestData>
	 * 
	 * */
	public Page<TestData> getAllData(Integer pageNumber,String number, String chinese, String english){
		//用來解密的金鑰 必須和前端加密使用的相同
		String key  ="1538663015386630";
		
		System.out.println("前端傳送過來的數字: "+number);
		System.out.println("前端傳送過來的中文: "+chinese);
		System.out.println("前端傳送過來的英文: "+english);
		try {
			//使用上述的解密方法decryptAES將從前端傳過來的參數進行解密
			String decryptedNumber = decryptAES(number,key);
			String decryptedChinese = decryptAES(chinese,key);
			String decryptedEnglish = decryptAES(english,key);
			//將解密後的資料儲存進資料庫
			dao.saveData(decryptedNumber, decryptedChinese, decryptedEnglish);
			//使用page 物件將資料庫中的資料取出後整理根據資料id返回最新五筆資訊
			PageRequest pgb = PageRequest.of(pageNumber-1, 5,Sort.Direction.DESC,"id");
			Page<TestData> findAll = dao.findAll(pgb);
			return findAll;
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
