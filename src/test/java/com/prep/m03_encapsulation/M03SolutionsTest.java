package com.prep.m03_encapsulation;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.prep.m03_encapsulation.solutions.*;
import static org.assertj.core.api.Assertions.*;

public class M03SolutionsTest {

    @Tag("solutions")
    @Test
    void testBankAccount() {
        var acc = new Ex1_BankAccountS(100);
        assertThat(acc.getBalance()).isEqualTo(100);

        acc.deposit(50);
        assertThat(acc.getBalance()).isEqualTo(150);

        assertThat(acc.withdraw(30)).isTrue();
        assertThat(acc.getBalance()).isEqualTo(120);

        assertThat(acc.withdraw(200)).isFalse();
        assertThat(acc.getBalance()).isEqualTo(120);
    }

    @Tag("solutions")
    @Test
    void testBankAccountInvariants() {
        assertThatThrownBy(() -> new Ex1_BankAccountS(-10))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
