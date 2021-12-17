package com.liu.forfunboot.protobufTest;

import com.google.protobuf.InvalidProtocolBufferException;
import com.liu.forfunboot.protobuf.DataInfo;
import org.junit.jupiter.api.Test;

public class ProtobufTest {

    @Test
    public void test1() throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("liujingxuan")
                .setAge(25)
                .setAddress("china")
                .build();
        byte[] bytes = student.toByteArray();
        System.out.println(bytes);

        DataInfo.Student student1 = DataInfo.Student.parseFrom(bytes);
        System.out.println(student1.getName());
        System.out.println(student1.getAge());
        System.out.println(student1.getAddress());
    }

}
