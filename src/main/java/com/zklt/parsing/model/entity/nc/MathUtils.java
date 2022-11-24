package com.zklt.parsing.model.entity.nc;

public class MathUtils {

    public static int middle(int a, int b, int c) {
        return a > b ? (b > c ? b : (a > c ? c : a)) : (a > c ? a : (b > c ? c : b));
    }
}
