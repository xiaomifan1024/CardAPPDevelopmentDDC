package com.example.practice.data

class CircleData(value: Int, contentText: String?, color: Int) {
    /**
     * 所占值
     */
    private var value = 0

    /**
     * 顶部文本
     */
    private var contentText: String? = null


    /**
     * 颜色
     */
    private var color = 0


    fun getValue(): Int {
        return value
    }

    fun setValue(value: Int) {
        this.value = value
    }

    fun getContentText(): String? {
        return contentText
    }

    fun setContentText(contentText: String?) {
        this.contentText = contentText
    }

    fun getColor(): Int {
        return color
    }

    fun setColor(color: Int) {
        this.color = color
    }
}