package com.mulan.mulan_auto.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class SecurityConstants {
	public static final String SIGN_UP_URLS = "/api/v1/users/**";
	public static final String SECRET = "SecretKeyToGenJWT";
	public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	public static final String TOKEN_PREFIX = "Bearer "; // space is necessary after Bearer
	public static final String HEADER_STRING = "Authorization";
	public static final long EXPIRATION_TIME = 3_600_000; // 1 hour in milliseconds - 1000 = 1s
}