package com.devsuperior.msapigateway.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ReadPublicKey {
	
	public static PublicKey getKeyFromResource(String pemName) throws Exception {
		String strKey = getKey(pemName);
		PublicKey key = strPemToPublicKey(strKey);
		return key;
	}
	
	public static PublicKey getKeyFromConfig(String pemKey) throws Exception {
		PublicKey key = strPemToPublicKey(pemKey);
		return key;
	}
	
	private static PublicKey strPemToPublicKey(String strKey) throws Exception {
		byte[] encoded = Base64.decodeBase64(strKey);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}
	
	private static String getKey(String pemName) throws IOException {
		Resource resource = new ClassPathResource(pemName);
		File file = resource.getFile();

		// Read key from file
		String strKeyPEM = "";
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			if (!line.contains("--")) {
				strKeyPEM += line + "\n";
			}
		}
		br.close();
		return strKeyPEM;
	}

}