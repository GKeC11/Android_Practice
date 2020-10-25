package com.example.android_school.music

class MusicEvent(musicBean: MusicBean) {
    var music = musicBean
}

class PositionEvent(position: Int){
    var position = position
}

class StateEvent(isPlay: Boolean){
    var isPlay = isPlay
}