package java_lang.enumDemo;

public enum CityEnum {
    GUIYANG("贵阳",1),
    LIUPANSHUI("六盘水",2);

    private String desc;
    private int cityNum;

    CityEnum(String desc, int cityNum) {
        this.desc = desc;
        this.cityNum = cityNum;
    }

    public String getDesc() {
        return desc;
    }
    public int getCityNum(){
        return cityNum;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static void main(String[] args) {
        for (CityEnum city : CityEnum.values() ) {
            System.out.printf("ordinal:%d, name:%s, desc:%s, toString:%s\n",
                    city.ordinal(), city.name(), city.getDesc(), city.toString());
        }
    }
}
