package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付curd")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法，json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功加入记录，返回值：" + i);
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<String> deletePay(@PathVariable("id") Integer id){
        int i = payService.delete(id);
        return ResultData.success("成功删除记录，返回值：" + i);


    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDto){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDto,pay);

        int i = payService.update(pay);

        return ResultData.success("成功修改记录，返回值：" + i);
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        if(id == -4) throw new RuntimeException("id不能为负数");
//        try {
//            TimeUnit.SECONDS.sleep(62);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return ResultData.success(payService.getById(id));
    }

    @GetMapping(value = "/pay/all")
    @Operation(summary = "查询所有流水",description = "查询所有支付流水方法")
    public ResultData<List<Pay>> getAll(){
        return ResultData.success(payService.getAll());
    }

    @GetMapping(value = "/pay/error")
    public ResultData<Integer> getPayError(){
        Integer i = Integer.valueOf(200);
        try{
            System.out.println("--------come here--------");
            int data = 10 / i;
        }catch (Exception e){
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return ResultData.success(i);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    private String getInfoByConsul(@Value("${atguigu.info}") String atguiguInfo){
        return "atguiguInfo:"+atguiguInfo+"\t"+"port:"+port;
    }

}
