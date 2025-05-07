package com.mybank.bankofbihar.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonConstants {
	
	@Value("${key}")
	private String secret;
	
	@Value("${publicKeyPath}")
	private String publicKeyPath;
	
	@Value("${privateKeyPath}")
	private String privateKeyPath;

	public String getPublicKeyPath() {
		return publicKeyPath;
	}

	public String getPrivateKeyPath() {
		return privateKeyPath;
	}

	public String getSecret() {
		return secret;
	}

	
	

}
