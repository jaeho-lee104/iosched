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

package com.google.samples.apps.iosched.shared.fcm

import com.google.firebase.iid.FirebaseInstanceId

import timber.log.Timber

/**
 * Service that receives new FCM ID Tokens.
 */
class IoschedFirebaseInstanceIDService : DaggerFirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val token = FirebaseInstanceId.getInstance().token

        Timber.d("Token refresh! $token") // STOPSHIP: Remove this

        // TODO: Update token in Firestore
    }
}
