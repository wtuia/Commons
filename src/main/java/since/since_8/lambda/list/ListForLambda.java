package since.since_8.lambda.list;

import java.util.ArrayList;
import java.util.List;


// 使用lambda 遍历List
public class ListForLambda {

    public static void main(String[] args) {
        ArrayList list = new ArrayList(){
            {
                add("lihua");
                add("wangmeimei");
                add("zhangsan");
                add("lisi");
            }
        };
        // s -> s.startsWith("l") 接收参数s返回该方法的值
        // 用与实例化 boolean check(String s), 与此方法定义语法相同

        checkAndExecutor(list, s -> s.startsWith("l"), // 实例化check 方法
                System.out::println); // 实例化execute方法
    }

    /**
     * 定义方法
     */
    public static void checkAndExecutor(List<String> list,
                                        NameChecker checker,
                                        Executor executor) {
        for (String s : list) {
            if (checker.check(s)){
            }
        }
    }

}
