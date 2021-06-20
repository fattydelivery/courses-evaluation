package io.github.fattydelivery.courseevaluation.utils;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-06-20-12:15
 **/

import io.github.fattydelivery.courseevaluation.properties.ScriptProperties;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class CmdExcutor {
    private static final Logger logger = LoggerFactory.getLogger(CmdExcutor.class);

    @Autowired
    private ScriptProperties scriptProperties;

    public void start() throws IOException {
        System.out.println(scriptProperties.getImportShell());
        executeShell(scriptProperties.getImportShell());
    }

    public void first() throws IOException {
        executeShell(scriptProperties.getFirstStepShell());
    }

    public void second() throws IOException {
        executeShell(scriptProperties.getSecondStepShell());
    }

    public void end() throws IOException {
        executeShell(scriptProperties.getExportShell());
    }

    private void executeShell(String fileName) throws IOException {

        String fileFullName = scriptProperties.getFullName(fileName);
        File file = new File(fileFullName);
        if(!file.exists()) {
            logger.error("file {} not existed!", fileFullName);
            return;
        }

        ProcessBuilder processBuilder = new ProcessBuilder(fileFullName);
        processBuilder.directory(new File(scriptProperties.getDirectory()));

        Process process = processBuilder.start();

//        String input;
//        BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//        while ((input = stdInput.readLine()) != null) {
//            logger.info(input);
//        }
//        while ((input = stdError.readLine()) != null) {
//            logger.error(input);
//        }

        int runningStatus = 0;
        try {
            runningStatus = process.waitFor();
        } catch (InterruptedException e) {
            logger.error("shell", e);
        }

        if(runningStatus != 0) {
            logger.error("failed.");
        }else {
            logger.info("success.");
        }
    }
}
