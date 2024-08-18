package com.route.legrand.fragments.production.oee

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.route.legrand.R
import com.route.legrand.base.BaseFragment
import com.route.legrand.databinding.FragmentOeeBinding
import com.route.legrand.utils.ConstantsApp
import com.route.legrand.utils.ConstantsApp.REQUEST_CODE
import com.route.legrand.utils.getHourIn12
import com.route.legrand.utils.getTimeAmPm
import com.route.legrand.utils.showDatePickerDialog
import com.route.legrand.utils.showTimePickerDialog
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class OeeFragment : BaseFragment<FragmentOeeBinding>() {
    private val oeeViewModel: OeeViewModel by viewModels<OeeViewModel>()
    private var adapter: ArrayAdapter<String>? = null
    private var dateCalendar = Calendar.getInstance()
    private var timeCalendar = Calendar.getInstance()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = oeeViewModel
        checkPermissions()
        initClickDate()
        initClickTime()
        initShiftList()
        initMachineList()
        oeeViewModel.getPartNumber()
    }
    private fun initPartNumberList(list: List<String>) {
        adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_dropdown_item_1line, list)
        binding.menuPartOee.setAdapter(adapter)
    }

    private fun initMachineList() {
        adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_dropdown_item_1line,
            ConstantsApp.listOfMachine
        )
        binding.menuMachine.setAdapter(adapter)
    }

    private fun initShiftList() {
        adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_dropdown_item_1line,
            ConstantsApp.listOfShift
        )
        binding.menuShift.setAdapter(adapter)
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
        oeeViewModel.listOfPartNUmber.observe(viewLifecycleOwner) {
            initPartNumberList(it)
        }
        oeeViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        oeeViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(it)
        }
        oeeViewModel.messageAdded.observe(viewLifecycleOwner) {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
            initInitialValueToEditText()
        }
        oeeViewModel.messageEdited.observe(viewLifecycleOwner){
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
            initInitialValueToEditText()
        }
    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_CODE)
            }
        }
    }

    private fun initInitialValueToEditText() {
        oeeViewModel.partNumberLiveData.value = ""
        oeeViewModel.shiftLiveData.value = ""
        oeeViewModel.machineLiveData.value = ""
        oeeViewModel.dateLiveData.value = ""
        oeeViewModel.timeLiveData.value = ""
        oeeViewModel.operatorLiveData.value = ""
        oeeViewModel.cavityNumberLiveData.value = ""
        oeeViewModel.totalProducedLiveData.value = ""
        oeeViewModel.workCvLiveData.value = ""
        oeeViewModel.CTLiveData.value = ""
        oeeViewModel.MELiveData.value = ""
        oeeViewModel.SULiveData.value = ""
        oeeViewModel.ELiveData.value = ""
        oeeViewModel.QLiveData.value = ""
        oeeViewModel.CMLiveData.value = ""
        oeeViewModel.MOLiveData.value = ""
        oeeViewModel.DMLiveData.value = ""
        oeeViewModel.RLiveData.value = ""
        oeeViewModel.HLiveData.value = ""
        oeeViewModel.COLiveData.value = ""
        oeeViewModel.WLLiveData.value = ""
        oeeViewModel.OTHLiveData.value = ""
    }


    override fun getLayout(): Int = R.layout.fragment_oee
}