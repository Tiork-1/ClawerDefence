package com.example.clawerdefence.utils;

import java.io.IOException;
import java.io.OutputStream;

public interface IVerifyCodeGen {
    /**
     * 生成验证码并返回code，将图片写的os中
     */
    String generate(int width, int height, OutputStream os) throws IOException;

    /**
     * 生成验证码对象
     */
    VerifyCode generate(int width, int height) throws IOException;
}
