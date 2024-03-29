package com.farris.beauty.time.sdjdi.type

enum class WeatherElementType(val elementName: String, val description: String) {

    RainChance12H("PoP12h", "12小時降雨機率"),
    AverageTemp("T", "平均溫度"),
    MinTemp("MinT", "最低溫度"),
    MaxTemp("MaxT", "最高溫度"),
    MaxBodyTemp("MaxAT", "最高體感溫度"),
    MinBodyTemp("MinAT", "最低體感溫度"),
    DewPointTemp("Td", "露點溫度"),
    MaxWindSpeed("WS", "最大風速"),
    WindDirection("WD", "風向"),
    MinComfort("MinCI", "最小舒適度指數"),
    MaxComfort("MaxCI", "最大舒適度指數"),
    UVIndex("UVI", "紫外線指數"),
    AverageHumidity("RH", "平均相對濕度"),
    WeatherPhenomenon("Wx", "天氣現象"),
    WeatherDescription("WeatherDescription", "天氣預報綜合描述"),

    BodyTemp("AT", "體感溫度"),
    Comfort("CI", "舒適度指數");

    companion object {

        fun threeDaysElements(): List<WeatherElementType> = listOf(
            WeatherPhenomenon,
            RainChance12H,
            AverageTemp,
            BodyTemp,
            Comfort,
            AverageHumidity,
            WeatherDescription,
            MaxWindSpeed,
            WindDirection,
            DewPointTemp
        )

        fun oneWeekElements(): List<WeatherElementType> {
            return values().filterNot {
                it == BodyTemp || it == Comfort
            }
        }

        fun fromName(name: String): WeatherElementType? {
            return values().find { it.elementName == name }
        }
    }
}