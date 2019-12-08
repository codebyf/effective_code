package com.byf.effective.stream;

import com.alibaba.fastjson.JSON;
import com.byf.effective.lamba.cart.CartService;
import com.byf.effective.lamba.cart.Sku;
import com.byf.effective.lamba.cart.SkuCategoryEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


/**
 * 对比：原始集合操作与Stream集合操作
 */
public class StreamVs {
    /**
     * 1 想看购物车中都有什么商品
     * 2 图书类商品都给买
     * 3 其余的商品买两件最贵的
     * 4 只需要两件商品的名称和总价
     */

    /**
     * 以最原始的操作实现需求
     */
    @Test
    public void oldCartHandle(){
        List<Sku> cartSkuList = CartService.getCartSkuList();

        for (Sku sku:cartSkuList){
            System.out.println(JSON.toJSONString(sku,true));
        }

        List<Sku> notBooksSkuList = new ArrayList<>();
        for(Sku sku:cartSkuList){
            if (!SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory())){
                notBooksSkuList.add(sku);
            }
        }

        notBooksSkuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku o1, Sku o2) {
                if(o1.getTotalPrice() > o2.getTotalPrice()){
                    return -1;
                } else if(o1.getTotalPrice() < o2.getTotalPrice()){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        List<Sku> top2SkuList = new ArrayList<>();
        for(int i=0;i<2;i++){
            top2SkuList.add(notBooksSkuList.get(i));
        }

        Double money = 0.0;
        for(Sku sku:top2SkuList){
            money += sku.getTotalPrice();
        }

        List<String> resultSkuNameList = new ArrayList<>();
        for(Sku sku:top2SkuList){
            resultSkuNameList.add(sku.getSkuName());
        }

        System.out.println(JSON.toJSONString(resultSkuNameList, true));
        System.out.println("商品总价：" + money);
    }

    /**
     * 以Stream流方式实现需求
     */
    @Test
    public void newCartHandle(){
        AtomicReference<Double> money = new AtomicReference<>(Double.valueOf(0.0));

        List<String> resultSkuNameList = CartService.getCartSkuList()
                .stream()
                // 打印商品信息
                .peek(sku -> System.out.println(JSON.toJSONString(sku,true)))
                // 过滤图书商品
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                // 排序，默认从大到小
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                // TOP 2
                .limit(2)
                // 累加商品总金额
                .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                // 获取商品名称
                .map(sku -> sku.getSkuName())
                // 收集结果
                .collect(Collectors.toList());

        System.out.println(JSON.toJSONString(resultSkuNameList, true));
        System.out.println("商品总价：" + money);

    }
}
