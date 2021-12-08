package com.liu.forfunboot.util;

import com.csvreader.CsvWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

@Slf4j
public class CsvExportUtil {
    public static <T> byte[] writeCSV(Collection<T> dataset, String[] csvHeaders) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            // 定义路径，分隔符，编码
            CsvWriter csvWriter = new CsvWriter(byteArrayOutputStream, ',', Charset.forName("UTF-8"));
            //加BOM标识，防止Excel打开中文乱码
            byteArrayOutputStream.write(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF });
            // 写表头
            csvWriter.writeRecord(csvHeaders);
//            csvWriter.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
            // 写内容
            //遍历集合
            Iterator<T> it = dataset.iterator();
            while (it.hasNext()) {
                T t = (T) it.next();
                if (t instanceof Long){
                    String[] csvContent = new String[1];
                    csvContent[0] = t.toString();
                    csvWriter.writeRecord(csvContent);
                    continue;
                }

                //获取类属性
                Field[] fields = t.getClass().getDeclaredFields();
                String[] csvContent = new String[fields.length];
                for (short i = 0; i < fields.length; i++) {


                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    try {
                        Class tCls = t.getClass();
                        Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                        Object value = getMethod.invoke(t, new Object[]{});
                        if (value == null) {
                            continue;
                        }
                        //取值并赋给数组
                        String textvalue = value.toString();
                        csvContent[i] = textvalue;
                        //System.out.println("fieldname="+fieldName+"||getMethodname="+getMethodName+"||textvalue="+textvalue);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                //迭代插入记录
                csvWriter.writeRecord(csvContent);
            }
            csvWriter.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            log.error("writeCSV error", e);
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                log.error("writeCSV error", e);
            }
        }
        return null;
    }
}
