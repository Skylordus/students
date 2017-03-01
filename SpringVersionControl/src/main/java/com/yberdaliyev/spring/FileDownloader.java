package com.yberdaliyev.spring;

import org.springframework.stereotype.Component;

/**
 * Created by Yerlan on 01.03.2017.
 */
@Component
public class FileDownloader implements Downloader {
    public String download(String path) {
        return "string from file";
    }
}
