package com.yberdaliyev.spring;

import org.springframework.stereotype.Component;

/**
 * Created by Yerlan on 01.03.2017.
 */
@Component
public class DBUploader implements Uploader {
    public boolean upload(String path, Object content) {
        System.out.println("uploaded to DB");
        return true;
    }
}
