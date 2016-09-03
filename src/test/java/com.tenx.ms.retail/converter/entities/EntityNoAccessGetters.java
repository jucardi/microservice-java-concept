package com.tenx.ms.retail.converter.entities;


import com.tenx.ms.retail.common.util.DenyConverterAccess;

public class EntityNoAccessGetters {
    private int     someField1;
    private long    someField2;
    private String  someField3;
    private boolean someFlag;

    public EntityNoAccessGetters() {

    }

    public EntityNoAccessGetters(int someField1, long someField2, String someField3, boolean someFlag) {
        this.someField1 = someField1;
        this.someField2 = someField2;
        this.someField3 = someField3;
        this.someFlag   = someFlag;
    }

    @DenyConverterAccess
    public String getSomeField3() { return this.someField3; }
    public void setSomeField3(String someField33) { this.someField3 = someField33; }

    @DenyConverterAccess
    public long getSomeField2() { return this.someField2; }
    public void setSomeField2(long someField2) { this.someField2 = someField2; }

    public int getSomeField1() { return this.someField1; }
    public void setSomeField1(int someField1) { this.someField1 = someField1; }

    public boolean isSomeFlag() { return this.someFlag; }
    public void setSomeFlag(boolean someFlag) { this.someFlag = someFlag; }
}
