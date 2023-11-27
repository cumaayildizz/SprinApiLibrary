package com.cuma.yildiz.LibrarySpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarySpringApplication {
    //netstat -an | find "8081"
    public static void main(String[] args) {
        SpringApplication.run(LibrarySpringApplication.class, args);
    }

}

/*
{
 "status": false,
 "message": "gereken mesaj",
 "code": "gereken kod(400,200,404 vb),
    {
       "deger bos olamaz",
       "deger veri gutubuna uygun degil",
       "vs vs":
    }
}
*/