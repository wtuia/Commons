package java_lang.enums.enum_map;

public class Plant {

    /**
     * 内部枚举，按类型分(一年生，两年，多年)
     */
    enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return name;
    }

}
