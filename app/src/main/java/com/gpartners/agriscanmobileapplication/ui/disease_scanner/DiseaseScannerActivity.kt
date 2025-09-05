package com.gpartners.agriscanmobileapplication.ui.disease_scanner

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.cardview.widget.CardView
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.gpartners.agriscanmobileapplication.R
import com.gpartners.agriscanmobileapplication.ui.mock_data.ScanResult

class DiseaseScannerActivity : AppCompatActivity() {

    // Action buttons
    private lateinit var btnTakePhoto: CardView
    private lateinit var btnUploadImage: CardView

    // Recent Scans section
    private lateinit var recentScansCard: CardView

    // Image Preview
    private lateinit var cardImagePreview: CardView
    private lateinit var previewDescription: EditText
    private lateinit var btnAnalyzeImage: Button

    // Loading Card
    private lateinit var cardLoadingAnalyze: CardView

    // Mock data
    private val mockScans = mutableListOf<ScanResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_scan)

        // Bind views
        btnTakePhoto = findViewById(R.id.cardTakePhoto)
        btnUploadImage = findViewById(R.id.cardUploadImage)

        recentScansCard = findViewById(R.id.cardRecentScans)
        cardImagePreview = findViewById(R.id.cardImagePreview)
        previewDescription = findViewById(R.id.inputImageDescription)
        btnAnalyzeImage = findViewById(R.id.btnAnalyzeImage)

        cardLoadingAnalyze = findViewById(R.id.cardLoadingAnalyze)

        setupListeners()
        showRecentScans()
    }

    private fun setupListeners() {
        // Take Photo (mock)
        btnTakePhoto.setOnClickListener {
            navigateToImagePreview()
        }

        // Upload Image (mock)
        btnUploadImage.setOnClickListener {
            navigateToImagePreview()
        }

        // Analyze button in preview
        btnAnalyzeImage.setOnClickListener {
            startLoadingAndShowResults()
        }
    }

    private fun navigateToImagePreview() {
        // Hide recent scans, show preview
        cardImagePreview.visibility = View.VISIBLE
        recentScansCard.visibility = View.VISIBLE
        cardLoadingAnalyze.visibility = View.GONE
    }

    private fun startLoadingAndShowResults() {
        cardImagePreview.visibility = View.GONE
        cardLoadingAnalyze.visibility = View.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            cardLoadingAnalyze.visibility = View.GONE

            // Mock scan result
            val result = ScanResult(
                diseaseName = "Tomato Blight",
                probability = 87,
                description = "A common fungal disease that affects tomato leaves, causing dark spots and wilting.",
                treatment = "Apply copper-based fungicide weekly and remove infected leaves.",
                date = "22 Aug 2025"
            )
            mockScans.add(0, result)

            showScanResultDialog(result)
            showRecentScans()
        }, 2000) // 2 second fake delay
    }

    private fun showScanResultDialog(result: ScanResult) {
        val dialog = ScanResultDialog(this, result)
        dialog.show()
    }

    private fun showRecentScans() {
        val scansContainer: LinearLayout = findViewById(R.id.recentScansContainer)
        scansContainer.removeAllViews()

        if (mockScans.isEmpty()) {
            val emptyText = TextView(this).apply {
                text = "No scans yet\nTake your first photo to get started"
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                textSize = 16f
                setPadding(16, 32, 16, 32)
            }
            scansContainer.addView(emptyText)
        } else {
            for (scan in mockScans) {
                val view = layoutInflater.inflate(R.layout.item_recent_scan, scansContainer, false)

                val txtName = view.findViewById<TextView>(R.id.textDiseaseName)
                val txtProb = view.findViewById<TextView>(R.id.textDiseaseProbability)
                val txtDate = view.findViewById<TextView>(R.id.textScanDate)
                val txtTreatment = view.findViewById<TextView>(R.id.textDiseaseTreatment)

                txtName.text = scan.diseaseName
                txtProb.text = "Confidence: ${scan.probability}%"
                txtDate.text = scan.date
                txtTreatment.text = scan.treatment

                scansContainer.addView(view)
            }
        }
    }
}
