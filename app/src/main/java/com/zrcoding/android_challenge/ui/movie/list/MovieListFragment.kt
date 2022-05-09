package com.zrcoding.android_challenge.ui.movie.list

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.PopupMenu
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.zrcoding.android_challenge.R
import com.zrcoding.android_challenge.data.local.helpers.OrderBy
import com.zrcoding.android_challenge.databinding.MovieListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var binding: MovieListFragmentBinding
    private lateinit var sortPopupMenu: PopupMenu
    private lateinit var viewModel: MovieListViewModel
    private val movieListAdapter: MovieListAdapter by lazy {
        MovieListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.movie_list_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        setupSearchMovie()
        setupSortMenu()
        setupMovieListRecyclerView()
        setupClickListeners()
        subscribeUi()
    }

    private fun setupSearchMovie() {
        binding.searchMoviesEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateMovieListFromInput()
                true
            } else {
                false
            }
        }
        binding.searchMoviesEt.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateMovieListFromInput()
                true
            } else {
                false
            }
        }
        binding.searchMoviesEt.doAfterTextChanged {text ->
            text?.let {
                if (it.isEmpty()) {
                    updateMovieListFromInput()
                }
            }
        }
    }

    private fun setupSortMenu() {
        sortPopupMenu = PopupMenu(requireContext(), binding.sortBtn)
        sortPopupMenu.menuInflater.inflate(R.menu.sort_menu, sortPopupMenu.menu)
        binding.sortBtn.setOnClickListener { sortPopupMenu.show() }
    }

    private fun setupMovieListRecyclerView() {
        binding.movieListRv.adapter = movieListAdapter
    }

    private fun setupClickListeners() {
        sortPopupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            onMenuItemSelected(menuItem)
        }
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest {
                movieListAdapter.submitData(it)
            }
        }
    }

    private fun onMenuItemSelected(item: MenuItem): Boolean {
        val orderBy = when (item.itemId) {
            R.id.name_asc -> {
                OrderBy.ALPHABETICAL_ASC
            }
            R.id.name_desc -> {
                OrderBy.ALPHABETICAL_DESC
            }
            R.id.date_asc -> {
                OrderBy.DATE_ASC
            }
            R.id.date_desc -> {
                OrderBy.DATE_DESC
            }
            else -> return super.onContextItemSelected(item)
        }
        sortMovieList(orderBy)
        return if (orderBy != OrderBy.NONE) true else super.onContextItemSelected(item)
    }

    private fun updateMovieListFromInput() {
        binding.searchMoviesEt.text?.trim().let {
            viewModel.accept(MovieUiActions.Search(it.toString()))
            binding.movieListRv.scrollToPosition(0)
        }
    }

    private fun sortMovieList(orderBy: OrderBy) {
        viewModel.accept(MovieUiActions.Sort(orderBy))
        binding.movieListRv.scrollToPosition(0)
    }

}