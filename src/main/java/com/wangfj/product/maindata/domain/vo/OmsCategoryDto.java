package com.wangfj.product.maindata.domain.vo;

/**
 * Created by wangxuan on 2016-06-07 0007.
 */
public class OmsCategoryDto {

    private String name;//分类名称

    private String categoryCode;//分类编码

    private String level;//分类级别

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "OmsCategoryDto{" +
                "name='" + name + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
