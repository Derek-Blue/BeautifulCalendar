package com.farris.beauty.time.sdjdi.type

sealed class ForecastApiType(
    open val path: String,
    open val description: String
) {

    object ThirtySixHours : ForecastApiType("F-C0032-001", "一般天氣預報-今明 36 小時天氣預報")

    sealed class TownShip(path: String, description: String) : ForecastApiType(path, description) {

        sealed class ThreeDays(
            val name: String,
            override val path: String,
            override val description: String
        ) : TownShip(path, description) {

            object Yilan : ThreeDays("宜蘭縣", "F-D0047-001", "鄉鎮天氣預報-宜蘭縣未來2天天氣預報")
            object Taoyuan : ThreeDays("桃園市", "F-D0047-005", "鄉鎮天氣預報-桃園市未來2天天氣預報")
            object HsinChu : ThreeDays("新竹縣", "F-D0047-009", "鄉鎮天氣預報-新竹縣未來2天天氣預報")
            object Miaoli : ThreeDays("苗栗縣", "F-D0047-013", "鄉鎮天氣預報-苗栗縣未來2天天氣預報")
            object Changhua : ThreeDays("彰化縣", "F-D0047-017", "鄉鎮天氣預報-彰化縣未來2天天氣預報")
            object Nantou : ThreeDays("南投縣", "F-D0047-021", "鄉鎮天氣預報-南投縣未來2天天氣預報")
            object Yunlin : ThreeDays("雲林縣", "F-D0047-025", "鄉鎮天氣預報-雲林縣未來2天天氣預報")
            object Chiayi : ThreeDays("嘉義縣", "F-D0047-029", "鄉鎮天氣預報-嘉義縣未來2天天氣預報")
            object Pingtung : ThreeDays("屏東縣", "F-D0047-033", "鄉鎮天氣預報-屏東縣未來2天天氣預報")
            object Taitung : ThreeDays("臺東縣", "F-D0047-037", "鄉鎮天氣預報-臺東縣未來2天天氣預報")
            object Hualien : ThreeDays("花蓮縣", "F-D0047-041", "鄉鎮天氣預報-花蓮縣未來2天天氣預報")
            object Penghu : ThreeDays("澎湖縣", "F-D0047-045", "鄉鎮天氣預報-澎湖縣未來2天天氣預報")
            object Keelung : ThreeDays("基隆市", "F-D0047-049", "鄉鎮天氣預報-基隆市未來2天天氣預報")
            object HsinChuCity : ThreeDays("新竹市", "F-D0047-053", "鄉鎮天氣預報-新竹市未來2天天氣預報")
            object ChiayiCity : ThreeDays("嘉義市", "F-D0047-057", "鄉鎮天氣預報-嘉義市未來2天天氣預報")
            object Taipei : ThreeDays("臺北市", "F-D0047-061", "鄉鎮天氣預報-臺北市未來2天天氣預報")
            object Kaohsiung : ThreeDays("高雄市", "F-D0047-065", "鄉鎮天氣預報-高雄市未來2天天氣預報")
            object NewTaipei : ThreeDays("新北市", "F-D0047-069", "鄉鎮天氣預報-新北市未來2天天氣預報")
            object Taichung : ThreeDays("臺中市", "F-D0047-073", "鄉鎮天氣預報-臺中市未來2天天氣預報")
            object Tainan : ThreeDays("臺南市", "F-D0047-077", "鄉鎮天氣預報-臺南市未來2天天氣預報")
            object Lianjiang : ThreeDays("連江縣", "F-D0047-081", "鄉鎮天氣預報-連江縣未來2天天氣預報")
            object Kinmen : ThreeDays("金門縣", "F-D0047-085", "鄉鎮天氣預報-金門縣未來2天天氣預報")
        }

        sealed class OneWeek(
            val name: String,
            override val path: String,
            override val description: String
        ) : TownShip(path, description) {

            object Yilan : OneWeek("宜蘭", "F-D0047-003", "鄉鎮天氣預報-宜蘭縣未來1週天氣預報")
            object Taoyuan : OneWeek("桃園", "F-D0047-007", "鄉鎮天氣預報-桃園市未來1週天氣預報")
            object HsinChu : OneWeek("新竹縣", "F-D0047-011", "鄉鎮天氣預報-新竹縣未來1週天氣預報")
            object Miaoli : OneWeek("苗栗縣", "F-D0047-015", "鄉鎮天氣預報-苗栗縣未來1週天氣預報")
            object Changhua : OneWeek("彰化縣", "F-D0047-019", "鄉鎮天氣預報-彰化縣未來1週天氣預報")
            object Nantou : OneWeek("南投縣", "F-D0047-023", "鄉鎮天氣預報-南投縣未來1週天氣預報")
            object Yunlin : OneWeek("雲林縣", "F-D0047-027", "鄉鎮天氣預報-雲林縣未來1週天氣預報")
            object Chiayi : OneWeek("嘉義縣", "F-D0047-031", "鄉鎮天氣預報-嘉義縣未來1週天氣預報")
            object Pingtung : OneWeek("屏東縣", "F-D0047-035", "鄉鎮天氣預報-屏東縣未來1週天氣預報")
            object Taitung : OneWeek("臺東縣", "F-D0047-039", "鄉鎮天氣預報-臺東縣未來1週天氣預報")
            object Hualien : OneWeek("花蓮縣", "F-D0047-043", "鄉鎮天氣預報-花蓮縣未來1週天氣預報")
            object Penghu : OneWeek("澎湖縣", "F-D0047-047", "鄉鎮天氣預報-澎湖縣未來1週天氣預報")
            object Keelung : OneWeek("基隆市", "F-D0047-051", "鄉鎮天氣預報-基隆市未來1週天氣預報")
            object HsinChuCity : OneWeek("新竹市", "F-D0047-055", "鄉鎮天氣預報-新竹市未來1週天氣預報")
            object ChiayiCity : OneWeek("嘉義市", "F-D0047-059", "鄉鎮天氣預報-嘉義市未來1週天氣預報")
            object Taipei : OneWeek("臺北市", "F-D0047-063", "鄉鎮天氣預報-臺北市未來1週天氣預報")
            object Kaohsiung : OneWeek("高雄市", "F-D0047-067", "鄉鎮天氣預報-高雄市未來1週天氣預報")
            object NewTaipei : OneWeek("新北市", "F-D0047-071", "鄉鎮天氣預報-新北市未來1週天氣預報")
            object Taichung : OneWeek("臺中市", "F-D0047-075", "鄉鎮天氣預報-臺中市未來1週天氣預報")
            object Tainan : OneWeek("臺南市", "F-D0047-079", "鄉鎮天氣預報-臺南市未來1週天氣預報")
            object Lianjiang : OneWeek("連江縣", "F-D0047-083", "鄉鎮天氣預報-連江縣未來1週天氣預報")
            object Kinmen : OneWeek("金門縣", "F-D0047-087", "鄉鎮天氣預報-金門縣未來1週天氣預報")
        }
    }

    sealed class AllTaiwan(
        override val path: String,
        override val description: String
    ) : ForecastApiType(path, description) {
        object TwoDays: AllTaiwan("F-D0047-089", "鄉鎮天氣預報-臺灣未來 2 天天氣預報")
        object OneWeek: AllTaiwan("F-D0047-091", "鄉鎮天氣預報-臺灣未來1週天氣預報")
    }

    object ChooseLocation: ForecastApiType("F-D0047-093", "鄉鎮天氣預報-全臺灣各鄉鎮市區預報資料")

    object Tidal: ForecastApiType("F-A0021-001", "潮汐預報-未來 1 個月潮汐預報")

    companion object {
        val threeDaysForecasts = listOf(
            TownShip.ThreeDays.Yilan,
            TownShip.ThreeDays.Yilan,
            TownShip.ThreeDays.Taoyuan,
            TownShip.ThreeDays.HsinChu,
            TownShip.ThreeDays.Miaoli,
            TownShip.ThreeDays.Changhua,
            TownShip.ThreeDays.Nantou,
            TownShip.ThreeDays.Yunlin,
            TownShip.ThreeDays.Chiayi,
            TownShip.ThreeDays.Pingtung,
            TownShip.ThreeDays.Taitung,
            TownShip.ThreeDays.Hualien,
            TownShip.ThreeDays.Penghu,
            TownShip.ThreeDays.Keelung,
            TownShip.ThreeDays.HsinChuCity,
            TownShip.ThreeDays.ChiayiCity,
            TownShip.ThreeDays.Taipei,
            TownShip.ThreeDays.Kaohsiung,
            TownShip.ThreeDays.NewTaipei,
            TownShip.ThreeDays.Taichung,
            TownShip.ThreeDays.Tainan,
            TownShip.ThreeDays.Lianjiang,
            TownShip.ThreeDays.Kinmen
        )
        val oneWeekForecast = listOf(
            TownShip.OneWeek.Yilan,
            TownShip.OneWeek.Yilan,
            TownShip.OneWeek.Taoyuan,
            TownShip.OneWeek.HsinChu,
            TownShip.OneWeek.Miaoli,
            TownShip.OneWeek.Changhua,
            TownShip.OneWeek.Nantou,
            TownShip.OneWeek.Yunlin,
            TownShip.OneWeek.Chiayi,
            TownShip.OneWeek.Pingtung,
            TownShip.OneWeek.Taitung,
            TownShip.OneWeek.Hualien,
            TownShip.OneWeek.Penghu,
            TownShip.OneWeek.Keelung,
            TownShip.OneWeek.HsinChuCity,
            TownShip.OneWeek.ChiayiCity,
            TownShip.OneWeek.Taipei,
            TownShip.OneWeek.Kaohsiung,
            TownShip.OneWeek.NewTaipei,
            TownShip.OneWeek.Taichung,
            TownShip.OneWeek.Tainan,
            TownShip.OneWeek.Lianjiang,
            TownShip.OneWeek.Kinmen
        )

        fun threeDaysForecastFromName(name: String): TownShip.ThreeDays {
            return threeDaysForecasts.find { it.name == name } ?: TownShip.ThreeDays.Taoyuan
        }

        fun oneWeekForecastFromName(name: String): TownShip.OneWeek {
            return oneWeekForecast.find { it.name == name } ?: TownShip.OneWeek.Taoyuan
        }
    }
}