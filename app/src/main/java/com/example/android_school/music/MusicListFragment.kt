package com.example.android_school.music

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module_common.BaseBeanAdapter
import com.example.android_school.databinding.FragmentMusicListBinding

class MusicListFragment : Fragment(){
    var binding: FragmentMusicListBinding? = null
    var musicAdapter = BaseBeanAdapter<MusicBean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicListBinding.inflate(inflater, container, false)
        val musicList = MediaHelper.queryMusic(requireContext())

        binding?.rvMusic?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = musicAdapter
        }

        musicAdapter.addBeans(musicList)
        return binding?.root
    }
}