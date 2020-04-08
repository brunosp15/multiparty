package com.multiparty.util

import com.twilio.video.RemoteParticipant
import com.twilio.video.Room
import com.twilio.video.TwilioException

interface RoomListenerAdapter : Room.Listener {

    override fun onRecordingStopped(room: Room) = toast("RecordingStopped")

    override fun onRecordingStarted(room: Room) = toast("RecordingStarted")

    override fun onConnectFailure(room: Room, ex: TwilioException) = toast("ConnectFailure: ${ex.localizedMessage}")

    override fun onReconnected(room: Room) = toast("Reconnected")

    override fun onDisconnected(room: Room, twilioException: TwilioException?) = toast("Disconnected")

    override fun onReconnecting(room: Room, twilioException: TwilioException) = toast("Reconnecting")

    override fun onParticipantDisconnected(room: Room, participant: RemoteParticipant) = toast("${participant.identity} disconnected")

    fun toast(message: String)
}