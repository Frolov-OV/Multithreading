import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Main {
    private static final String link = "https://metanit.com/";

    public static void main(String[] args) {
        ForkJoinP forkJoinP = new ForkJoinP(link);
        ForkJoinPool fjPool = new ForkJoinPool();
        fileCreated(fjPool.invoke(forkJoinP));

    }
    public static void fileCreated (Set<String> listForWrite) {
        try {
            Files.write(Paths.get("src/main/resources/LinkList.txt"), listForWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
