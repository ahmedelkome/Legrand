package com.route.legrand.fragments.oee

import android.os.Bundle
import android.view.View
import com.route.legrand.R
import com.route.legrand.base.BaseFragment
import com.route.legrand.databinding.FragmentOeeBinding
import com.route.legrand.utils.getHourIn12
import com.route.legrand.utils.getTimeAmPm
import com.route.legrand.utils.showDatePickerDialog
import com.route.legrand.utils.showTimePickerDialog
import java.util.Calendar

class OeeFragment : BaseFragment<FragmentOeeBinding>() {
    private var dateCalendar = Calendar.getInstance()
    private var timeCalendar = Calendar.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickDate()
        initClickTime()
    }

    private fun initClickTime() {
        binding.tilSelectTime.setOnClickListener {

            val calendar = Calendar.getInstance()

            showTimePickerDialog(
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                title = "Select task time",
                childFragmentManager
            ) { hour, minutes ->
                val minuteString = if (minutes == 0) "00" else minutes.toString()
                binding.selectTimeTv.text =
                    "${getHourIn12(hour)}:${minuteString} ${getTimeAmPm(hour)}"
                this.timeCalendar.set(Calendar.YEAR, 0)
                this.timeCalendar.set(Calendar.MONTH, 0)
                this.timeCalendar.set(Calendar.DAY_OF_MONTH, 0)
                this.timeCalendar.set(Calendar.HOUR_OF_DAY, hour)
                this.timeCalendar.set(Calendar.MINUTE, minutes)
                this.timeCalendar.set(Calendar.SECOND, 0)
                this.timeCalendar.set(Calendar.MILLISECOND, 0)
            }

        }
    }

    private fun initClickDate() {
        binding.tilSelectDate.setOnClickListener {
            context?.let { context ->
                showDatePickerDialog(context) { date, calendar ->
                    this.dateCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR))
                    this.dateCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
                    this.dateCalendar.set(
                        Calendar.DAY_OF_MONTH,
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    this.dateCalendar.set(Calendar.HOUR_OF_DAY, 0)
                    this.dateCalendar.set(Calendar.MINUTE, 0)
                    this.dateCalendar.set(Calendar.SECOND, 0)
                    this.dateCalendar.set(Calendar.MILLISECOND, 0)
                    binding.selectDateTv.text = date
                }
            }
        }
    }

    override fun observeLiveData() {

    }

    override fun getLayout(): Int = R.layout.fragment_oee
}