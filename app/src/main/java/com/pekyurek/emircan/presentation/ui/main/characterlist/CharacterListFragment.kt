package com.pekyurek.emircan.presentation.ui.main.characterlist

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.FragmentCharacterListBinding
import com.pekyurek.emircan.presentation.core.util.GridDividerItemDecoration
import com.pekyurek.emircan.presentation.ui.base.BaseFragment
import com.pekyurek.emircan.presentation.ui.base.adapter.RecyclerLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentCharacterListBinding, CharacterListViewModel>() {

    override val viewModel: CharacterListViewModel by viewModels()
    override val layoutResId: Int = R.layout.fragment_character_list

    private val characterGridLayoutManager by lazy {
        GridDividerItemDecoration(
            resources.getDimension(R.dimen.material_grid_divide),
            resources.getInteger(R.integer.character_list_span_count)
        )
    }

    private lateinit var adapter: CharacterListAdapter

    override fun initBinding() {
        binding.viewModel = viewModel
    }

    override fun initViews() {
        initAnimations()
        adapter = CharacterListAdapter(onItemClick = { navigatorExtra, character ->
            val action =
                CharacterListFragmentDirections.actionMainFragmentToCharacterDetailFragment(
                    character
                )
            findNavController().navigate(action, navigatorExtra)
        })

        binding.rvCharacterList.adapter = adapter.withLoadStateFooter(
            RecyclerLoadStateAdapter(adapter::retry)
        )

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest {
                val isLoading = it.refresh is LoadState.Loading
                viewModel.showLoading(isLoading)
                if (isLoading.not() && adapter.itemCount != 0) cancel()
            }
        }

        initVerticalList()
        //TODO initGridList()
    }

    private fun initAnimations() {
        postponeEnterTransition()
        binding.rvCharacterList.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    override fun setObservers() {
        super.setObservers()
        viewModel.pagingCharacterList.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }

    private fun initVerticalList() {
        binding.rvCharacterList.layoutManager = LinearLayoutManager(context)
        binding.rvCharacterList.removeItemDecoration(characterGridLayoutManager)
    }

    private fun initGridList() {
        adapter.itemViewType = CharacterListViewType.GRID
        binding.rvCharacterList.apply {
            layoutManager = GridLayoutManager(
                context,
                resources.getInteger(R.integer.character_list_span_count)
            )
            addItemDecoration(characterGridLayoutManager)
        }
    }

}