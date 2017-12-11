package org.unico.assign.gcd.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.unico.assign.gcd.dao.UserDao;

/**
 * 
 * @author Sarin
 *
 */

public class AuthenticationUtil {

	public static final String USERNAME = "Username";
	public static final String PASSWORD = "Password";
	
	public static final String ALGORITHM = "RSA";
	public static final String PRIVATE_KEY = "private.key";

	public static void authenticate(UserDao userDao, Map<String, List<String>> httpHeaders)
			throws GeneralSecurityException, FileNotFoundException, IOException, ClassNotFoundException {

		String userName = null;
		String password = null;

		List<String> users = (List<String>) httpHeaders.get(USERNAME);
		List<String> passwords = (List<String>) httpHeaders.get(PASSWORD);
		
		if (null != users && ! users.isEmpty()) {
			userName = users.get(0).toString();
		}

		if (null != passwords && ! passwords.isEmpty()) {
			password = passwords.get(0).toString();
			Decoder decoder = java.util.Base64.getDecoder();
			byte[] decodedByte = decoder.decode(password);

			ObjectInputStream objectInputStream = new ObjectInputStream( 
					(AuthenticationUtil.class.getResourceAsStream(PRIVATE_KEY)));
			PrivateKey privateKey = (PrivateKey) objectInputStream.readObject();
			String decryptedPasswordString = decryptString(decodedByte, privateKey);
			if (userDao.getUser(userName, decryptedPasswordString) == null) {
				throw new GeneralSecurityException("Username or password");
			}
		} else {
			throw new GeneralSecurityException("Username or password is missing");
		}

	}

	/**
	 * This method decrypt text using private key
	 * 
	 * @param text
	 * @param key
	 * @return String
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private static String decryptString(byte[] text, PrivateKey key) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] dectyptedString = null;

		final Cipher cipher = Cipher.getInstance(ALGORITHM);

		cipher.init(Cipher.DECRYPT_MODE, key);
		dectyptedString = cipher.doFinal(text);
		return new String(dectyptedString);
	}

}
