package com.yberdaliyev.spring;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yerlan on 01.03.2017.
 */
public class DataHandler {

    @Autowired
    private Downloader downloader;
    @Autowired
    private Uploader uploader;

    public DataHandler(){
    /*Жесткое создание полей*/
        downloader=new FileDownloader();
        uploader = new DBUploader();
    }
    public DataHandler(Downloader downloader, Uploader uploader) {
        this.downloader = downloader;
        this.uploader = uploader;
    }

    public void hadleData(String srcPath, String destPath){
        String content = this.downloader.download(srcPath);
        String handledContent = handle(content);
        this.uploader.upload(destPath, handledContent);
    }

    private String handle(String content) {
        return "";
    }


    public void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }



    public void setDownloader(Downloader downloader) {
        this.downloader = downloader;
    }
}
