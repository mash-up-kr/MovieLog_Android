package com.mashup.kkyuni.feature.playlist.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.feature.playlist.domain.model.MusicModel
import com.mashup.kkyuni.feature.playlist.presentation.holder.PlayListViewHolder
import com.mashup.kkyuni.feature.playlist.presentation.holder.impl.PlayListEmptyViewHolderImpl
import com.mashup.kkyuni.feature.playlist.presentation.holder.impl.PlayListMusicViewHolderImpl

class PlayListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val playList = mutableListOf<MusicModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun updatePlayList(list: List<MusicModel>){
        playList.clear()
        playList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when(playList[position]){
            is MusicModel.EmptyData -> TYPE_EMPTY_LIST
            is MusicModel.MusicData -> TYPE_MUSIC_LIST
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_EMPTY_LIST -> PlayListEmptyViewHolderImpl(
                DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.holder_play_list_empty,
                parent,
                false
                )
            )
            TYPE_MUSIC_LIST -> PlayListMusicViewHolderImpl(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.holder_play_list_music,
                    parent,
                    false
                )
            )
            else -> {
                throw Exception("is undefined view type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is PlayListViewHolder){
            holder.bind(playList[position])
        }else {
            throw TypeCastException("is impossible type cast to PlayListViewHolder")
        }
    }

    override fun getItemCount() = playList.size

    companion object {
        const val TYPE_EMPTY_LIST = 10
        const val TYPE_MUSIC_LIST = 11
    }
}