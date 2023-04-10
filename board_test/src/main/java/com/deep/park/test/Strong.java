package com.deep.park.test;

public class Strong implements Crypto{
    public String test;

    public void setTest(String str){
        this.test = str;
    }
    public String getTest(){
        return this.test;
    }
    @Override
    public String encrypt(String str) {
        return "strongEncrypt";
    }

    @Override
    public String decrypt(String str) {
        return "strongDecrypt";
    }
}
