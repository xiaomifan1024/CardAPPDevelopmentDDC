package com.example.practice.network.base

import java.util.*

/**
 * Auto-generated: 2021-12-09 12:0:12
 *
 * @author fan
 * @website http://www.bejson.com/java2pojo/
 */
class WeatherGson {
    var cityid: String? = null
    var city: String? = null
    var cityEn: String? = null
    var country: String? = null
    var countryEn: String? = null
    var update_time: String? = null
    var dailyData: List<DailyBean>? = null
    var aqi: AqiBean? = null

    class DailyBean {
        /**
         * "day": "14日（星期二）","date": "2021-12-14","week": "星期二","wea": "多云", "wea_img": "yun", "wea_day": "多云","wea_day_img": "yun","wea_night": "多云","wea_night_img": "yun", "tem": "8",
         * "tem1": "8","tem2": "1","humidity": "","visibility": "","pressure": "",
         * "win": [],   "win_speed": "5-6级",   "win_meter": "",       "win_meter": "",  "sunrise": "07:03","sunset": "16:31", "air": "23","air_level": "优", "air_tips": "",
         * "alarm": {"alarm_type": "","alarm_level": "", "alarm_content": ""},
         * "hours": [ { "hours": "08时","wea": "多云","wea_img": "yun","tem": "2","win": "西南风","win_speed": "5-6级"},] ,
         * "index": [{"title": "紫外线指数","level": "中等", "desc": "涂擦SPF大于15、PA+防晒护肤品。"  },]
         */
        var day: String? = null
        var date: String? = null
        var week: String? = null
        var wea: String? = null
        var wea_img: String? = null
        var wea_day: String? = null
        var wea_day_img: String? = null
        var wea_night: String? = null
        var wea_night_img: String? = null
        var tem: String? = null
        var tem1: String? = null
        var tem2: String? = null
        var humidity: String? = null
        var visibility: String? = null
        var pressure: String? = null
        var win: List<String>? = null
        var win_speed: String? = null
        var win_meter: String? = null
        var sunrise: String? = null
        var sunset: String? = null
        var air: String? = null
        var air_level: String? = null
        var air_tips: String? = null
        var alarm: AlarmBean? = null
        var hours: List<HoursBean>? = null
        var index: List<IndexBean>? = null
    }

    class AlarmBean {
        /**
         * "alarm": {"alarm_type": "", "alarm_level": "","alarm_content": "" }
         */
        var alarm_type: String? = null
        var alarm_level: String? = null
        var alarm_content: String? = null
    }

    class HoursBean {
        /**
         * "hours": "08时",
         * "wea": "多云",
         * "wea_img": "yun",
         * "tem": "1",
         * "win": "西风",
         * "win_speed": "4-5级"
         */
        var hours: String? = null
        var wea: String? = null
        var wea_img: String? = null
        var tem: String? = null
        var win: String? = null
        var win_speed: String? = null
    }

    class IndexBean {
        /**
         * "title": "紫外线指数",
         * "level": "中等",
         * "desc": "涂擦SPF大于15、PA+防晒护肤品。"
         */
        var title: String? = null
        var level: String? = null
        var desc: String? = null
    }

    class AqiBean {
        /**
         * "aqi": {
         * "update_time": "09:14","cityid": "101070201","city": "大连","cityEn": "dalian","country": "中国","countryEn": "China","air": "51","air_level": "良",
         * "air_tips": "空气好，可以外出活动，除极少数对污染物特别敏感的人群以外，对公众没有危害！","pm25": "31","pm25_desc": "优","pm10": "51","pm10_desc": "良",
         * "o3": "35","o3_desc": "优","no2": "32","no2_desc": "优","so2": "5","so2_desc": "优","co": "-","co_desc": "-","kouzhao": "不用佩戴口罩","yundong": "适宜运动",
         * "waichu": "适宜外出","kaichuang": "适宜开窗","jinghuaqi": "关闭净化器"
         * }
         */
        var update_time: String? = null
        var cityid: String? = null
        var city: String? = null
        var cityEn: String? = null
        var country: String? = null
        var countryEn: String? = null
        var air: String? = null
        var air_level: String? = null
        var air_tips: String? = null
        var pm25: String? = null
        var pm25_desc: String? = null
        var pm10: String? = null
        var pm10_desc: String? = null
        var o3: String? = null
        var o3_desc: String? = null
        var no2: String? = null
        var no2_desc: String? = null
        var so2: String? = null
        var so2_desc: String? = null
        var co: String? = null
        var co_desc: String? = null
        var kouzhao: String? = null
        var yundong: String? = null
        var waichu: String? = null
        var kaichuang: String? = null
        var jinghuaqi: String? = null
    }
}