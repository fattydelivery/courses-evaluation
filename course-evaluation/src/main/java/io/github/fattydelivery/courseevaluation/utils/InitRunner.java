package io.github.fattydelivery.courseevaluation.utils;

import io.github.fattydelivery.courseevaluation.properties.ScriptProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-06-20-12:34
 **/
@Component
public class InitRunner implements CommandLineRunner {

    @Autowired
    private ScriptProperties scriptProperties;

    @Autowired
    ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        generateFile(scriptProperties.getImportShell());
        generateFile(scriptProperties.getFirstStepShell());
        generateFile(scriptProperties.getSecondStepShell());
        generateFile(scriptProperties.getExportShell());
    }

    private void generateFile(String fileName) throws IOException {

        String fileFullName = scriptProperties.getFullName(fileName);
        File file = new File(fileFullName);
        if(file.exists()) {
            return;
        }

        // 如果文件已存在，FileWriter 会先删除再新建
        FileWriter fileWriter = new FileWriter(fileFullName);

        Resource resource = resourceLoader.getResource("classpath:" + fileName);
        InputStream inputStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String data;
        while ((data = bufferedReader.readLine()) != null) {
            fileWriter.write(data + "\n");
        }

        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();

        fileWriter.close();

        // 设置权限，否则会报 Permission denied
        file.setReadable(true);
        file.setWritable(true);
        file.setExecutable(true);
    }
}