package io.github.fattydelivery.courseevaluation.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @program:course-evaluation
 * @description
 * @author: Jayce(Bingjie Yan)
 * @create: 2021-06-20-12:23
 **/
@Component
@ConfigurationProperties(prefix = "shell")
public class ScriptProperties {
    private String directory;
    private String importShell;
    private String firstStepShell;
    private String secondStepShell;
    private String exportShell;

    /* getter & setter */

    public String getFullName(String fileName) {
        return directory + File.separator + fileName;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getImportShell() {
        return importShell;
    }

    public void setImportShell(String importShell) {
        this.importShell = importShell;
    }

    public String getFirstStepShell() {
        return firstStepShell;
    }

    public void setFirstStepShell(String firstStepShell) {
        this.firstStepShell = firstStepShell;
    }

    public String getSecondStepShell() {
        return secondStepShell;
    }

    public void setSecondStepShell(String secondStepShell) {
        this.secondStepShell = secondStepShell;
    }

    public String getExportShell() {
        return exportShell;
    }

    public void setExportShell(String exportShell) {
        this.exportShell = exportShell;
    }
}
