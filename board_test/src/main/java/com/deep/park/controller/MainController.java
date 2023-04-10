package com.deep.park.controller;

import com.deep.park.test.Crypto;
import com.deep.park.test.MainCrypto;
import com.deep.park.test.Strong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    private Crypto crypto;

//    @Autowired
    public MainController(@MainCrypto Crypto crypto){
        this.crypto = crypto;
    }



    @GetMapping("/")
    @ResponseBody
    public String sample(){
        return crypto.encrypt("hello");
    }
}
