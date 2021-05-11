package com.pekyurek.emircan.presentation.ui.main.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pekyurek.emircan.databinding.ItemGridCharacterBinding
import com.pekyurek.emircan.databinding.ItemVerticalCharacterBinding
import com.pekyurek.emircan.presentation.core.data.model.character.Character
import com.pekyurek.emircan.presentation.core.extensions.nextItemOrFirst
import com.pekyurek.emircan.presentation.ui.base.adapter.RecyclerLoadStateAdapter

class CharacterListAdapter(
    var itemViewType: CharacterListViewType = CharacterListViewType.VERTICAL,
    private val onItemClick: (navigationExtra: FragmentNavigator.Extras, character: Character) -> Unit
) : PagingDataAdapter<Character, RecyclerView.ViewHolder>(CHARACTER_COMPARATOR) {

    init {
        withLoadStateFooter(RecyclerLoadStateAdapter(::retry))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (itemViewType) {
            CharacterListViewType.VERTICAL -> VerticalViewHolder(
                ItemVerticalCharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            CharacterListViewType.GRID -> GridViewHolder(
                ItemGridCharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { character ->
            when (holder) {
                is VerticalViewHolder -> holder.bind(character)
                is GridViewHolder -> holder.bind(character)
            }
        }
    }

    inner class VerticalViewHolder(private val binding: ItemVerticalCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.root.setOnClickListener {
                onItemClick.invoke(
                    FragmentNavigatorExtras(binding.ivCharacterIcon to binding.ivCharacterIcon.transitionName),
                    character
                )
            }
            binding.character = character
            binding.executePendingBindings()
        }
    }

    inner class GridViewHolder(private val binding: ItemGridCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.root.setOnClickListener {
                onItemClick.invoke(
                    FragmentNavigatorExtras(binding.ivCharacterIcon to binding.ivCharacterIcon.transitionName),
                    character
                )
            }
            binding.character = character
            binding.executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int = itemViewType.typeId

    fun changeItemViews(onSelectedView: (selectedViewType: CharacterListViewType) -> Unit) {
        itemViewType = CharacterListViewType.values().toList().nextItemOrFirst(itemViewType)
        onSelectedView(itemViewType)
        notifyDataSetChanged()
    }
}


val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}