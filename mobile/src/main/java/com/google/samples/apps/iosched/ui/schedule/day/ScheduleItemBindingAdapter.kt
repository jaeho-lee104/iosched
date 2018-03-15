/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.iosched.ui.schedule.day

import android.content.Context
import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.google.samples.apps.iosched.R
import com.google.samples.apps.iosched.shared.firestore.entity.LastReservationRequested
import com.google.samples.apps.iosched.shared.firestore.entity.UserEvent
import org.threeten.bp.Duration
import org.threeten.bp.ZonedDateTime

@BindingAdapter(value = ["sessionStart", "sessionEnd", "sessionRoom"], requireAll = true)
fun sessionLengthLocation(
    textView: TextView,
    startTime: ZonedDateTime,
    endTime: ZonedDateTime,
    room: String
) {
    textView.text = textView.context.getString(
        R.string.session_duration_location,
            durationString(textView.context, Duration.between(startTime, endTime)), room
    )
}

private fun durationString(context: Context, duration: Duration): String {
    val hours = duration.toHours()
    return if (hours > 0L) {
        context.resources.getQuantityString(R.plurals.duration_hours, hours.toInt(), hours)
    } else {
        val minutes = duration.toMinutes()
        context.resources.getQuantityString(R.plurals.duration_minutes, minutes.toInt(), minutes)
    }
}

@BindingAdapter("reservation_status")
fun reservationStatus(
        imageView: ImageView,
        userEvent: UserEvent?
) {

    val context = imageView.context
    imageView.background = context.getDrawable(getReservationDrawable(userEvent))
}

fun getReservationDrawable(userEvent: UserEvent?) : Int {

    return when {
        userEvent?.isReserved() == true -> R.drawable.ic_reservation_reserved
        userEvent?.isWaitlisted() == true -> R.drawable.ic_reservation_waitlisted
        userEvent?.reservationRequested == LastReservationRequested.RESERVATION ->
            R.drawable.ic_reservation_pending
        else -> return R.drawable.ic_reservation_default
    }
}