package com.nathansdev.newsfeed.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss", strict = false)
public class ArticleResponse {

    @Element(name = "channel")
    public Channel channel;
}
