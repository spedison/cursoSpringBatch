package br.com.spedison.primeirobach.commom;

import org.springframework.stereotype.Component;

public class DetectOs {
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String OS_Original = System.getProperty("os.name").toLowerCase();

    public DetectOs() {
    }

    public boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public boolean isUnix() {
        return (OS.indexOf("nix") >= 0
                || OS.indexOf("nux") >= 0
                || OS.indexOf("aix") > 0);
    }

    public boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

    public String getName() {
        return OS_Original;
    }
}
