package com.iddevops.common.presentation.activity.dashboard

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract

class DashboardDefaultChannel : ActivityResultContract<Any?, Unit>() {
    override fun createIntent(context: Context, justParseNull: Any?): Intent {
        Log.d("TAG", "onCreate: Launch dashboard activity through default channel")
        return Intent(context, DashboardActivity::class.java)
    }

    override fun parseResult(resultCode: Int, intent: Intent?) {
        Log.d("TAG", "parseResult: Dashboard activity finished")
        return
    }
}