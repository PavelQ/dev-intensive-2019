package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import de.hdodenhof.circleimageview.CircleImageView
import ru.skillbranch.devintensive.R

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CircleImageView(context, attrs, defStyleAttr) {

    companion object{
        private const val DEFAULT_BORDER_WIDTH = 2f
        private const val DEFAULT_BORDER_COLOR = Color.WHITE
    }

init {
    val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
    borderWidth = a.getDimension(R.styleable.CircleImageView_cv_borderWidth, DEFAULT_BORDER_WIDTH).toInt()
    borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)
    a.recycle()
}

    @Dimension
    override fun getBorderWidth():Int{
        return super.getBorderWidth()
    }

    override fun setBorderWidth(@Dimension dp:Int){
        super.setBorderWidth(dp)
    }
    fun setBorderColor(hex:String){
        borderColor = Color.parseColor(hex)
    }
    override fun  setBorderColor(@ColorRes colorId: Int){
        super.setBorderColor(colorId)
    }
}