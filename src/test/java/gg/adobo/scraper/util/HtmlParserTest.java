package gg.adobo.scraper.util;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HtmlParserTest {
    @InjectMocks
    private HtmlParser htmlParser;

    private String TARGET_URL = "https://www.abodo.com/chicago-il";

    @Test
    public void getDocument_should_return_Document(){
        Document result = htmlParser.getDocument(TARGET_URL);

        assertNotNull(result);
    }

    @Test
    public void getElementsByTagFromElements_should_return_Elements(){
        Document document = htmlParser.getDocument(TARGET_URL);
        Elements elements = htmlParser.getElementsByTagFromDocument(document, "section");

        Elements result = htmlParser.getElementsByTagFromElements(elements.get(0), "section");

        assertNotNull(result);
    }

    @Test
    public void getElementsByTagFromDocument_should_return_Elements(){
        Document result = htmlParser.getDocument(TARGET_URL);

        Elements elements = htmlParser.getElementsByTagFromDocument(result, "section");

        assertNotNull(elements);
    }

    @Test
    public void getElementsByClassFromDocument_should_return_Elements(){
        Document result = htmlParser.getDocument(TARGET_URL);

        Elements elements = htmlParser.getElementsByClassFromDocument(result, "search-section search-section-rent-report-page");

        assertNotNull(elements);
    }
}
