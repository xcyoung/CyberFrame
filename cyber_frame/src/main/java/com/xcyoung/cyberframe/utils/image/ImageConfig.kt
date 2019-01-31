package com.xcyoung.cyberframe.utils.image

/**
 * @author ChorYeung
 * @since 2019/1/12
 */
class ImageConfig {
    companion object {
        var placeHolderRes:Int = 0
        var errorRes:Int = 0

        @JvmStatic
        fun setPlaceHolder(res:Int) {
            placeHolderRes = res
        }
        @JvmStatic
        fun setError(res:Int){
            errorRes = res
        }
    }
}