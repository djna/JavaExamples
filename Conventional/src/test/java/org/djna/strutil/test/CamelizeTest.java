package org.djna.strutil.test;

import org.djna.example.strutil.Conventional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CamelizeTest {

    @Test
    void simpleText() {
        String customerPhoneHuman = "Customer primary phone number";
        String customerPhoneCamel = "CustomerPrimaryPhoneNumber";
        assertEquals(customerPhoneCamel, Conventional.camelize(customerPhoneHuman));
    }
}
