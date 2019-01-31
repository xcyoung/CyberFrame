package com.xcyoung.cyberframe.widget.doublelist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xcyoung.cyberframe.R
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration

/**
 * @author ChorYeung
 * @since 2019/1/26
 */
class DoubleListLayout<LT:Any, LVH: RecyclerView.ViewHolder,
        RT:Any, RVH:RecyclerView.ViewHolder>(context: Context,
                                             val leftEventListener: EventListener<LT, LVH>,
                                             val rightEventListener: EventListener<RT, RVH>) : LinearLayout(context) {
    private val rvLeft:RecyclerView
    private val rvRight:RecyclerView

    private val leftAdapter =  object : GeneralViewBinder<LT, LVH>(false,0) {
        override fun onSelectChange(preItem: LT?, curItem: LT?) {
            leftEventListener.onSelectChange(preItem,curItem)
        }

        override fun onItemSelect(position:Int,item: LT, isSelected: Boolean) {
            leftEventListener.onItemSelect(position,item, isSelected)
        }

        override fun onCreateView(parent: ViewGroup): LVH {
            return leftEventListener.onCreateViewHolder(parent)
        }

        override fun onBindView(holder: LVH, item: LT) {
            leftEventListener.onBindViewHolder(holder, item)
        }
    }
    private val rightAdapter = object : GeneralViewBinder<RT, RVH>(){
        override fun onSelectChange(preItem: RT?, curItem: RT?) {
            rightEventListener.onSelectChange(preItem,curItem)
        }

        override fun onItemSelect(position:Int,item: RT, isSelected: Boolean) {
            rightEventListener.onItemSelect(position,item,isSelected)
        }

        override fun onCreateView(parent: ViewGroup): RVH {
            return rightEventListener.onCreateViewHolder(parent)
        }

        override fun onBindView(holder: RVH, item: RT) {
            rightEventListener.onBindViewHolder(holder,item)
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_double_list, this)
        rvLeft = this.findViewById(R.id.rvLeft)
        rvRight = this.findViewById(R.id.rvRight)
        rvLeft.layoutManager = LinearLayoutManager(context)
        rvRight.layoutManager = LinearLayoutManager(context)
        rvLeft.adapter = leftAdapter
        rvRight.adapter = rightAdapter
    }

    fun addLeftItemDecoration(color:Int,size:Int) = addItemDecoration(rvLeft,color,size)

    fun addRightItemDecoration(color:Int,size: Int) = addItemDecoration(rvRight,color,size)

    private fun addItemDecoration(rv: RecyclerView,color:Int,size:Int) {
        rv.addItemDecoration(HorizontalDividerItemDecoration.Builder(context)
                .color(color)
                .size(size)
                .build())
    }

    fun setRightData(list:List<RT>) = rightAdapter.setItems(list)

    fun setLeftData(list:List<LT>)  = leftAdapter.setItems(list)

    fun setLeftDefaultPos(position: Int) {
        leftAdapter.setDefaultPos(position)
    }

    fun setRightDefaultPos(position: Int) {
        rightAdapter.setDefaultPos(position)
    }

    interface EventListener<T:Any,VH:RecyclerView.ViewHolder>{
        fun onItemSelect(position:Int,item:T,isSelected:Boolean)
        fun onCreateViewHolder(parent: ViewGroup):VH
        fun onBindViewHolder(holder: VH, item: T)
        fun onSelectChange(preItem:T?,curItem:T?)
    }
}