package com.nathansdev.newsfeed.data.model;


import org.simpleframework.xml.Attribute;

public class Enclosure {

    @Attribute(name = "url")
    public String url;

    @Attribute(name = "length")
    public long length;

    @Attribute(name = "type")
    public String type;
}