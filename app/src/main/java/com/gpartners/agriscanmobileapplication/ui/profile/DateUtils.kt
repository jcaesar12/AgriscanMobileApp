package com.gpartners.agriscanmobileapplication.ui.profile

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun getTodayDate(): String = sdf.format(Calendar.getInstance().time)

    /** Attach a DatePicker to an EditText */
    fun attachDatePicker(context: Context, editText: EditText) {
        val calendar = Calendar.getInstance()
        editText.setText(sdf.format(calendar.time))
        editText.setOnClickListener {
            val dpd = DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    calendar.set(year, month, dayOfMonth)
                    editText.setText(sdf.format(calendar.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }
    }
}