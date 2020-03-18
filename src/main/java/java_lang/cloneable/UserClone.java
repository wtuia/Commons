package java_lang.cloneable;

public class UserClone implements Cloneable{

    int id;
    private String firstName;
    private String lastName;
    private UserName userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserName getUserName() {
        return userName;
    }

    public UserClone setUserName(UserName userName) {
        this.userName = userName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserClone setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserClone setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        UserClone clone = (UserClone) super.clone();
        // 递归调用引用类型的clone方法（包括数组）
        clone.userName = (UserName) userName.clone();
        return clone;
    }

    @Override
    public String toString() {
        return "UserClone{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName=" + userName +
                '}';
    }
}
