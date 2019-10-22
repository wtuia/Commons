package java_lang.enums.enumDemo;

/**
 * 带方法的枚举<br/>
 * 带 +-* / 的计算器，且扩展性好
 * (abstract的方法，在后续添加新计算时保证实现计算行为)
 */
public enum Operation {
    PLUS{
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    public abstract double apply(double x, double y);
}
