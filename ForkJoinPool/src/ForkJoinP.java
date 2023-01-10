import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;


@Getter
@Setter
public class ForkJoinP extends RecursiveTask<Set<String>> {

    private String link;
    private static HashSet<String> uniqueURL = new HashSet<>();
    private int tabs;

    public ForkJoinP (String link) {
        this.link = link;
    }

    @Override
    protected Set<String> compute() {
        List<ForkJoinP> forkJoinPList = new ArrayList<>();
        Set<String> linksSet = new LinkedHashSet<>();
        try {
            Thread.sleep(150);
            Document document = Jsoup.connect(link).get();
            Elements siteElement = document.select("a");
            siteElement.stream()
                    .map((link) -> link.attr("abs:href"))
                    .forEachOrdered((subUrl) -> {
                        boolean add = uniqueURL.add(subUrl);
                        if (add && subUrl.contains(link) && subUrl.endsWith("/")) {
                            StringBuilder sb = new StringBuilder();
                            tabs = StringUtils.countMatches(subUrl, "/");
                            sb.append(getTab(tabs - 3)).append(subUrl);
                            System.out.println(sb);
                            linksSet.add(sb.toString());
                            ForkJoinP forkParser = new ForkJoinP(subUrl);
                            forkJoinPList.add(forkParser);
                            forkParser.fork();
                        }
                    });
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (ForkJoinP task : forkJoinPList) {
            linksSet.addAll(task.join());
        }
        return linksSet;
    }
    private String getTab(int count) {
        return "\t".repeat(Math.max(0, count));
    }
}