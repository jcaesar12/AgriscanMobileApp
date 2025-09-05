package com.gpartners.agriscanmobileapplication.ui.disease_scanner

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.gpartners.agriscanmobileapplication.R
import com.gpartners.agriscanmobileapplication.ui.mock_data.ScanResult

class ScanResultDialog(context: Context, private val result: ScanResult) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_scan_results)

        val txtDiseaseName = findViewById<TextView>(R.id.resultDiseaseName)
        val txtProb = findViewById<TextView>(R.id.resultDiseasePercent)
        val txtDescription = findViewById<TextView>(R.id.resultDiseaseDesc)
        val txtTreatment = findViewById<TextView>(R.id.resultTreatment)

        val btnAskMwanedu = findViewById<Button>(R.id.btnAskMwanedu)
        val btnSaveHistory = findViewById<Button>(R.id.btnSaveHistory)
        val btnScanAgain = findViewById<Button>(R.id.btnScanAgain)

        txtDiseaseName.text = result.diseaseName
        txtProb.text = "Confidence: ${result.probability}%"
        txtDescription.text = result.description
        txtTreatment.text = result.treatment

        btnAskMwanedu.setOnClickListener {
            dismiss()
        }

        btnSaveHistory.setOnClickListener {
            dismiss()
        }

        btnScanAgain.setOnClickListener {
            dismiss()
        }
    }
}
