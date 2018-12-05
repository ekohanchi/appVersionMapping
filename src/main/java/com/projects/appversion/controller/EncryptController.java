package com.projects.appversion.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.projects.appversion.ApplicationConfig;

@Controller
public class EncryptController {
    @Autowired
    private ApplicationConfig applicationConfig;

    private String originalText = "";
    private String decText = "";
    private String encText = "";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/encryption")
    public String index(Model model) {

        model.addAttribute("originalText", originalText);
        model.addAttribute("decText", decText);
        model.addAttribute("encText", encText);

        return "encryption";
    }

    @RequestMapping(value = "/encryptText", method = RequestMethod.POST)
    public String encryptText(String orgText) {
        String serverHostName = "";
        try {
            serverHostName = InetAddress.getLocalHost().getHostName();
            log.info("SERVER HOST NAME: " + serverHostName);
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }

        if (serverHostName.contains(applicationConfig.getServerHostName())) {
            log.info("Getting the textEncryptor...");
            TextEncryptor textEncryptor =
                    Encryptors.text(applicationConfig.getEncryptPassword(), applicationConfig.getEncryptSalt());
            originalText = orgText;
            encText = textEncryptor.encrypt(orgText);
            decText = textEncryptor.decrypt(encText);
        } else {
            originalText = orgText;
            encText = "NOT AUTHORIZED TO ENCRYPT";
            decText = "NOT AUTHORIZED TO DECRYPT";
        }

        return "redirect:/encryption";
    }
}
