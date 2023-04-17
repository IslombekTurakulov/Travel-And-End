package com.iuturakulov.uzbarchitecture_ar.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.iuturakulov.uzbarchitecture_ar.R

class ViewPagerWithTabs(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var viewPager: ViewPager
    private var tabLayout: TabLayout

    init {
        // Задать ориентацию LinearLayout
        orientation = VERTICAL

        // Надуть макет из XML-файла
        LayoutInflater.from(context).inflate(R.layout.custom_view_pager, this, true)

        // Найти ссылки на элементы макета
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        // Получить атрибуты из XML-файла
        val attributesArr = context.obtainStyledAttributes(attrs, R.styleable.CustomViewPager)

        // Получить значения атрибутов и установить их для ViewPager
        val viewPagerHeight =
            attributesArr.getDimensionPixelSize(R.styleable.CustomViewPager_viewPagerHeight, 0)
        val viewPagerWidth =
            attributesArr.getDimensionPixelSize(R.styleable.CustomViewPager_viewPagerWidth, 0)
        if (viewPagerHeight > 0) {
            viewPager.layoutParams.height = viewPagerHeight
        }
        if (viewPagerWidth > 0) {
            viewPager.layoutParams.width = viewPagerWidth
        }

        // Получить значения атрибутов и установить их для TabLayout
        val tabHeight =
            attributesArr.getDimensionPixelSize(R.styleable.CustomViewPager_tabHeight, 0)
        if (tabHeight > 0) {
            tabLayout.layoutParams.height = tabHeight
        }

        // Освободить ресурсы
        attributesArr.recycle()
    }

    // Методы для управления ViewPager и TabLayout
    fun setPagerAdapter(adapter: PagerAdapter) {
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    fun setTabText(index: Int, text: String) {
        tabLayout.getTabAt(index)?.text = text
    }
}
