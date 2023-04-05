package com.deep.park.test;

public class Strong implements Crypto{
    @Override
    public String encrypt(String str) {
        return "strongEncrypt";
    }

    @Override
    public String decrypt(String str) {
        return "strongDecrypt";
    }
}
