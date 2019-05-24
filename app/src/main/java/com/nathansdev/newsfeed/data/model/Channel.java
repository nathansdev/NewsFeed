package com.nathansdev.newsfeed.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class Channel {

    @Element(name = "docs")
    public String docs;

    @Element(name = "lastBuildDate")
    public String lastBuildDate;

    @Element(name = "link")
    public String link;

    @Element(name = "title")
    public String title;

    @Element(name = "generator")
    public String generator;

    @Element(name = "pubDate")
    public String pubDate;

    @ElementList(inline = true, required = false)
    public List<Article> articles;
}
