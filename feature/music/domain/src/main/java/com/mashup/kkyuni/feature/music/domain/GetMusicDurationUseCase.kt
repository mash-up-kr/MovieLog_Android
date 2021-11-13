package com.mashup.kkyuni.feature.music.domain

import javax.inject.Inject

class GetMusicDurationUseCase @Inject constructor(private val musicRepository: MusicRepository) {
    suspend operator fun invoke(id: String): String {
        val duration = musicRepository.getDuration(id)
        return timeConvertor(duration.items[0].contentDetail.duration)
    }

    private fun timeConvertor(time: String): String {
        var duration = time.substring(2)
        var h = ""
        var m = ""
        var s = ""

        if (duration.indexOf("H") > -1) {
            h = duration.substring(0, duration.indexOf("H"))
            duration = duration.substring(duration.indexOf("H") + 1)
        } else {
            h = ""
        }

        if (duration.indexOf("M") > -1) {
            m = duration.substring(0, duration.indexOf("M"))
            duration = duration.substring(duration.indexOf("M") + 1)
            if (m.length == 1) {
                m = "0$m"
            }
        } else {
            m = if (h.isNotEmpty()) "00"
            else "0"
        }

        if (duration.indexOf("S") > -1) {
            s = duration.substring(0, duration.indexOf("S"))
            if (s.length == 1) {
                s = "0$s"
            }
        } else {
            s = "00"
        }

        if (h.isNotEmpty()) {
            return "$h:$m:$s"
        } else {
            return "$m:$s"
        }
    }
}