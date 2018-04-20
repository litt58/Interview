package com.jzli.map;

import java.util.Random;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/4/3
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
public class TestBean {
    private String id;
    private String name;

    private Random random = new Random(10);

    public TestBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 提供进行hash碰撞测试
     * @return
     */
    @Override
    public int hashCode() {
        if (random.nextBoolean()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestBean testBean = (TestBean) o;

        if (!id.equals(testBean.id)) return false;
        return name.equals(testBean.name);

    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
