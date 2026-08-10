package com.example.forgetpassword.models

import java.time.LocalTime

object AttendanceState {
    var isCheckedIn = false
    var isCheckedOut = false
    var checkInTimeText = "--:--"
    var checkOutTimeText = "--:--"
    var workingHoursText = "--:--"
    var checkInTimeRaw: LocalTime? = null
}