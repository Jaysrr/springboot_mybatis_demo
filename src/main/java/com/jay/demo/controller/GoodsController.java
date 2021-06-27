package com.jay.demo.controller;

import com.jay.demo.pojo.Goods;
import com.jay.demo.request.GoodsRequest;
import com.jay.demo.service.GoodsService;
import com.jay.demo.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: springboot_mybatis_demo
 * @description:
 * @author: Jaysrr
 * @create: 2021-06-22 19:09
 **/

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
            , @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize
                           //pageSize在前端以及传到这代表页码
                           // 但传到dao层 mysql做处理时，会转化成，当前pageNumy页,所对应的这第一行数据的下标
                           //比如传入pageNum=3，代表第三页，pageSize=2,代表一页显示两个，那么传到dao层
                           //就是limit 4,2 (因为是从0开始，所以第3页的第1行数据下标是4)
            , @RequestParam String goodsName) {
        List<Goods> list = goodsService.findByGoodsName(goodsName, pageNum, pageSize);
        return !list.isEmpty() ? JsonResult.ok(list) : JsonResult.error();
    }

    @PostMapping("/add")
    public JsonResult addOrder(@RequestBody GoodsRequest goodsRequest) {
        return JsonResult.ok(goodsService.add(goodsRequest));
    }

    //
    @PutMapping("/update")
    public JsonResult updateOrder(@RequestParam Long id, @RequestBody GoodsRequest goodsRequest) {
        return JsonResult.ok(goodsService.update(id, goodsRequest));
    }

    @DeleteMapping("/delete")
    public JsonResult updateOrder(@RequestParam Long id){
        return JsonResult.ok(goodsService.delete(id));
    }


}
