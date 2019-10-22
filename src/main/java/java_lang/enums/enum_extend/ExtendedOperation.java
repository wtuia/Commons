package java_lang.enums.enum_extend;

public enum ExtendedOperation implements Operation{
    EXP("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    };

    public String symbol;

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }
}
