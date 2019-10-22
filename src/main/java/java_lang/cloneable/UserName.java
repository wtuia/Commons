package java_lang.cloneable;

public class UserName implements Cloneable {

    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public UserName setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return (UserName)super.clone();
    }

    @Override
    public String toString() {
        return "UserName{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
