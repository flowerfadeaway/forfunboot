package com.liu.forfunboot.util;

import com.liu.forfunboot.config.DelimiterToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author mawf
 */
public class CsvParser<T> {

    // csv分隔符
    private String delimiter;
    private DelimiterToStringStyle delimiterToStringStyle;

    public CsvParser(String delimiter) {
        this.delimiter = delimiter;
        this.delimiterToStringStyle = new DelimiterToStringStyle(this.delimiter);
    }

    // 将对象转为csv字符串
    public String parse(T t) {
        return ReflectionToStringBuilder.toString(t, delimiterToStringStyle);
    }

}
