package com.example.jpastudy.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

/**
 * description
 *
 * @author : jkkim
 */
/*@SpringBootTest*/
public class JwtTest {

    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();
        System.out.println(jws);
        String jwt = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getSubject();
        System.out.println(jwt);
    }

    /*@Test
    public void tokenGenerateTest() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jwtStr = Jwts.builder().setSubject("Joe").signWith(key).compact();
        System.out.println(jwtStr);

        //Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody().getSubject().equals("Joe");
    }*/
}
