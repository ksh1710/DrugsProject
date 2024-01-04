package com.example.drugsproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date


class NonSmokingHabitManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("NonSmokingHabitSharedPreferences", Context.MODE_PRIVATE)
    }
    private val gson = Gson()

    private val habitKey = "nonSmokingHabit"

    fun getNonSmokingHabit(): NonSmokingHabit {
        val habitJson = sharedPreferences.getString(habitKey, null)
        return gson.fromJson(habitJson, NonSmokingHabit::class.java) ?: NonSmokingHabit()
    }

    fun saveNonSmokingHabit(habit: NonSmokingHabit) {
        val habitJson = gson.toJson(habit)
        sharedPreferences.edit().putString(habitKey, habitJson).apply()
    }

    fun markNonSmokingDay() {
        val habit = getNonSmokingHabit()

        if (habit.lastCompletedDate != null) {
            val lastCompletedLocalDate = LocalDate.parse(habit.lastCompletedDate, DateTimeFormatter.ofPattern("yyyyMMdd"))
            val currentLocalDate = LocalDate.now()

            if (lastCompletedLocalDate.isEqual(currentLocalDate)) {
                // User clicked on the same day, don't update streak
                Toast.makeText(context,"testing",Toast.LENGTH_SHORT).show()
            } else if (lastCompletedLocalDate.isEqual(currentLocalDate.minusDays(1))) {
                // Habit completed consecutively, increment streak
                habit.streak++

            } else if (lastCompletedLocalDate.isBefore(currentLocalDate.minusDays(1))) {
                // Habit not completed consecutively, reset streak
                habit.streak = 1
            }
        } else {
            // First time completion
            habit.streak = 1
        }

        // Update lastCompletedDate
        habit.lastCompletedDate = getCurrentDate()
        saveNonSmokingHabit(habit)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDate(): String {
//            return System.currentTimeMillis().toString()
//            return SystemClock.elapsedRealtime().toString()
//            return dateFormat.format(Date())
        val dateFormat = SimpleDateFormat("yyyyMMdd")
        return dateFormat.format(Date())

    }
}
