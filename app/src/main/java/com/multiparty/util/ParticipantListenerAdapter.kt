package com.multiparty.util

import com.twilio.video.*

interface ParticipantListenerAdapter : RemoteParticipant.Listener {

    override fun onAudioTrackPublished(participant: RemoteParticipant, audioTrack: RemoteAudioTrackPublication) {

    }

    override fun onAudioTrackUnpublished(participant: RemoteParticipant, audioTrack: RemoteAudioTrackPublication) {
    }

    override fun onDataTrackPublished(participant: RemoteParticipant, dataTrack: RemoteDataTrackPublication) {
    }

    override fun onDataTrackUnpublished(participant: RemoteParticipant, dataTrack: RemoteDataTrackPublication) {
    }

    override fun onVideoTrackPublished(
        participant: RemoteParticipant, videoTrack: RemoteVideoTrackPublication
    ) {
    }

    override fun onVideoTrackUnpublished(
        participant: RemoteParticipant, videoTrack: RemoteVideoTrackPublication
    ) {
    }

    override fun onAudioTrackSubscribed(
        participant: RemoteParticipant,
        audioTrack: RemoteAudioTrackPublication,
        remoteAudioTrack: RemoteAudioTrack
    ) {
    }

    override fun onAudioTrackUnsubscribed(
        participant: RemoteParticipant, audioTrack: RemoteAudioTrackPublication, remoteAudioTrack: RemoteAudioTrack
    ) {
    }

    override fun onAudioTrackSubscriptionFailed(
        participant: RemoteParticipant,
        audioTrack: RemoteAudioTrackPublication,
        twilioException: TwilioException
    ) {
    }

    override fun onDataTrackSubscribed(
        participant: RemoteParticipant,
        dataTrack: RemoteDataTrackPublication,
        remoteDataTrack: RemoteDataTrack
    ) {
    }

    override fun onDataTrackUnsubscribed(
        participant: RemoteParticipant,
        dataTrack: RemoteDataTrackPublication,
        remoteDataTrack: RemoteDataTrack
    ) {
    }

    override fun onDataTrackSubscriptionFailed(
        participant: RemoteParticipant,
        dataTrack: RemoteDataTrackPublication,
        twilioException: TwilioException
    ) {
    }

    override fun onVideoTrackSubscribed(
        participant: RemoteParticipant,
        videoTrack: RemoteVideoTrackPublication,
        remoteVideoTrack: RemoteVideoTrack
    ) {

    }

    override fun onVideoTrackUnsubscribed(
        participant: RemoteParticipant,
        videoTrack: RemoteVideoTrackPublication,
        remoteVideoTrack: RemoteVideoTrack
    ) {

    }

    override fun onVideoTrackSubscriptionFailed(
        participant: RemoteParticipant,
        videoTrack: RemoteVideoTrackPublication,
        twilioException: TwilioException
    ) {

    }

    override fun onAudioTrackEnabled(
        participant: RemoteParticipant,
        audioTrack: RemoteAudioTrackPublication
    ) {
    }

    override fun onAudioTrackDisabled(
        participant: RemoteParticipant,
        audioTrack: RemoteAudioTrackPublication
    ) {
    }

    override fun onVideoTrackEnabled(
        participant: RemoteParticipant,
        videoTrack: RemoteVideoTrackPublication
    ) {
    }

    override fun onVideoTrackDisabled(
        participant: RemoteParticipant,
        videoTrack: RemoteVideoTrackPublication
    ) {
    }
}
