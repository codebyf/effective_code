package com.byf.effective.lamba.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

public class VersionTest {
    @Test
    public void filterElectronicsSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterElectronicsSkus(cartSkuList);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void filterSkusByCategory(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkusByCategory(cartSkuList, SkuCategoryEnum.BOOKS);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void filterSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();

        // 根据商品总价过滤超过2000元的商品列表
        List<Sku> result = CartService.filterSkus(
                cartSkuList, null,
                2000.00, false);

        System.out.println(JSON.toJSONString(
                result, true));
    }

    @Test
    public void testFilterSkus(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(
                cartSkuList, new SkuBooksCategoryPredicate());

        System.out.println(JSON.toJSONString(
                result, true));
    }

    @Test
    public void testFilterSkus1(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(
                cartSkuList, new SkuPredicate() {
                    public boolean test(Sku sku) {
                        return sku.getTotalPrice() > 2000;
                    }
                });

        System.out.println(JSON.toJSONString(
                result, true));
    }

    @Test
    public void testFilterSkus2(){
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<Sku> result = CartService.filterSkus(
                cartSkuList, (Sku sku) -> 2000 < sku.getTotalPrice());

        System.out.println(JSON.toJSONString(
                result, true));
    }
}
