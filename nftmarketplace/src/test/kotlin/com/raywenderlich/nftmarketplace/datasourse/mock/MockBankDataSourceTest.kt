package com.raywenderlich.nftmarketplace.datasourse.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {
    private val mockDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of bank`() {
        val banks = mockDataSource.retrieveBanks()

        assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should provide some mock data`() {
        val banks = mockDataSource.retrieveBanks()

        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        assertThat(banks).anyMatch { it.trust != 0.0 }
        assertThat(banks).anyMatch { it.transactionFee != 0}
//        `anyMatch` возвращает true, если хоть один элемент соотв условию
//        `allMatch` возвращает true, если все элементы соотв условию
    }
}