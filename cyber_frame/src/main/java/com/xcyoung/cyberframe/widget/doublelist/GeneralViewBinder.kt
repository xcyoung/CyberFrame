package com.xcyoung.cyberframe.widget.doublelist

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author ChorYeung
 * @since 2019/1/26
 */
abstract class GeneralViewBinder<T:Any,VH: RecyclerView.ViewHolder>(private val isCanCancel:Boolean=true, defaultPos:Int= -1):RecyclerView.Adapter<VH>() {
    val items = ArrayList<T>()
    private var selectedPos = defaultPos
    private var preSelectedPos = -1     //用于记录之前选中的位置
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateView(parent)
    }

    override fun onBindViewHolder(holder: VH, @SuppressLint("RecyclerView") position: Int) {
        onBindView(holder, items[position])
        holder.itemView.setOnClickListener {
            if(selectedPos!= position){
                if(selectedPos!=-1){
                    onItemSelect(selectedPos, items[selectedPos],false)
                    notifyItemChanged(selectedPos,false)
                }
                preSelectedPos = selectedPos
                selectedPos = position
                onItemSelect(selectedPos, items[selectedPos],true)
                notifyItemChanged(selectedPos,true)
//                onSelectChange(if(preSelectedPos == -1) null else items[preSelectedPos],items[selectedPos])
            }else{
                if(isCanCancel){
                    preSelectedPos = selectedPos
                    selectedPos = -1
                    onItemSelect(position, items[position],false)
                    notifyItemChanged(position, false)
//                    onSelectChange(items[preSelectedPos],null)
                }
            }
        }
        if(selectedPos !=-1){           //第一次
            onSelectChange(if(preSelectedPos == -1) null else items[preSelectedPos],items[selectedPos])
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if(payloads.isEmpty()){
            this.onBindViewHolder(holder,position)
        }else{
//            val isSelected = payloads[0] as Boolean
            onBindView(holder, items[position])
            onSelectChange(if(preSelectedPos == -1) null else items[preSelectedPos],if(selectedPos == -1) null else items[selectedPos])
        }
    }

    fun setItems(items: List<T>?) {
        if (items == null) {
            return
        }
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(position: Int, item: T?) {
        if (item == null) {
            return
        }
        this.items.add(position, item)
        notifyItemInserted(position)
    }

    fun setDefaultPos(position: Int) {
        selectedPos = position
    }

    abstract fun onItemSelect(position:Int,item: T,isSelected:Boolean)

    abstract fun onCreateView(parent: ViewGroup):VH

    abstract fun onBindView(holder: VH, item: T)
    //1、null curItem        第一次被选中，默认选中为-1
    //2、preItem curItem     非第一次被选中
    //3、preItem null        取消当前选中
    abstract fun onSelectChange(preItem:T?,curItem:T?)
}