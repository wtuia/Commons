package java_lang.cloneable;

import org.junit.Test;

public class CloneTest {

    @Test
    public void cloneTest() {
        UserName fullName = new UserName().setFullName("full");
        UserClone user = new UserClone().setFirstName("first")
                .setLastName("last").setUserName(fullName);
        user.setId(11);
        UserClone userClone = null;

        try {
            userClone = (UserClone) user.clone();
            userClone.setFirstName("clone first");
            userClone.setLastName("clone last");
            userClone.setUserName(userClone.getUserName().setFullName("clone full"));
            userClone.setId(12);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(user);
        System.out.println(userClone);
    }
}
