/*
package com.example.controller;

import com.example.entity.User1;
import com.example.service.GitHubLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

*/
/**
 * Created by zhangrui on 2017/11/22.
 *//*

@Component
public class AppRunner implements CommandLineRunner{
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final GitHubLookupService gitHubLookupService;

    public AppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }
    @Override
    public void run(String... strings) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();
        String s = "+++++++++++++++++++++++++++++++++++";
        // Kick of multiple, asynchronous lookups
        Future<User1> page1 = gitHubLookupService.findUser("PivotalSoftware");
        Future<User1> page2 = gitHubLookupService.findUser("CloudFoundry");
        Future<User1> page3 = gitHubLookupService.findUser("Spring-Projects");

        // Wait until they are all done
        while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
            Thread.sleep(10); //10-millisecond pause between each check
        }

        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
        logger.info("--> " + page1.get());
        logger.info("--> " + page2.get());
        logger.info("--> " + page3.get());
    }

}
*/
