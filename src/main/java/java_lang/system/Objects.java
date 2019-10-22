package java_lang.system;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Objects {

    @Test
    public void RequireNonNullDemo(){
        List<String> list = new ArrayList<>();
        try {
            java.util.Objects.requireNonNull(list);
        }catch (NullPointerException e) {
            list = new ArrayList<>();
        }
    }


}
