package com.deep.park.test;

public class Sha512 implements Crypto{
    @Override
    public String encrypt(String str) {
        return "encrypt";
    }

    @Override
    public String decrypt(String str) {
        return "decrypt";
    }
}
