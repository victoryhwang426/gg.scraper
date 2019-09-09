package gg.adobo.scraper.util;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HtmlParser {
    public Document getDocument(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url)
                    .ignoreHttpErrors(true)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    public Elements getElementsByTagFromElements(Element Element, String name){
        return Element.getElementsByTag(name);
    }

    public Elements getElementsByTagFromDocument(Document document, String name){
        return document.getElementsByTag(name);
    }

    public Elements getElementsByClassFromDocument(Document document, String name){
        return document.getElementsByClass(name);
    }
}
