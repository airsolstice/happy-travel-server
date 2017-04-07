package com.solstice.bean;


public enum GoodsCatalog {
	/**
	 * 栏目一
	 */
	BEAUTY(1),
	/**
	 * 栏目二
	 */
	SKINCARE(2),
	/**
	 * 栏目三
	 */
	FASHION(3),
	/**
	 * 栏目四
	 */
	COSTUME(4),
	/**
	 * 栏目五
	 */
	HOUSEHOLD(5);
	
    private int value = 0;  
	  
    private GoodsCatalog(int value) {   
        this.value = value;  
    }  
	
    public static GoodsCatalog valueOf(int value) {    //    手写的从int到enum的转换函数  
        switch (value) {  
        case 1:  
            return BEAUTY;  
        case 2:  
            return SKINCARE;  
        case 3:  
            return FASHION;  
        case 4:  
            return COSTUME;  
        case 5:  
            return HOUSEHOLD;  
        default:  
            return null;  
        }  
    }  
  
    public int value() {  
        return this.value;  
    }  
}
