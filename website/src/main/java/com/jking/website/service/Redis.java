package com.jking.website.service;



public interface Redis {
    void set(String key, String value);
    String get(String key);
    void setcode(String key,String value);
    String getcode(String key);
}
