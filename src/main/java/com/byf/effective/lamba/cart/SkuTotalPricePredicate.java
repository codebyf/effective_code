package com.byf.effective.lamba.cart;

/**
 * 对Sku的总价是否超过2000作为判断标准
 */
public class SkuTotalPricePredicate implements SkuPredicate {
    public boolean test(Sku sku) {
        return sku.getTotalPrice() > 2000.0;
    }
}
