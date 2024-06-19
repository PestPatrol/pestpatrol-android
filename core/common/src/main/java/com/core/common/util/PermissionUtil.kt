package com.core.common.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object PermissionUtil {

    const val CAMERA_PERMISSION = Manifest.permission.CAMERA

    fun hasRequiredPermission(
        context: Context,
        permission: String,
    ): Boolean =
        ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    fun hasRequiredPermissions(
        context: Context,
        permissions: List<String>
    ): Boolean = permissions.all { permission ->
        ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}