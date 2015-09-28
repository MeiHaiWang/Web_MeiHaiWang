package common.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class EncryptUtil {
	static
    {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }
	
    public static byte[] generateKey()throws NoSuchAlgorithmException
    {
        final SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed((long) Math.random()); 
        final KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256, random);
        return keyGen.generateKey().getEncoded();
    }
    
    public static common.model.EncryptedData encrypt(byte[] key, byte[] input) throws NoSuchAlgorithmException,NoSuchPaddingException,InvalidKeyException,IllegalBlockSizeException,BadPaddingException
    {
        final SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed((long) Math.random()); 
        final SecretKey secretKey = new SecretKeySpec(key, "AES");
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
        common.model.EncryptedData result = new common.model.EncryptedData();
        result.iv = cipher.getIV();
        result.data = cipher.doFinal(input);
		return result;
    }
 
    public static byte[] decrypt(byte[] key, byte[] iv, byte[] input) throws NoSuchAlgorithmException,NoSuchPaddingException,InvalidKeyException,InvalidAlgorithmParameterException,IllegalBlockSizeException,BadPaddingException
    {
        final SecretKey secretKey = new SecretKeySpec(key,"AES");
        Cipher cipher = null;
        int BLOCK_SIZE = 0;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding","BC");
			BLOCK_SIZE = cipher.getBlockSize();
			cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		return cipher.doFinal(input,BLOCK_SIZE,input.length-BLOCK_SIZE);
    }
    
    public static String getHashValue(String targetValue){
    	MessageDigest md = null;
    	StringBuffer buffer = new StringBuffer();
    	try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	    md.update(targetValue.getBytes());
	    byte[] valueArray = md.digest();
	    // ハッシュ値の配列をループ
	    for(int i = 0; i < valueArray.length; i++){
	        // 値の符号を反転させ、16進数に変換
	        String tmpStr = Integer.toHexString(valueArray[i] & 0xff);
	        if(tmpStr.length() == 1){
	            // 値が一桁だった場合、先頭に0を追加し、バッファに追加
	            buffer.append('0').append(tmpStr);
	        } else {
	            // その他の場合、バッファに追加
	            buffer.append(tmpStr);
	        }
	    }
	    // 完了したハッシュ計算値を返却
	    return buffer.toString();
    }
}
