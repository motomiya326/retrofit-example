package net.laurahouse.retrofit_example.data.api.response

import net.laurahouse.retrofit_example.data.Address

data class ZipResponse(
        var message: String? = null,
        var status: Int? = null,
        var results: ArrayList<Address> = ArrayList()
)