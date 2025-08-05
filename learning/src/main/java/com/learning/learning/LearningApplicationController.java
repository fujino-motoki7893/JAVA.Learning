package com.learning.learning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearningApplicationController {

	@GetMapping("/")  // ルートへこのメソッドをマップする
    public String test(@RequestParam("id") String id) { // 引数 id を貰ってみる。
        return "Hello World id=[" + id + "]";
    }
}
