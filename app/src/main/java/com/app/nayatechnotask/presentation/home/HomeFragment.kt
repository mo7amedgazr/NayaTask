package com.app.nayatechnotask.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.nayatechnotask.R
import com.app.nayatechnotask.databinding.FragmentHomeBinding
import com.app.nayatechnotask.domain.entity.ListItem
import com.app.nayatechnotask.presentation.home.adapter.ItemsListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val mViewModel: HomeViewModel by activityViewModels()
    private val mAdapter by lazy {
        ItemsListAdapter()
    }

    private val itemsList = mutableListOf<ListItem>()

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private var viewInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        initViewModel()
        initClickListener()

    }

    private fun initClickListener() {
        binding.ivWishlistIcon.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun initViewModel() {
        mViewModel.listItemsResponse.observe(viewLifecycleOwner) {
            it.currency?.let { cr -> mAdapter.setCurrency(cr) }
            it.items?.let { list ->
                itemsList.clear()
                itemsList.addAll(list)
            }
            mAdapter.submitList(itemsList)
            mAdapter.notifyDataSetChanged()
        }

    }

    override fun onResume() {
        super.onResume()

        mViewModel.getItemsList()
    }

    private fun initRV() {
        binding.rvItemList.adapter = mAdapter
        binding.rvItemList.animation = null

        mAdapter.setItemTapListener(object : ItemsListAdapter.OnItemTap {
            override fun onSave(listItem: ListItem, position: Int) {
                if (listItem.saved) {
                    listItem.id?.let { mViewModel.removeItem(listItem) }
                    listItem.saved = false
                } else {
                    listItem.id?.let { mViewModel.saveItem(listItem) }
                    listItem.saved = true
                }
                itemsList[position] = listItem
                mAdapter.submitList(itemsList)
                mAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}