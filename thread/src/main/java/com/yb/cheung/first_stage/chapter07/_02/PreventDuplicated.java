package com.yb.cheung.first_stage.chapter07._02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class PreventDuplicated {

    private final static String LOCK_PATH = "d:/";

    private final static String LOCK_FILE = ".lock";

    private final static String PERMISSION = "rw-------";

    public static void main(String[] args) throws IOException{

        // 0.程序启动创建一个
        Files.write(getLockFile(),PERMISSION.getBytes());

        // 1.注入Hook线程，在程序退出时删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("The program received kill SIGNAL.");
                getLockFile().toFile().delete();
            }
        });

        // 2.检查是否存在 .lock文件
        checkRunning();

        // 3.简单模拟当前程序运行
        for (;;){
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(1/0);
        }
    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        System.out.println(path.toFile().exists());
        if (path.toFile().exists()){
            throw  new RuntimeException(" The program already running.");
        }
    }

    private static Path getLockFile(){
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }

}
