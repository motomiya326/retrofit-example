package net.laurahouse.retrofit_example.data

data class Address(
        var address1: String ?= null, //"address1": "北海道",
        var address2: String ?= null, //"address2": "美唄市",
        var address3: String ?= null, //"address3": "上美唄町協和",
        var kana1: String ?= null, //"kana1": "ﾎｯｶｲﾄﾞｳ",
        var kana2: String ?= null, //"kana2": "ﾋﾞﾊﾞｲｼ",
        var kana3: String ?= null, //"kana3": "ｶﾐﾋﾞﾊﾞｲﾁｮｳｷｮｳﾜ",
        var prefcode: String ?= null, //"prefcode": "1",
        var zipcode: String ?= null //"zipcode": "0790177"
)