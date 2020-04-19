package org.example.mianshi;

import lombok.Getter;

public enum NameEnum {

    STNO1(1,"张"),
    STNO2(2,"王"),
    STNO3(3,"李"),
    STNO4(4,"赵"),
    STNO5(5,"刘"),
    STNO6(6,"左");

    @Getter
    private int key;
    @Getter
    private String name;

    NameEnum(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public static NameEnum getName(int key){
        NameEnum[] nameEnums = NameEnum.values();
        for (NameEnum nameEnum:nameEnums) {
            if (nameEnum.getKey() == key) {
                return nameEnum;
            }
        }
        return null;
    }



}
