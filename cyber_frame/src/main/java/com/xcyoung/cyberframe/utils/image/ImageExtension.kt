package com.xcyoung.cyberframe.utils.image

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.request.RequestOptions
import java.io.File

/**
 * @author ChorYeung
 * @since 2019/1/12
 */
fun ImageView.load(url:String,placeholderRes:Int = ImageConfig.placeHolderRes,errorRes: Int = ImageConfig.errorRes){
    Glide.with(context)
            .asBitmap()
            .load(url)
            .transition(BitmapTransitionOptions.withCrossFade())
            .apply(RequestOptions()
                .placeholder(placeholderRes)
                .error(errorRes)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
            .into(this)
}

fun ImageView.load(drawableId: Int,placeholderRes:Int = ImageConfig.placeHolderRes,errorRes: Int = ImageConfig.errorRes){
    Glide.with(context)
            .asBitmap()
            .load(drawableId)
            .transition(BitmapTransitionOptions.withCrossFade())
            .apply(RequestOptions()
                    .placeholder(placeholderRes)
                    .error(errorRes)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
            .into(this)
}

fun ImageView.load(file:File,placeholderRes:Int = ImageConfig.placeHolderRes,errorRes: Int = ImageConfig.errorRes){
    Glide.with(context)
            .asBitmap()
            .load(file)
            .transition(BitmapTransitionOptions.withCrossFade())
            .apply(RequestOptions()
                    .placeholder(placeholderRes)
                    .error(errorRes)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
            .into(this)
}

fun ImageView.load(uri: Uri,placeholderRes:Int = ImageConfig.placeHolderRes,errorRes: Int = ImageConfig.errorRes){
    Glide.with(context)
            .asBitmap()
            .load(uri)
            .transition(BitmapTransitionOptions.withCrossFade())
            .apply(RequestOptions()
                    .placeholder(placeholderRes)
                    .error(errorRes)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE))
            .into(this)
}