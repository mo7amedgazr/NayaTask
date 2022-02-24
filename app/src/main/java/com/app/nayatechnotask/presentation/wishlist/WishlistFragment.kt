package com.app.nayatechnotask.presentation.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.nayatechnotask.databinding.FragmentWishlistBinding
import com.app.nayatechnotask.domain.entity.ListItem
import com.app.nayatechnotask.presentation.home.HomeViewModel
import com.app.nayatechnotask.presentation.wishlist.adapter.WishListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WishlistFragment : Fragment() {

    private val mViewModel: HomeViewModel by activityViewModels()
    private val mAdapter by lazy {
        WishListAdapter()
    }

    private val itemsList = mutableListOf<ListItem>()

    private var _binding: FragmentWishlistBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRV()
        initViewModel()
        initCLickListener()
    }

    private fun initCLickListener() {
        binding.ivWishlistIcon.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initRV() {
        binding.rvItemList.adapter = mAdapter
        binding.rvItemList.animation = null

        mAdapter.setItemTapListener(object : WishListAdapter.OnItemTap {
            override fun onSave(listItem: ListItem, position: Int) {
                if (listItem.saved) {
                    listItem.id?.let { mViewModel.removeItem(it) }
                    listItem.saved = false
                } else {
                    listItem.id?.let { mViewModel.saveItem(it) }
                    listItem.saved = true
                }
                itemsList[position] = listItem
                mAdapter.submitList(null)
                mAdapter.submitList(itemsList)
            }
        })
    }

    private fun initViewModel() {
        mViewModel.listItemsResponse.observe(viewLifecycleOwner) {
            val allItems = it.items
            allItems?.filter { it.saved }?.let { savedList -> itemsList.addAll(savedList) }

            // fire the adapter
            mAdapter.submitList(itemsList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}