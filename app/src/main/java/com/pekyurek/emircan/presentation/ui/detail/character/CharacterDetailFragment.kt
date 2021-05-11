package com.pekyurek.emircan.presentation.ui.detail.character

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.FragmentCharacterDetailBinding
import com.pekyurek.emircan.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>() {

    override val layoutResId: Int = R.layout.fragment_character_detail

    private val args: CharacterDetailFragmentArgs by navArgs()

    override val viewModel: CharacterDetailViewModel by viewModels()

    override fun onInit() {
        loadData()
    }

    override fun initBinding() {
        binding.character = args.argCharacter
        binding.invalidateAll()
    }

    private fun loadData() {
        viewModel.getLastEpisode(args.argCharacter)
    }

    override fun initViews() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

    }
}