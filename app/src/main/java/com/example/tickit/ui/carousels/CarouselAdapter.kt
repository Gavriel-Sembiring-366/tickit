package com.example.tickit.ui.carousels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.tickit.R

class CarouselAdapter(
    private val ListCarouselItem: List<ItemCarousel>,
    private val itemWidth: Int,
    private val itemHeight: Int,
    private val backgroundColor: Int?
) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    private val infiniteScrollCount = ListCarouselItem.size * 100

    inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val Image: ImageView = itemView.findViewById(R.id.carousel_image)
        private val Background: TextView = itemView.findViewById(R.id.background)

        fun bind(Item: ItemCarousel) {
            Image.setImageResource(Item.imageResId)
            if (backgroundColor!=null){
                Background.setBackgroundColor(backgroundColor)
            }

            val layoutParams = itemView.layoutParams
            layoutParams.width = (itemWidth * itemView.resources.displayMetrics.density).toInt()
            layoutParams.height = (itemHeight * itemView.resources.displayMetrics.density).toInt()
            itemView.layoutParams = layoutParams
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false)
        return CarouselViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val actualPosition = (position % ListCarouselItem.size + ListCarouselItem.size) % ListCarouselItem.size
        holder.bind(ListCarouselItem[actualPosition])
    }

    override fun getItemCount(): Int {
        return infiniteScrollCount
    }


    // ----- // Warning (Use this to control a textview text when an item is on the center) // ----- //
    interface OnCenteredItemChangedListener {
        fun onCenteredItemChanged(title: String) //Currently Not in use
    }
    // ----- // Warning (Use this to control a textview text when an item is on the center) // ----- //


    fun attachSnapHelperWithListener(recyclerView: RecyclerView) {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.post {
            val centerView = snapHelper.findSnapView(recyclerView.layoutManager)
            centerView?.let {
                val position = recyclerView.getChildAdapterPosition(centerView)
                if (position != RecyclerView.NO_POSITION) {
                    (centerView.findViewById<TextView>(R.id.background))?.background = null
                }
            }
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val centerView = snapHelper.findSnapView(recyclerView.layoutManager)
                    for (i in 0 until recyclerView.childCount) {
                        val child = recyclerView.getChildAt(i)
                        if (child != centerView) {
                            if (backgroundColor != null) {
                                child.findViewById<TextView>(R.id.background)?.setBackgroundColor(backgroundColor)
                            }
                        } else {
                            // Set background of the centered item to null
                            child.findViewById<TextView>(R.id.background)?.background = null
                        }
                    }
                    centerView?.let {
                        val position = recyclerView.getChildAdapterPosition(centerView)
                        if (position != RecyclerView.NO_POSITION) {
                            (centerView.findViewById<TextView>(R.id.background))?.background = null
                        }
                    }
                }
            }
        })
    }
}
