package com.liu.forfunboot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "badGuy", url = "${bad.guy.url}", path = "api")
public interface BadGuyFeignClient {

    /**
     * 随机获取一句甜言蜜语
     *
     * @return
     */
    @GetMapping("SweetNothings")
    String getSweetNothings();

    /**
     * 获取 count 条甜言蜜语
     *
     * @param count 获取甜言蜜语条数
     * @return Json 格式的结果
     */
//    @GetMapping("SweetNothings/{count}/Serialization/Json")
//    ReturnResult<List<String>> getSweetNothingsJsonByCount(@PathVariable("count") Integer count);
}
