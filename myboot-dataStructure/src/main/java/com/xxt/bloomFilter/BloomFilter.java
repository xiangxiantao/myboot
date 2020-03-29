package com.xxt.bloomFilter;

/**
 * 自己实现一个布隆过滤器
 * 布隆过滤器只能哦按段一个值一定不存在或者可能存在
 * 他的错误率与两个因素有关：
 * 1.hash（）的个数，目前只用一个
 * 2.byte数组的长度，长度越长，出错的机率就越小，内存开销也越大
 */
public class BloomFilter {

    private byte[] bloomArray;

    public BloomFilter(int length) {
        this.bloomArray = new byte[length];
    }

    /**
     * 判断是否存在
     *
     * @param value
     * @return
     */
    public boolean exesit(String value) {
        int index = getIndex(value);
        return bloomArray[index] == 1 ? true : false;
    }

    /**
     * 获取下标
     *
     * @param value
     * @return
     */
    private int getIndex(String value) {
        int hashCode = value.hashCode();
        int index = hashCode % bloomArray.length;

        return index;
    }

    /**
     * 保存
     *
     * @param value
     */
    public void save(String value) {
        int index = getIndex(value);
        bloomArray[index] = 1;
    }

    public static void main(String[] args) {
        BloomFilter bloomFilter=new BloomFilter(100);
        bloomFilter.save("8944");
        bloomFilter.save("7777");
        bloomFilter.save("2222");
        bloomFilter.save("5678");
        System.out.println(bloomFilter.exesit("7777"));
        System.out.println(bloomFilter.exesit("7779"));
    }
}
