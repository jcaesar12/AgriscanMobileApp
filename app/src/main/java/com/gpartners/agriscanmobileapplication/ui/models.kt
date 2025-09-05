package com.gpartners.agriscanmobileapplication.ui

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.math.BigDecimal
import java.util.Date

// ---------------- ACTIVITY SEASONS ----------------
@Entity
data class ActivitySeason(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val activityId: Int,
    val seasonId: Int,
    val recommendedTimeOfDay: String? = null,
    val notes: String? = null
)

// ---------------- AGRICULTURAL ACTIVITIES ----------------
@Entity
data class AgriculturalActivity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val activityName: String,
    val avgCarbonEmissions: BigDecimal? = null,
    val sequestrationPractices: String? = null,
    val bestTimeOfDay: String? = null,
    val bestSeason: String? = null,
    val otherDetails: String? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- CARBON FOOTPRINT ----------------
@Entity
data class CarbonFootprint(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val activityId: Int,
    val emissionType: String, // "Emission" or "Sequestration"
    val carbonAmount: BigDecimal,
    val measurementMethod: String? = null,
    val activityDate: Date,
    val notes: String? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- COUNTY TABLE ----------------
@Entity
data class County(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String
)

// ---------------- CROP PRODUCTION DATA ----------------
@Entity
data class CropProductionData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val mainCropId: Int? = null,
    val acreagePerCrop: BigDecimal? = null,
    val cropVarietyId: Int? = null,
    val plantingSeasonId: Int? = null,
    val averageYields: BigDecimal? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- FARM PROFILE ----------------
@Entity
data class FarmProfile(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val totalFarmSize: BigDecimal? = null,
    val landTenureId: Int? = null,
    val irrigationTypeId: Int? = null,
    val soilTypeId: Int? = null,
    val waterAccessId: Int? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- FARMER ACTIVITIES ----------------
@Entity
data class FarmerActivity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val activityId: Int,
    val startDate: Date? = null,
    val endDate: Date? = null,
    val notes: String? = null
)

// ---------------- FARMER REGISTRATION ----------------
@Entity
data class FarmerRegistration(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val firstName: String? = null,
    val middlename: String? = null,
    val surname: String? = null,
    val username: String,
    val password: String,
    val villageId: Int? = null,
    val sexId: Int? = null,
    val dob: Date? = null,
    val maritalStatusId: Int? = null,
    val educationLevelId: Int? = null,
    val householdSize: Int? = null,
    val disabilityStatusId: Int? = null,
    val gpsCoordinates: String? = null,
    val primaryOccupationId: Int? = null,
    val otherPrimaryOccupation: String? = null,
    val secondaryOccupationId: Int? = null,
    val otherSecondaryOccupation: String? = null,
    val annualIncomeBracketId: Int? = null,
    val creditAccessId: Boolean? = null,
    val membersFarmerCooperative: Boolean? = null,
    val farmerCooperativeId: Int? = null,
    val insuranceAccessCurrentlyId: Int? = null,
    val nationalId: String? = null,
    val idPhoto: ByteArray? = null,
    val farmerPhoto: ByteArray? = null,
    val dataUseSharingConsent: Boolean? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- IMAGE SCAN ----------------
@Entity
data class ImageScan(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val cropTypeId: Int,
    val diagnosisImage: ByteArray? = null,
    val diagnosisDescription: String? = null,
    val imageMetaData: String? = null, // JSON string if storing locally
    val geolocation: String? = null,
    val deviceTypeId: Int? = null,
    val diagnosisResult: String? = null,
    val recommendedAction: String? = null,
    val confidenceScore: BigDecimal? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- LIVESTOCK DATA ----------------
@Entity
data class LivestockData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val livestockTypeId: Int? = null,
    val flockSize: Int? = null,
    val breedInformation: String? = null,
    val productionPurposeId: Int? = null,
    val averageOutputMonthly: BigDecimal? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- SEASONS ----------------
@Entity
data class Season(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val subCountyId: Int? = null,
    val seasonName: String,
    val startMonth: Int,
    val endMonth: Int,
    val notes: String? = null
)

// ---------------- SUB COUNTY ----------------
@Entity
data class SubCounty(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val countyId: Int
)

// ---------------- SUSTAINABILITY ----------------
@Entity
data class Sustainability(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val climateChallengesId: Int? = null,
    val otherClimateChallenges: String? = null,
    val adaptationStrategiesId: Int? = null,
    val otherAdaptationChallenges: String? = null,
    val renewableEnergyUsedId: Int? = null,
    val otherRenewableEnergy: String? = null,
    val environmentalPracticesId: Int? = null,
    val otherEnvironmentalPractices: String? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- SYSTEM CODE DETAILS ----------------
@Entity
data class SystemCodeDetail(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val codeType: String,
    val codeValue: String,
    val description: String? = null
)

// ---------------- TECHNOLOGY ACCESS ----------------
@Entity
data class TechnologyAccess(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val phoneOwnershipId: Int? = null,
    val internetAccess: Boolean? = null,
    val useMobileMoney: Boolean? = null,
    val accessToExtensionServicesId: Int? = null,
    val marketingChannelsUsedId: Int? = null,
    val challengesFacedId: Int? = null,
    val otherChallenges: String? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- TRAINING NEEDS ----------------
@Entity
data class TrainingNeed(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val capacityBuildingInterestId: Int? = null,
    val previousTrainingsAttended: String? = null,
    val preferredLanguageId: Int? = null,
    val otherLanguage: String? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- USERS ----------------
@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val username: String,
    val password: String,
    val role: String = "Farmer", // "Admin","FieldOfficer","Farmer"
    val createdAt: Date? = null
)

// ---------------- VILLAGE TABLE ----------------
@Entity
data class Village(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val subCountyId: Int
)

// ---------------- FINANCIAL ENTRY ----------------
@Entity
data class FinancialEntry(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val entryType: String, // "Income" or "Expense"
    val category: String,
    val amount: BigDecimal,
    val entryDate: Date,
    val notes: String? = null,
    val createdOn: Date? = null,
    val modifiedOn: Date? = null,
    val createdById: Int? = null,
    val modifiedById: Int? = null
)

// ---------------- CHAT HISTORY ----------------
@Entity
data class ChatHistory(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val farmerRegistrationId: Int,
    val topic: String? = null,
    val message: String,
    val sender: String, // "Farmer" or "Bot"
    val messageType: String = "Text", // "Text", "Image", "Audio"
    val createdAt: Date? = null,
    val synced: Boolean? = false,
    val metadataInfo: String? = null
)

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? = value?.toPlainString()

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? = value?.let { BigDecimal(it) }
}