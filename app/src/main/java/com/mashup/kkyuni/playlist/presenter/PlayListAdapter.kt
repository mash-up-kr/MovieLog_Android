package com.mashup.kkyuni.playlist.presenter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.R
import com.mashup.kkyuni.databinding.HolderPlayListEmptyBinding
import com.mashup.kkyuni.playlist.domain.model.PlayList
import com.mashup.kkyuni.playlist.presenter.holder.PlayListViewHolder
import com.mashup.kkyuni.playlist.presenter.holder.impl.PlayListEmptyViewHolderImpl
import com.mashup.kkyuni.playlist.presenter.holder.impl.PlayListMusicViewHolderImpl

class PlayListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val playList = mutableListOf<PlayList>()

    @SuppressLint("NotifyDataSetChanged")
    fun updatePlayList(list: List<PlayList>){
        playList.clear()
        playList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when(playList[position]){
            is PlayList.EmptyData -> TYPE_EMPTY_LIST
            is PlayList.MusicData -> TYPE_MUSIC_LIST
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