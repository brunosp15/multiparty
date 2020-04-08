package com.multiparty

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.multiparty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val bind by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        bind.btnEnter.setOnClickListener { onEnterClick() }
        bind.txtClean.setOnClickListener { onCleanClick() }

        if (checkPermissionForCamera().not()) {
            requestPermissionForCamera()
        }

        bindSavedInfo()
    }

    private fun onCleanClick() {
        bind.edtRoomName.setText("")
        bind.edtAccessToken.setText("")
    }

    private fun checkPermissionForCamera(): Boolean {
        val resultCamera: Int = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return resultCamera == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissionForCamera() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
    }

    private fun onEnterClick() {
        val roomName = bind.edtRoomName.text.toString()
        val accessToken = bind.edtAccessToken.text.toString()

        if (roomName.isEmpty() || accessToken.isEmpty()) {
            Toast.makeText(this, "Insira o nome da sala e o ACCESS TOKEN", Toast.LENGTH_SHORT).show()
        } else {
            saveInfo(roomName, accessToken)
            startActivity(RoomActivity.getStartIntent(this, roomName, accessToken))
        }
    }

    private fun saveInfo(roomName: String, accessToken: String) {
        PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putString("ROOM_NAME", roomName)
            .putString("ACCESS_TOKEN", accessToken)
            .apply()
    }

    private fun bindSavedInfo() {
        val roomName = PreferenceManager.getDefaultSharedPreferences(this).getString("ROOM_NAME", "")
        val accessToken = PreferenceManager.getDefaultSharedPreferences(this).getString("ACCESS_TOKEN", "")

        bind.edtRoomName.setText(roomName)
        bind.edtAccessToken.setText(accessToken)
    }

}