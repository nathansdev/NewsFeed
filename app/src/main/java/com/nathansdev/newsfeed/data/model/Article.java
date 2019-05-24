package com.nathansdev.newsfeed.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Article {

    @Element(name = "title")
    public String title;

    @Element(name = "description")
    public String description;

    @Element(name = "link")
    public String link;

    @Element(name = "pubDate")
    public String pubDate;

    @Element(name = "enclosure")
    public Enclosure enclosure;
}
