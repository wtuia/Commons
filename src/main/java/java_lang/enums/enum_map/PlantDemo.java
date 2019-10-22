package java_lang.enums.enum_map;

import org.junit.Test;

import java.util.*;

public class PlantDemo {

    /**
     * 使用数组的优点在于，与List或set相比，更快
     * <br/>
     * Arrays.fill 替代 for ,
     * 内部使用for循环实现，在传入下标的重构方法中，添加下下标检查
     * @see java.util.Arrays#fill(long[] a, int fromIndex, int toIndex, long val)
     */
    @Test
    public void arrayPlant() {
        // 按植物类型分为三个不同的set
        /* 不允许创建泛型数组，因为数组的协变的，而泛型在编译时擦除，
         * 在编译前，声明set<String>[] 与 set[] 等同，如此所有类型的泛型数组与基础数组等同，与泛型特性相冲突
         */
        @SuppressWarnings("unchecked")
        Set<Plant>[] plantsByLiftCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

        Arrays.fill(plantsByLiftCycle, new HashSet<>());

        /*
        for (int i = 0; i < plantsByLiftCycle.length; i++) {
            plantsByLiftCycle[i] = new HashSet<>();
        }
        */

        // 实例化一个花园，并添加植物
        List<Plant> garden = new ArrayList<>();
        Plant plant = new Plant("一年生植物", Plant.LifeCycle.ANNUAL);
        garden.add(plant);

        // 将对应类型的植物存入对应的set
        for (Plant p : garden) {
            plantsByLiftCycle[p.lifeCycle.ordinal()].add(p);
        }
    }

    /**
     * arrayPlant 的 enumMap实现
     * 速度与数组差不多，且去除了类型检查的隐患
     */
    public void enumMapPlant() {
        // 采用Class实例化，提供运行时的泛型信息
        Map<Plant.LifeCycle, Set<Plant>> plantsByLiftCycle =
                new EnumMap<Plant.LifeCycle,Set<Plant>>(Plant.LifeCycle.class);

        for (Plant.LifeCycle lc: Plant.LifeCycle.values()) {
            plantsByLiftCycle.put(lc, new HashSet<>());
        }

        // 实例化一个花园，并添加植物
        List<Plant> garden = new ArrayList<>();
        Plant plant = new Plant("一年生植物", Plant.LifeCycle.ANNUAL);
        garden.add(plant);

        for (Plant p : garden) {
            plantsByLiftCycle.get(p.lifeCycle).add(p);
        }
    }
}
