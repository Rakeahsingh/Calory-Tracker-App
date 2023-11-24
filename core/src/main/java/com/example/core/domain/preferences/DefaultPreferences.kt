package com.example.core.domain.preferences

import android.content.SharedPreferences
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo

class DefaultPreferences(
    private val sharePref: SharedPreferences
): Preferences {
    override fun SaveGender(gender: Gender) {
        sharePref.edit()
            .putString(Preferences.KEY_GENDER, gender.name)
            .apply()
    }

    override fun SaveAge(age: Int) {
        sharePref.edit()
            .putInt(Preferences.KEY_AGE, age)
            .apply()
    }

    override fun SaveHeight(height: Int) {
        sharePref.edit()
            .putInt(Preferences.KEY_HEIGHT, height)
            .apply()
    }

    override fun SaveWeight(weight: Float) {
        sharePref.edit()
            .putFloat(Preferences.KEY_WEIGHT, weight)
            .apply()
    }

    override fun SaveActivityLevel(level: ActivityLevel) {
        sharePref.edit()
            .putString(Preferences.KEY_ACTIVITY_LEVEL, level.name)
            .apply()
    }

    override fun SaveGoalType(type: GoalType) {
        sharePref.edit()
            .putString(Preferences.KEY_GOAL_TYPE, type.name)
            .apply()
    }

    override fun SaveCarbRatio(ratio: Float) {
        sharePref.edit()
            .putFloat(Preferences.KEY_CARB_RATIO, ratio)
            .apply()
    }

    override fun SaveProteinRatio(ratio: Float) {
        sharePref.edit()
            .putFloat(Preferences.KEY_PROTEIN_RATIO, ratio)
            .apply()
    }

    override fun SaveFatRatio(ratio: Float) {
        sharePref.edit()
            .putFloat(Preferences.KEY_FAT_RATIO, ratio)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharePref.getInt(Preferences.KEY_AGE, -1)
        val height = sharePref.getInt(Preferences.KEY_HEIGHT, -1)
        val weight = sharePref.getFloat(Preferences.KEY_WEIGHT, -1f)
        val genderString = sharePref.getString(Preferences.KEY_GENDER, null)
        val activityLevelString = sharePref
            .getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val goalType = sharePref.getString(Preferences.KEY_GOAL_TYPE, null)
        val carbRatio = sharePref.getFloat(Preferences.KEY_CARB_RATIO, -1f)
        val proteinRatio = sharePref.getFloat(Preferences.KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharePref.getFloat(Preferences.KEY_FAT_RATIO, -1f)

        return UserInfo(
            gender = Gender.fromString(genderString ?: "female"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalType ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio)
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharePref.edit()
            .putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow)
            .apply()
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharePref.getBoolean(
            Preferences.KEY_SHOULD_SHOW_ONBOARDING,
            true
        )
    }
}