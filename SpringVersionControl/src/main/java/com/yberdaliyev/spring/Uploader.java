package com.yberdaliyev.spring;

/**
 * Created by Yerlan on 01.03.2017.
 */
public interface Uploader {

    /**
     *
     * @param path
     * @param content
     * @return
     */
    public boolean upload(String path, Object content);
}