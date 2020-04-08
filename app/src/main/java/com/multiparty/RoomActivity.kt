package com.multiparty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.multiparty.databinding.ActivityRoomBinding
import com.multiparty.util.ParticipantListenerAdapter
import com.multiparty.util.RoomListenerAdapter
import com.twilio.video.*


class RoomActivity : AppCompatActivity(), RoomListenerAdapter, ParticipantListenerAdapter {

    private var trackOnMain: VideoTrack? = null
    private var localVideoTrack: LocalVideoTrack? = null
    private var room: Room? = null

    private val bind by lazy { ActivityRoomBinding.inflate(layoutInflater) }

    private val usedVideoList: MutableMap<RemoteParticipant, VideoView> = mutableMapOf()

    companion object {
        fun getStartIntent(context: Context, roomName: String, accessToken: String): Intent {
            return Intent(context, RoomActivity::class.java)
                .putExtra("EXTRA_ROOM_NAME", roomName)
                .putExtra("EXTRA_ACCESS_TOKEN", accessToken)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        localVideoTrack = LocalVideoTrack.create(
            this,
            true,
            CameraCapturer(
                this,
                CameraCapturer.CameraSource.FRONT_CAMERA,
                null
            )
        )

        bind.localVideo.setOnClickListener { setTrackToMain(localVideoTrack) }

        setTrackToMain(localVideoTrack)

        localVideoTrack?.addRenderer(bind.localVideo)

        connectToRoom(intent?.getStringExtra("EXTRA_ROOM_NAME").orEmpty())
    }

    private fun setTrackToMain(videoTrack: VideoTrack?) {
        trackOnMain?.removeRenderer(bind.mainVideo)
        videoTrack?.addRenderer(bind.mainVideo)
        trackOnMain = videoTrack
    }

    override fun onDestroy() {
        room?.disconnect()
        localVideoTrack?.release()
        super.onDestroy()
    }

    private fun connectToRoom(roomName: String) {
        val connectOptions =
            ConnectOptions.Builder(intent?.getStringExtra("EXTRA_ACCESS_TOKEN").orEmpty())
                .roomName(roomName)
                .videoTracks(listOf(localVideoTrack))
                .build()

        room = Video.connect(this, connectOptions, this)
    }


    override fun onConnected(room: Room) {
        toast("Connected")

        room.remoteParticipants.forEach {

            val remoteTrack = it.remoteVideoTracks.first()

            if (remoteTrack.isTrackSubscribed) {
                addRemoteParticipantVideo(it, remoteTrack.remoteVideoTrack)
            }

            it.setListener(this)
        }
    }

    override fun onParticipantConnected(room: Room, remoteParticipant: RemoteParticipant) {
        toast("${remoteParticipant.identity} connected")

        if (remoteParticipant.remoteVideoTracks.first().isTrackSubscribed) {
            addRemoteParticipantVideo(remoteParticipant, remoteParticipant.remoteVideoTracks.first().remoteVideoTrack)
        }

        remoteParticipant.setListener(this)
    }

    private fun addRemoteParticipantVideo(participant: RemoteParticipant, videoTrack: RemoteVideoTrack?) {

        val videoView = VideoView(this).apply { setOnClickListener { setTrackToMain(videoTrack) } }

        bind.ctnParticipants.addView(videoView)
        usedVideoList[participant] = videoView
        videoTrack?.addRenderer(videoView)

        setTrackToMain(videoTrack)
    }


    override fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onVideoTrackSubscribed(
        participant: RemoteParticipant,
        videoTrack: RemoteVideoTrackPublication,
        remoteVideoTrack: RemoteVideoTrack
    ) {
        addRemoteParticipantVideo(participant, remoteVideoTrack)
    }

    override fun onVideoTrackUnsubscribed(
        participant: RemoteParticipant,
        videoTrack: RemoteVideoTrackPublication,
        remoteVideoTrack: RemoteVideoTrack
    ) {
        usedVideoList.remove(participant)?.let {
//            remoteVideoTrack.removeRenderer(it)
//            bind.ctnParticipants.removeView(it)
        }
    }
}