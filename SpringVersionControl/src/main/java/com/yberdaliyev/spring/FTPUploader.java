package com.yberdaliyev.spring;

/**
 * Created by Yerlan on 01.03.2017.
 */
public class FTPUploader implements Uploader {
    public boolean upload(String path, Object content) {
        System.out.println("uploaded to FTP");
        return true;
    }
}
