package com.liu.forfunboot.mmsegtest;

import com.chenlb.mmseg4j.analysis.ComplexAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MmsegTest {
    String txt = "";

//    public static void displayTokens(Analyzer analyzer,String text) throws IOException {
//        TokenStream tokenStream = analyzer.tokenStream("text", text);
//        displayTokens(tokenStream);
//    }

    public static void displayTokens(TokenStream tokenStream) throws IOException {
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        PositionIncrementAttribute positionIncrementAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);

        tokenStream.reset();
        int position = 0;
        while (tokenStream.incrementToken()) {
            int increment = positionIncrementAttribute.getPositionIncrement();
            if(increment > 0) {
                position = position + increment;
                System.out.print(position + ":");
            }
            int startOffset = offsetAttribute.startOffset();
            int endOffset = offsetAttribute.endOffset();
            String term = charTermAttribute.toString();
            System.out.println("[" + term + "]" + ":(" + startOffset + "-->" + endOffset + "):" + typeAttribute.type());
        }
    }

    @Test
//    @Ignore
    public void testComplex() throws IOException {
        txt = "1999年12345日报道了一条新闻,2000年中法国足球比赛";
        /*txt = "第一卷 云天落日圆 第一节 偷欢不成倒大霉";
        txt = "中国人民银行";
        txt = "我们";
        txt = "工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作";*/
        //ComplexSeg.setShowChunk(true);
        Analyzer analyzer = new ComplexAnalyzer();
//        displayTokens(analyzer,txt);
    }







}
