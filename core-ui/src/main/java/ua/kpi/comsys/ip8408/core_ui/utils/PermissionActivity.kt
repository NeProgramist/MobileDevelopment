package ua.kpi.comsys.ip8408.core_ui.utils

import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

open class PermissionActivity : AppCompatActivity() {
    var requestPermissionLauncher: ActivityResultLauncher<String>? = null
    var permissionCallback: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            permissionCallback?.invoke(it)
        }
    }

}