package com.example.android_school.music

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import java.util.concurrent.TimeUnit

class MediaHelper {

    companion object{

        fun queryMusic(context: Context): MutableList<MusicBean>{
            val musicList = mutableListOf<MusicBean>()
            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE
            )
            val selection = "${MediaStore.Audio.Media.DURATION} >= ?"
            val selectionArgs = arrayOf(
                TimeUnit.MILLISECONDS.convert(1, TimeUnit.MINUTES).toString()
            )
            val sortOrder = "${MediaStore.Audio.Media.DISPLAY_NAME} ASC"

            val query = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder
            )

            query.use {
                val idColumn = it!!.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                val nameColumn = it!!.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                val authorColumn = it!!.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                val durationColumn = it!!.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
                val sizeColumn = it!!.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)

                while(it!!.moveToNext()){
                    val id = it.getLong(idColumn)
                    val name = it.getString(nameColumn)
                    val author = it.getString(authorColumn)
                    val duration = it.getInt(durationColumn)
                    val size = it.getInt(sizeColumn)
                    val contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)

                    musicList.add(
                        MusicBean(
                            id,
                            name,
                            author,
                            duration,
                            size,
                            contentUri
                        )
                    )
                }
            }

            return musicList
        }
    }
}